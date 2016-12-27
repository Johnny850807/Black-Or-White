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
		playerControl(); //�ާ@���a
		playMusic(); //���񭵼�
		
		//�����ͩǪ��b�S�w�ϰ�
		for(int i = 0 ; i < Map1Director.AI_CREATE_X_SET.length && i < monsters.size() ; i ++ )
			specificlyAddMonster(i);
		int time;
		
		//�x���Ҧ����� �Ͳ��ɶ����1/2
		if(View.crazyMode)
			spacingCreation *= 0.5;
		
		//�A�C�C�H����m����
		while(monsters.size() > 0){
			try {TimeUnit.SECONDS.sleep(spacingCreation);}catch (InterruptedException e) {e.printStackTrace();}
			for ( int i = 0 ; i < 4 && monsters.size() > 0 ; i ++ )
				randomlyAddMonster();
		}
			
		while ( controller.getRemainningMonster() > 0 ); //�u�n�٦��@���Ǫ��N���~��

		try {TimeUnit.SECONDS.sleep(5);}catch (InterruptedException e) {e.printStackTrace();}
			
		Log.d("�U�@���}�l!!");
		new Thread(nextStage).start();  //�U�@��
	} 
	
	public void specificlyAddMonster(int siteIndex){
		//���w��m���ͩǪ�
		int x = Map1Director.AI_CREATE_X_SET[siteIndex];
		int y = Map1Director.AI_CREATE_Y_SET[siteIndex];
		AI monster = monsters.remove(0);
		monster.setDimension(new Dimension(x,y));
		controller.addMonster(monster);
	}
	
	public void randomlyAddMonster(){
		//�H�����ͤ@���Ǫ����{��
		AI monster = monsters.remove(0);
		monster.setDimension(getRandomCreateXY());
		controller.addMonster(monster);
	}
	
	public Dimension getRandomCreateXY(){
		//�H�����ͧ@��
		Random random = new Random();
		int index = random.nextInt(Map1Director.AI_CREATE_X_SET.length);
		int x = Map1Director.AI_CREATE_X_SET[index];
		int y = Map1Director.AI_CREATE_Y_SET[index];	
		Dimension d = new Dimension(x,y);
		return new Dimension(x,y); 
	}
	
	public abstract void playMusic();  //�O�_�󴫭���
	public abstract void playerControl();  //�O�_�i��ާ@���a �]�\�v�� �]�\����..
	
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
