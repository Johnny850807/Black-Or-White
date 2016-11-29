package weapon.bullets;

import mvc.Dir;
import mvc.Model;
import weapon.bulletflying.BulletFlying;

public abstract class Bullet {
	private Model model;
	public int cX;  //起點座標
	public int cY;
	private int damage;
	public Dir dir;  //飛行方向
	private BulletFlying bulletFly;
	
	
	public Bullet(int cX, int cY, int damage, BulletFlying bulletFly) {
		super();
		this.cX = cX;
		this.cY = cY;
		this.damage = damage;
		this.bulletFly = bulletFly;
	}
	
	public void flying(){
		//從飛行strategy中取得子彈每次飛行距離
		int distance = bulletFly.getFlyingDistance();
		//然後根據不同方向更新座標 還要判斷障礙物
		switch(dir){
		case NORTH:
			cY = cY - distance;
			break;
		case EAST:	
			cX = cX + distance;
			break;
		case SOUTH:
			cY = cY + distance;
			break;
		case WEST:	
			cX = cX - distance;
		}
	}
	
	public boolean flyable(){
		/*障礙物判斷子彈是否能繼續飛行，還是消失
		 * 
		 * */
		return false;
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
