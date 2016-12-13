package mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import mvc.gameObject.GameObjects;
import mvc.stage.*;
import role.AI;
import role.Player;
import role.Role;
import weapon.bullets.Bullet;

public class Controller extends Thread{
	private volatile static Controller controller = new Controller(); // singleton
	private static boolean gameStart = false;
	private static boolean netWork = false;
	private static View view;
	private Player player1 = null;  // single game
	private Player player2 = null;  // net game
	private Stage curStage;
	private GameObjects gameObjects = GameObjects.getGameObjects();  //all objects will be painted in the game
	
	private Controller(){}; // singleton
	public static Controller getController(){return controller;}
	
	private void createPlayer(){  //create the player in the pair of specific coordinates
		player1 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
		Log.d("Create PLAYER 1 ");
		gameObjects.addRole(player1);
		/*if(netWork){ //網路部分....可有可無
			player2 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
			roles.add(player2);
		}*/
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
				Thread.sleep(70); 
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
	
	public void deleteModel(Role role){
		gameObjects.removeRole(role);
	}
	public void deleteModel(Bullet bullet){
		gameObjects.removeBullet(bullet);
	}
	
	public void checkConflict(){
		//確認衝突
		List<Role> roles = gameObjects.getRoles();
		AI ai;
		Model playerModel = player1.getModel();
		int pX = playerModel.getcX() , pY = playerModel.getcY(); //玩家座標
		Model AIModel;  //怪物資料
		//確認玩家是否碰到怪物
		//略過玩家
		for ( int i = 1 ; i < roles.size() ; i ++ ){
			ai = (AI)roles.get(i);
			AIModel = ai.getModel();
			if ( player1.conflictWithSomething(AIModel.getcX(), AIModel.getcY(), ai.getFeetW() , ai.getFeetH() ))
				player1.getDamaged(ai);
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
		}catch(Exception err){
			Log.d(err.toString());
			return false;
		}
		return true;
	}
	
	public void updatePlayerHp(){
		view.updatePlayerHp(player1.getModel().getHp());
	}
	
	public void playerDie(){
		view.playerSetDie();
	}
	
	public void movePlayer(ActionType act,Dir dir){
		player1.addRequest(act, dir);
	}
	
	public int getRemainningMonster(){
		//得到剩下怪物的數量
		int sum = 0;
		Log.d(gameObjects.rolesSize()+"");
		for ( int i = 0 ; i < gameObjects.rolesSize() ; i ++ )
			sum += gameObjects.getRole(i) instanceof AI ? 1 : 0;
		return sum;
	}
	
	public void addMonster(AI monster){
		gameObjects.addRole(monster);
	}
	
	public void addBullet(Bullet bullet){
		gameObjects.addBullet(bullet);
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
	
}
