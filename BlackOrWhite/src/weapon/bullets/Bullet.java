package weapon.bullets;

import mvc.Dir;
import mvc.Model;
import weapon.bulletflying.BulletFlying;

public abstract class Bullet {
	private Model model;
	public int cX;  //�_�I�y��
	public int cY;
	private int damage;
	public Dir dir;  //�����V
	private BulletFlying bulletFly;
	
	
	public Bullet(int cX, int cY, int damage, BulletFlying bulletFly) {
		super();
		this.cX = cX;
		this.cY = cY;
		this.damage = damage;
		this.bulletFly = bulletFly;
	}
	
	public void flying(){
		//�q����strategy�����o�l�u�C������Z��
		int distance = bulletFly.getFlyingDistance();
		//�M��ھڤ��P��V��s�y�� �٭n�P�_��ê��
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
		/*��ê���P�_�l�u�O�_���~�򭸦�A�٬O����
		 * 
		 * */
		return false;
	}
	
	public Bullet endFlying(){
		/*���������n�����Ʊ�
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
