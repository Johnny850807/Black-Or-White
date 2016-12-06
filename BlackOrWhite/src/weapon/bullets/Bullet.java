package weapon.bullets;

import java.util.ArrayList;

import mvc.ActionType;
import mvc.Dir;
import mvc.ImageSequence;
import mvc.Item;
import mvc.Log;
import mvc.Map1Director;
import mvc.MapBuilder;
import mvc.Model;
import role.Role;


public abstract class Bullet implements Runnable{
	public static ArrayList<Integer> BARRIER_X_SET = Map1Director.BULLET_BARRIER_X_SET;  //障礙物座標集合
	public static ArrayList<Integer> BARRIER_Y_SET = Map1Director.BULLET_BARRIER_Y_SET;
	protected Model model;

	protected ImageSequence[][] actionImgs;
	protected int distance; //飛行距離
	protected int damage;
	//建構子

	private int vW;  // vertical 垂直高度
	private int vH;  // 垂直寬度 (所以橫向的話 高度,寬度要對調)
	

	protected int cX;  //座標
	protected int cY;
	public Dir curDir;  //飛行方向

	public Bullet(int cX, int cY, int w, int h,int damage, Dir curDir ,BulletFactory factory) {
		super();
		this.cX = cX;
		this.cY = cY;
		this.vW = w;
		this.vH = h;
		this.curDir = curDir;
		this.damage = factory.getDamage();
		this.actionImgs = factory.getActionImages();
		this.distance = factory.getDistance();
		model = new Model(this, Item.BULLET, cX,cY, ActionType.WALK , curDir, actionImgs[0][curDir.ordinal()]);
	}
	
	@Override
	public void run() {
		Log.d("Bullet Run!");
		while(true){
			flying();
			if(!flyable())  //不能飛了  刪掉
			{
				Log.d("end flying");
				endFlying();
				break;
			}
			try {Thread.sleep(50);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public void flying(){
		//然後根據不同方向更新座標 還要判斷障礙物
		int dX = 0,dY = 0; //位移
		ImageSequence iS = model.getiS();
		switch(curDir)
		{
			case NORTH:
				dY = distance*(-1);
				break;
			case SOUTH:
				dY = distance;
				break;
			case EAST:
				dX = distance;
				break;
			case WEST:
				dX = distance*(-1);
				break;
		}

		iS = actionImgs[0][curDir.ordinal()];
		model.setState(dX, dY, ActionType.WALK , curDir, iS);  
		cX = model.getcX();  //更新座標
		cY = model.getcY();
	}
	
	public boolean flyable(){
		//障礙物判斷子彈是否能繼續飛行，還是消失
		return ! ( outOfBound(cX,cY) | conflictWithBarrier(cX,cY));
	}
	
	public boolean outOfBound(int x, int y){
		//判斷是否出界
		return x < -5 || y < -5 || x > MapBuilder.SIZEX*100+10 || y > MapBuilder.SIZEY*100+10;
	}
	public boolean conflictWithBarrier(int x , int y){
		int w = curDir == Dir.NORTH || curDir == Dir.SOUTH ? vW : vH;  //垂直
		int h = curDir == Dir.EAST || curDir == Dir.WEST ? vW : vH;  //水平 寬高相反
		//判斷是否撞到障礙物
		Boolean conflict = false;
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (x+w >= (BARRIER_X_SET.get(i)+18) && x <= BARRIER_X_SET.get(i) + 92) 
			&& (y+h >= (BARRIER_Y_SET.get(i)+18) && y <= BARRIER_Y_SET.get(i) + 92) )
				conflict = true;
		return conflict;
	}
	
	public void endFlying(){
		/*結束飛行後要做的事情
		 * 
		 * */
		model.delete();
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getcX() {
		return cX;
	}
	public void setcX(int cX) {
		this.cX = cX;
	}
	public int getcY() {
		return cY;
	}
	public void setcY(int cY) {
		this.cY = cY;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
}
