package mvc;

import java.util.ArrayList;
import java.util.List;

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
	private boolean[] roleUpdates = new boolean[10000];  //�T�{�Ҧ����⪬�A��s����Acontroller�~��s�@���e��
	private boolean[] bulletUpdates = new boolean[10000];  //�T�{�Ҧ��l�u���A��s����Acontroller�~��s�@���e��
	
	private Controller(){}; // singleton
	public static Controller getController(){return controller;}
	
	private void createPlayer(){  //create the player in the pair of specific coordinates
		player1 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
		Log.d("Create PLAYER 1 ");
		new Thread(player1).start();
		gameObjects.addRole(player1);
		/*if(netWork){ //��������....�i���i�L
			player2 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
			roles.add(player2);
		}*/
	}
	public void startGame(){
		gameStart = true;
		createPlayer();
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
		new Thread(curStage).start(); // �}�l�Ͳ��Ǫ�
		try{
			while(true)
			{
				if(checkUpdate())
				{
					view.refreshScreen();
					clearAllUpdate();
				}
				else
				{
					Log.d("False");
				}
				Thread.sleep(100); 
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(Exception e){
			Log.d(e.toString());
		}
		catch (Error e){
			Log.d(e.toString());
		}
		
	}
	
	public void updateModel(Role role){
		//��s�Y�Ө��⪺���A
		int idx = gameObjects.getIndexOf(role);
		if(idx != -1)
			roleUpdates[gameObjects.getIndexOf(role)] = true;
	}
	
	public void updateModel(Bullet bullet){
		//��s�Y�Ӥl�u�����A
		int idx = gameObjects.getIndexOf(bullet);
		if(idx != -1)
		bulletUpdates[gameObjects.getIndexOf(bullet)] = true;
	}
	
	public void deleteModel(Role role){
		gameObjects.removeRole(role);
	}
	public void deleteModel(Bullet bullet){
		gameObjects.removeBullet(bullet);
	}
	
	//�T�{�O�_������s
	public boolean checkUpdate(){
		for ( int i = 0 ; i < gameObjects.rolesSize() ; i ++ ){
			if(gameObjects.getRole(i)instanceof Player)  //���ˬd���a�O�_��s
				continue;
			if ( roleUpdates[i] == false )
				return false;
		}
		for ( int i = 0 ; i < gameObjects.bulletSize() ; i ++ )
			if ( bulletUpdates[i] == false )
				return false;
		return true;
	}
	
	public void clearAllUpdate(){
		roleUpdates = new boolean[1000]; // �w�]��false ...
		bulletUpdates = new boolean[1000]; // �w�]��false ...
	}
	
	public void movePlayer(ActionType act,Dir dir){
		player1.addRequest(act, dir);
	}
	
	public int getRemainningMonster(){
		//�o��ѤU�Ǫ����ƶq
		int sum = 0;
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
