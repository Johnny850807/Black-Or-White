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
import role.AI;

public abstract class Stage implements Runnable{
	protected Stage nextStage;
	protected Controller controller;
	protected List<AI> monsters = Collections.checkedList(new ArrayList<AI>(), AI.class);
	public Stage(Controller controller){
		this.controller = controller;
	}
	@Override
	public void run(){

			//先產生怪物在特定區域
			for(int i = 0 ; i < Map1Director.AI_CREATE_X_SET.length && i < monsters.size() ; i ++ )
				specificlyAddMonster(i);
			//再慢慢隨機產生
			while(monsters.size() > 0){
				try {TimeUnit.SECONDS.sleep(15);}catch (InterruptedException e) {e.printStackTrace();}
				randomlyAddMonster();
				if (monsters.size() > 0)
					randomlyAddMonster();
				if (monsters.size() > 0)
					randomlyAddMonster();
			}
			
			while ( controller.getRemainningMonster() > 1 ); //只要還有一隻怪物就不繼續
			
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
