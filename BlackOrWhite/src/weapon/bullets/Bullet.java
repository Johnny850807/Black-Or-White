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
import weapon.bulletflying.BulletFlying;

public abstract class Bullet implements Runnable{
	public static ArrayList<Integer> BARRIER_X_SET = Map1Director.BARRIER_X_SET;  //障礙物座標集合
	public static ArrayList<Integer> BARRIER_Y_SET = Map1Director.BARRIER_Y_SET;
	protected Model model;
	//工廠
	protected BulletFlying bulletFly;
	protected ImageSequence[][] actionImgs;
	//建構子
	private int damage;
	private int vW;  // vertical 垂直高度
	private int vH;  // 垂直寬度 (所以橫向的話 高度,寬度要對調)

	protected int cX;  //起點座標
	protected int cY;
	public Dir curDir;  //飛行方向

	public Bullet(int cX, int cY, int w, int h,int damage, Dir curDir ,BulletFactory factory) {
		super();
		this.cX = cX;
		this.cY = cY;
		this.vW = w;
		this.vH = h;
		this.curDir = curDir;
		this.damage = damage;
		this.bulletFly = factory.getBulletFlying();
		this.actionImgs = factory.getActionImages();
		model = new Model(this, Item.BULLET, cX,cY, ActionType.WALK , curDir, actionImgs[0][curDir.ordinal()]);
	}
	
	public void flying(){
		//從飛行strategy中取得子彈每次飛行距離
		int distance = bulletFly.getFlyingDistance();
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
	}
	
	public boolean flyable(){
		/*障礙物判斷子彈是否能繼續飛行，還是消失
		 * */

		int w = curDir == Dir.NORTH || curDir == Dir.SOUTH ? vW : vH;  //垂直
		int h = curDir == Dir.EAST || curDir == Dir.WEST ? vW : vH;  //水平 寬高相反
		//判斷是否碰到障礙物
		if ( cX < 0 || cY < 0 || cX > MapBuilder.SIZEX*100 || cY > MapBuilder.SIZEY*100 ){
			Log.d("超出界線");
			return false;
		}
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (cX+w >= BARRIER_X_SET.get(i) && cX <= BARRIER_X_SET.get(i) + 100) 
			&& (cY+h >= BARRIER_Y_SET.get(i) && cY <= BARRIER_Y_SET.get(i) + 100) ){
				Log.d("撞到障礙物"+BARRIER_X_SET.get(i)+","+BARRIER_Y_SET.get(i)+",子彈:"+cX+","+cY);
				return false;
			}
		return true;
	}
	
	public Bullet endFlying(){
		/*結束飛行後要做的事情
		 * 
		 * */
		return null;
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
	public BulletFlying getBulletFly() {
		return bulletFly;
	}
	public void setBulletFly(BulletFlying bulletFly) {
		this.bulletFly = bulletFly;
	}
	
	
}
