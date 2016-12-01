package role;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import mvc.*;
import role.abstractFactory.RoleFactory;
import role.movements.AI_Movement;
import role.movements.Backable;
import weapon.guns.Gun;

public abstract class Role {
	protected Model model;
	protected Queue<Request> requests;
	public static ArrayList<Integer> BARRIER_X_SET = Map1Director.BARRIER_X_SET;  //障礙物座標集合
	public static ArrayList<Integer> BARRIER_Y_SET = Map1Director.BARRIER_Y_SET;
	//工廠生產
	protected int hp;  //生命
	protected ImageSequence[][] actionImgs;
	protected Backable backable; //受到傷害後的行為
	protected RoleFactory factory; //抽象角色工廠
	protected Gun gun;  //角色擁有的槍 若沒有為null
	protected AI_Movement movement; //選擇動作 玩家此變數為null
	//需要子類別自己定義的
	protected int atk;  //攻擊力
	protected int df;  //防禦力
	protected int offsetX;  //這個是判定player的腳在那的差值
	protected int offsetY;  //才能判斷是否能往前走
	protected int feetW;  //腳的寬度 (判斷底位)
	protected int feetH;  //腳的高度
	//建構子參數
	public int x;
	public int y;
	public ActionType curAct;
	public Dir curDir;
	public boolean isDead = false; //判斷是否死亡  若死亡要啟動死亡生命週期
	
	//玩家輸入的命令
	protected class Request{
		public ActionType act;
		public Dir dir;
		public Request(ActionType act , Dir dir){
			this.act = act;
			this.dir = dir;
		}
	}
	
	public Role(RoleFactory factory){
		this.factory = factory;
		actionImgs = factory.getActionImages();
		backable = factory.getBackable();
		gun = factory.getGun();
		movement = factory.getMovement();
		hp = factory.getHp();
		isDead = false;
		requests = new LinkedList(); // requests queue
	}
	
	public Role(RoleFactory factory, int x, int y , ActionType act , Dir dir) {
		this(factory);
		this.x = x;
		this.y = y;
		this.curAct = act;
		this.curDir = dir;
		this.model = new Model(this,Item.ROLE,x,y,act,dir,actionImgs[curAct.ordinal()][curDir.ordinal()]);
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	protected void hurtedJudgement(){
		// hook method , do something while hurted.
		
	}
	
	//真正執行動作
	protected void getMoved(ActionType act , Dir dir){
		curAct = act;
		curDir = dir;
		int dX = 0,dY = 0; //位移
		ImageSequence iS = model.getiS();

		switch(curAct)
		{
			case HALT:  
				break;
			case WALK:
				switch(curDir)
				{
					case NORTH:
					case SOUTH:
						dY = getMovingDistance(curAct,curDir);
						break;
					case EAST:
					case WEST:
						dX = getMovingDistance(curAct,curDir);
						break;
				}
				break;
			case SHOOT:
				gun.gunShooting(this);
			try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
				break;
		}
		x = model.getcX();
		y = model.getcY();
		int rX = x + offsetX + dX, rY = y + offsetY + dY;  //結果位子
		Boolean conflict = false; //撞到障礙物
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (rX+feetW >= BARRIER_X_SET.get(i) && rX <= BARRIER_X_SET.get(i) + 100) 
			&& (rY+feetH >= BARRIER_Y_SET.get(i) && rY <= BARRIER_Y_SET.get(i) + 100) )
				conflict = true;
		if (conflict)
			dX = dY = 0;
		iS = actionImgs[curAct.ordinal()][curDir.ordinal()];
		if ( curDir != dir )
			iS.reset();

		model.setState(dX, dY, curAct, curDir, iS);
	}
	
	abstract int getMovingDistance(ActionType act , Dir dir);  //回傳該角色每次移動距離
	
	
	
}
