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
	public static ArrayList<Integer> BARRIER_X_SET = Map1Director.BARRIER_X_SET;  //��ê���y�ж��X
	public static ArrayList<Integer> BARRIER_Y_SET = Map1Director.BARRIER_Y_SET;
	protected Model model;
	//�u�t
	protected BulletFlying bulletFly;
	protected ImageSequence[][] actionImgs;
	//�غc�l
	private int damage;
	private int vW;  // vertical ��������
	private int vH;  // �����e�� (�ҥH��V���� ����,�e�׭n���)

	protected int cX;  //�_�I�y��
	protected int cY;
	public Dir curDir;  //�����V

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
		//�q����strategy�����o�l�u�C������Z��
		int distance = bulletFly.getFlyingDistance();
		//�M��ھڤ��P��V��s�y�� �٭n�P�_��ê��
		int dX = 0,dY = 0; //�첾
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
		/*��ê���P�_�l�u�O�_���~�򭸦�A�٬O����
		 * */

		int w = curDir == Dir.NORTH || curDir == Dir.SOUTH ? vW : vH;  //����
		int h = curDir == Dir.EAST || curDir == Dir.WEST ? vW : vH;  //���� �e���ۤ�
		//�P�_�O�_�I���ê��
		if ( cX < 0 || cY < 0 || cX > MapBuilder.SIZEX*100 || cY > MapBuilder.SIZEY*100 ){
			Log.d("�W�X�ɽu");
			return false;
		}
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (cX+w >= BARRIER_X_SET.get(i) && cX <= BARRIER_X_SET.get(i) + 100) 
			&& (cY+h >= BARRIER_Y_SET.get(i) && cY <= BARRIER_Y_SET.get(i) + 100) ){
				Log.d("�����ê��"+BARRIER_X_SET.get(i)+","+BARRIER_Y_SET.get(i)+",�l�u:"+cX+","+cY);
				return false;
			}
		return true;
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
