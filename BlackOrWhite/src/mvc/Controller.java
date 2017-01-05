package mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import mvc.gameObject.GameObjects;
import mvc.stage.*;
import role.AI;
import role.Player;
import role.Role;
import weapon.bullets.Bullet;
import weapon.gameEffects.GameEffect;
import weapon.guns.fallenWeapon.FallenItem;

public class Controller extends Thread{
	private volatile static Controller controller = new Controller(); // singleton
	private static boolean gameStart = false;
	private static boolean netWork = false;
	private static View view;
	private Player player1 = null;  // single game
	private Player player2 = null;  // net game
	private Stage curStage;
	private volatile GameObjects gameObjects = GameObjects.getGameObjects();  //all objects will be painted in the game
	
	private Controller(){}; // singleton
	public static Controller getController(){return controller;}
	
	private void createPlayer(){  //create the player in the pair of specific coordinates
		player1 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
		Log.d("Create PLAYER 1 ");
		gameObjects.addRole(player1);
		if(netWork){ //網路部分....可有可無
			player2 = new Player(Map1Director.PLAYER2_CREATE_X,Map1Director.PLAYER2_CREATE_Y);
			gameObjects.addRole(player2);
		}
	}
	public void startGame(){
		gameStart = true;
		createPlayer();
		super.setName("controller");
		
		start();  //run stage
	}
	public void startP2PGame(){
		gameStart = true;
		netWork = true;
		createPlayer();
		start();  //run stage
	}
	public void run(){
		curStage = StageFactory.createAllStages(this);
		view.refreshScreen();
		Log.d("Controller run!");
		new Thread(curStage).start(); // 開始生產怪物
		try{
			while(true)
			{
				checkUpdate();
				checkConflict();
				view.refreshScreen();
				Thread.sleep(15); 
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		catch (Error e){
			e.printStackTrace();
		}
	}
	
	public void showGameFinalWinMessage(){
		 view.showGameFinalWinMessage();
	}
	
	public void deleteModel(Role role){
		gameObjects.removeRole(role);
	}
	public void deleteModel(Bullet bullet){
		gameObjects.removeBullet(bullet);
	}
	public void deleteModel(FallenItem fallen){
		gameObjects.removeFallenItem(fallen);
	}
	public void deleteModel(GameEffect effect) {
		gameObjects.removeEffect(effect);
	}
	
	public void checkConflict(){
		//確認衝突
		List<Role> roles = gameObjects.getRoles();
		AI ai;
		Model AIModel;  //怪物資料
		//確認玩家是否碰到怪物
		//略過玩家
		for ( int i = 1 ; i < roles.size() ; i ++ ){
			if(roles.get(i)instanceof Player)
				continue;
			ai = (AI)roles.get(i);
			AIModel = ai.getModel();
			if(player1 != null)
			if ( player1.conflictWithSomething(AIModel.getcX(), AIModel.getcY(), ai.getFeetW() , ai.getFeetH() ))
				player1.getDamaged(ai);
			if(player2 !=null )
			if ( player2.conflictWithSomething(AIModel.getcX(), AIModel.getcY(), ai.getFeetW() , ai.getFeetH() ))
				player2.getDamaged(ai);
		}
		//確認怪物是否碰到子彈
		List<Bullet> bullets = gameObjects.getBullets();
		Role role;
		Bullet bullet;
		Model bModel;
		for ( int i = 0 ; i < roles.size() ; i ++ ){
			role = roles.get(i);
			for ( int j = 0 ; j < bullets.size() ; j ++ ){
				bullet = bullets.get(j);
				bModel = bullet.getModel();
				if ( role.conflictWithSomething(bModel.getcX(), bModel.getcY(), bullet.getvW(), bullet.getvH())){
					role.getDamaged(bullet);
				}
			}
		}
		
		//確認是否撿到槍
		List<FallenItem>  fallItems= gameObjects.getFallItems();
		FallenItem fallItem;
		Model fModel;
		for ( int i = 0 ; i < fallItems.size() ; i ++ ){
			fallItem = fallItems.get(i);
			fModel = fallItem.getModel();
			if(player1 != null)
			if ( player1.conflictWithSomething(fModel.getcX(), fModel.getcY(), fallItem.getW(), fallItem.getH())){
				player1.setGun(fallItem.getGun());
				fallItems.remove(fallItem);
				break;
			}
			if(player2 != null)
			if ( player2.conflictWithSomething(fModel.getcX(), fModel.getcY(), fallItem.getW(), fallItem.getH())){
				player2.setGun(fallItem.getGun());
				fallItems.remove(fallItem);
				break;
			}
		}
	}
	
	//update all the object's states
	public boolean checkUpdate(){
		try{
			Iterator<Role> itR = gameObjects.rolesIterator();
			Iterator<Bullet> itB = gameObjects.bulletsIterator();	
			while(itR.hasNext())
				itR.next().run();
			while(itB.hasNext())
				itB.next().run();
		}catch(ConcurrentModificationException err){
		}catch(Exception err){
			err.printStackTrace();
		}
		return true;
	}
	
	public void fallGun(FallenItem fallItem){
		gameObjects.addFallenGun(fallItem);
	}
	
	public void updatePlayerHp(){
		view.updatePlayerHp();
	}
	
	public void movePlayer(ActionType act,Dir dir){
		if(player1 != null)
			player1.addRequest(act, dir);
	}
	public void movePlayer2(ActionType act,Dir dir){
		if(player2 != null)
			player2.addRequest(act, dir);
	}
	
	public int getRemainningMonster(){
		//得到剩下怪物的數量
		int sum = 0;
		List<Role> roles = new ArrayList<Role>();  //為了防止在走訪roles聚合時，controller更動了roles內容而造成IndexOutOfBound
		//先複製內容 再到這個不被干擾的容器內走訪
		roles.addAll(gameObjects.getRoles());
		try{
			for ( Role r : roles )
				sum += r instanceof AI ? 1 : 0;
		}catch(Exception err){
			err.printStackTrace();
		}
		
		return sum;
	}
	
	public void addMonster(AI monster){
		gameObjects.addRole(monster);
	}
	
	public void addBullet(Bullet bullet){
		gameObjects.addBullet(bullet);
	}
	
	public void addEffect(GameEffect e){
		gameObjects.addEffect(e);
	}
	
	//治療玩家
	public void curePlayer(int hp){
		if (player1 != null )
			player1.setHp( player1.getHp()+hp > 500 ? 500 : player1.getHp()+hp);
		if (player2 != null)
			player2.setHp(player2.getHp()+hp > 500 ? 500 : player2.getHp()+hp);
	}
	
	public static boolean isNetWork() {
		return netWork;
	}
	public static void setNetWork(boolean netWork) {
		Controller.netWork = netWork;
	}
	public static boolean isGameStart() {
		return gameStart;
	}
	public static void setGameStart(boolean gameStart) {
		Controller.gameStart = gameStart;
	}
	public static View getView() {
		return view;
	}
	public static void setView(View view) {
		Controller.view = view;
	}
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void player1SetDie(){
		player1 = null;
	}
	public void player2SetDie(){
		player2 = null;
	}
	
}
