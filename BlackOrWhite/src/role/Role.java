package role;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import mvc.*;
import role.movements.*;
import role.abstractFactory.RoleFactory;
import role.movements.AI_Movement;
import role.movements.Backable;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.guns.Gun;

public abstract class Role implements Runnable{
	protected Model model;
	protected volatile Queue<Request> requests;
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
	public boolean isShootSpacing = false; //射擊間距是否緩衝中
	public boolean isBeingHurted = false;  //是否被傷害中(避免連續受傷)
	
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
		isDead = false;
		requests = new LinkedList(); // requests queue
	}
	
	public Role(RoleFactory factory, int x, int y , ActionType act , Dir dir , 
			int offsetX , int offsetY , int feetW , int feetH , int hp , int atk , int df) {
		this(factory);
		this.x = x;
		this.y = y;
		this.curAct = act;
		this.curDir = dir;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.feetW = feetW;
		this.feetH = feetH;
		this.hp = hp;
		this.atk = atk;
		this.df = df;
		this.model = new Model(this,Item.ROLE,x,y,act,dir,actionImgs[curAct.ordinal()][curDir.ordinal()]);
	}
	
	public int getAtk() {
		return atk;
	}

	public int getDf() {
		return df;
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
		//如果是玩家 設置生命之後要更新生命條
		if(this instanceof Player)
			updateHp();
		
	}
	
	public boolean isShootSpacing() {
		return isShootSpacing;
	}

	public void setShootSpacing(boolean isShootSpacing) {
		this.isShootSpacing = isShootSpacing;
	}

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}
	public int getFeetW() {
		return feetW;
	}
	public int getFeetH() {
		return feetH;
	}

	protected void hurtedJudgement(){
		// hook method , do something while hurted.
		
	}
	
	//真正執行動作 傳回是否可移動
	public boolean getMoved(ActionType act , Dir dir){
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
				ShootSpacing sh = new ShootSpacing(this);  //射擊間格
				sh.start();
				
				break;
		}
		x = model.getcX();
		y = model.getcY();
		int rX = x + offsetX + dX, rY = y + offsetY + dY;  //結果位子
		if (!moveable(rX,rY)){ //若不可移動則位移0
			dX = dY = 0; 
			return false;
		}
		iS = actionImgs[curAct.ordinal()][curDir.ordinal()];
		if ( curDir != dir )
			iS.reset();
		model.setState(dX, dY, curAct, curDir, iS);
		return true;
	}
	public boolean moveable(int x , int y){
		//判斷腳色是否可以繼續移動
		return !(outOfBound(x,y) || conflictWithBarrier(x,y));
	}
	public boolean outOfBound(int x, int y){
		//判斷是否出界
		return x < -5 || y < -5 || x > MapBuilder.SIZEX*100 - 40 || y > MapBuilder.SIZEY*100;
	}
	public boolean conflictWithSomething(int oX , int oY , int oW , int oH){
		//判斷是否撞到某個東西
		Boolean conflict = false;
			if ( (x+offsetX+feetW >= oX && x <= oX + oW) 
			&& (y+offsetY+feetH >= oY && y <= oY + oH) )
				conflict = true;
		return conflict;
	}
	public boolean conflictWithBarrier(int x , int y){
		//判斷是否撞到障礙物
		Boolean conflict = false;
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (x+feetW >= BARRIER_X_SET.get(i) && x <= BARRIER_X_SET.get(i) + 100) 
			&& (y+feetH >= BARRIER_Y_SET.get(i) && y <= BARRIER_Y_SET.get(i) + 100) )
				conflict = true;
		return conflict;
	}
	public void getDamaged(Bullet bullet){
		//人類的子彈 只有怪物才會受到攻擊
		if ( this instanceof AI && !isBeingHurted ){
			Log.d(bullet.getDamage()+"");
			hp = (hp - (bullet.getDamage() - df)) < 0 ? 0 : (hp - (bullet.getDamage() - df));
			Log.d("受到攻擊"+x+","+y);
			updateHp();
			bullet.getModel().delete();
		}
		else //怪物子彈
			;
		
	}
	/*public void getDamaged(AI_Bullet bullet){
		//怪物的子彈
		if ( this instanceof Player)
			hp = (hp - (bullet.getDamage() - df)) < 0 ? 0 : (hp - (bullet.getDamage() - df));
		updateHp();
	}*/
	public void getDamaged(AI ai){
		//受到怪物接觸攻擊
		if ( this instanceof Player && !isBeingHurted){
			hp = (hp - (ai.getAtk() - df)) < 0 ? 0 : (hp - (ai.getAtk() - df));
			new BeingHurted(this,3.5f).start();
			backable.goBack(this);
			updateHp();
		}
	}
	
	public void updateHp(){
		model.setHp(hp);
	}
	protected abstract int getMovingDistance(ActionType act , Dir dir);  //回傳該角色每次移動距離
	
	
	
}
