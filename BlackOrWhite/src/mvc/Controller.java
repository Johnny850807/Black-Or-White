package mvc;

import java.util.ArrayList;
import java.util.List;

import mvc.stage.*;
import role.AI;
import role.Player;
import role.Role;
import weapon.bullets.Bullet;

public class Controller extends Thread{
	public volatile static Controller controller = new Controller(); // double checked singleton
	public static boolean gameStart = false;
	public static boolean netWork = false;
	public static View view;
	public Player player1 = null;  // single game
	public Player player2 = null;  // net game
	public Stage curStage;
	public volatile List<Role> roles;
	public volatile List<Bullet> bullets;
	public boolean[] roleUpdates = new boolean[1000];  //確認所有角色狀態更新之後，controller才更新一次畫面
	public boolean[] bulletUpdates = new boolean[1000];  //確認所有子彈狀態更新之後，controller才更新一次畫面
	
	private Controller(){}; // singleton
	public static Controller getController(){return controller;}
	
	private void createPlayer(){  //create the player in the pair of specific coordinates
		player1 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
		Log.d("Create PLAYER 1 ");
		new Thread(player1).start();
		roles.add(player1);
		/*if(netWork){ //網路部分....可有可無
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
		new Thread(curStage).start(); // 開始生產怪物
		while(gameStart)
		{
			
		}
	}
	
	public void updateModel(Role role){
		//更新某個角色的狀態
		roleUpdates[roles.indexOf(role)] = true;
	}
	
	public void updateModel(Bullet bullet){
		//更新某個子彈的狀態
		roleUpdates[roles.indexOf(bullet)] = true;
	}
	
	//確認是否全部更新
	public boolean checkUpdate(){
		for ( int i = 0 ; i < roles.size() ; i ++ )
			if ( roleUpdates[i] == false )
				return false;
		for ( int i = 0 ; i < bullets.size() ; i ++ )
			if ( bulletUpdates[i] == false )
				return false;
		return true;
	}
	
	public void clearAllUpdate(){
		roleUpdates = new boolean[1000]; // 預設為false ...
		bulletUpdates = new boolean[1000]; // 預設為false ...
	}
	
	public void movePlayer(ActionType act,Dir dir){
		player1.addRequest(act, dir);
		Log.d("按鍵需求:"+act.getClass().getName()+","+dir.getClass().getName());
	}
	
	public int getRemainningMonster(){
		//得到剩下怪物的數量
		int sum = 0;
		for ( int i = 0 ; i < roles.size() ; i ++ )
			sum += roles.get(i) instanceof AI ? 1 : 0;
		return sum;
	}
	
	public void addMonster(AI monster){
		roles.add(monster);
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
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Bullet> getBullets() {
		return bullets;
	}
	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	
}
