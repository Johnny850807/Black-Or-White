package mvc.stage;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import mvc.Controller;
import mvc.Log;
import mvc.Map1Director;
import mvc.View;
import role.AI;

public abstract class Stage implements Runnable{
	protected Stage nextStage;
	protected Controller controller;
	protected int spacingCreation;
	protected List<AI> monsters = Collections.checkedList(new ArrayList<AI>(), AI.class);
	public Stage(Controller controller,int spacingCreation){
		this.controller = controller;
		this.spacingCreation = spacingCreation;
	}
	@Override
	public void run(){
		playerControl(); //操作玩家
		playMusic(); //播放音樂
		
		//先產生怪物在特定區域
		for(int i = 0 ; i < Map1Director.AI_CREATE_X_SET.length && i < monsters.size() ; i ++ )
			specificlyAddMonster(i);
		int time;
		
		//困難模式的話 生產時間減少1/2
		if(View.crazyMode)
			spacingCreation *= 0.5;
		
		//再慢慢隨機位置產生
		while(monsters.size() > 0){
			try {TimeUnit.SECONDS.sleep(spacingCreation);}catch (InterruptedException e) {e.printStackTrace();}
			for ( int i = 0 ; i < 4 && monsters.size() > 0 ; i ++ )
				randomlyAddMonster();
		}
			
		while ( controller.getRemainningMonster() > 0 ); //只要還有一隻怪物就不繼續

		try {TimeUnit.SECONDS.sleep(5);}catch (InterruptedException e) {e.printStackTrace();}
			
		Log.d("下一關開始!!");
		new Thread(nextStage).start();  //下一關
	} 
	
	public void specificlyAddMonster(int siteIndex){
		//指定位置產生怪物
		int x = Map1Director.AI_CREATE_X_SET[siteIndex];
		int y = Map1Director.AI_CREATE_Y_SET[siteIndex];
		AI monster = monsters.remove(0);
		monster.setDimension(new Dimension(x,y));
		controller.addMonster(monster);
	}
	
	public void randomlyAddMonster(){
		//隨機產生一隻怪物的程序
		AI monster = monsters.remove(0);
		monster.setDimension(getRandomCreateXY());
		controller.addMonster(monster);
	}
	
	public Dimension getRandomCreateXY(){
		//隨機產生作標
		Random random = new Random();
		int index = random.nextInt(Map1Director.AI_CREATE_X_SET.length);
		int x = Map1Director.AI_CREATE_X_SET[index];
		int y = Map1Director.AI_CREATE_Y_SET[index];	
		Dimension d = new Dimension(x,y);
		return new Dimension(x,y); 
	}
	
	public abstract void playMusic();  //是否更換音樂
	public abstract void playerControl();  //是否進行操作玩家 也許治療 也許移位..
	
	public Stage getNextStage() {
		return nextStage;
	}
	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
