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
	public Role player1 = null;  // single game
	public Role player2 = null;  // net game
	public Stage curStage;
	public List<Role> roles;
	public List<Bullet> bullets;
	
	private Controller(){}; // singleton
	public static Controller getController(){return controller;}
	
	private void createPlayer(){
		player1 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
		if(netWork)
			player2 = new Player(Map1Director.PLAYER_CREATE_X,Map1Director.PLAYER_CREATE_Y);
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
		new Thread(curStage).start(); // 開始生產怪物
		while(gameStart)
		{
		
		}
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
