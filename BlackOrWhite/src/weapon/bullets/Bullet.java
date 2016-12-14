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


public abstract class Bullet {
	public static ArrayList<Integer> BARRIER_X_SET = Map1Director.BULLET_BARRIER_X_SET;  //��ê���y�ж��X
	public static ArrayList<Integer> BARRIER_Y_SET = Map1Director.BULLET_BARRIER_Y_SET;
	protected Model model;
	protected boolean singleHit; //�O�_����Ƨ��� (�_�h���s����� ���|�]���I��Ǫ��Ӯ���)
	protected boolean playerBullet;  //�O�_�����a���l�u �p�G�O �h�N�� �|�ˮ`��Ǫ� �A �Y���_ �h�N��O�Ǫ��}�窺�l�u �|�ˮ`�쪱�a 

	protected ImageSequence[][] actionImgs;
	protected int distance; //����Z��
	protected int damage;
	//�غc�l

	private int vW;  // vertical ��������
	private int vH;  // �����e�� (�ҥH��V���� ����,�e�׭n���)
	

	protected int cX;  //�y��
	protected int cY;
	public Dir curDir;  //�����V

	public Bullet(int cX, int cY, int w, int h,int damage,boolean singleHit,boolean playerBullet, Dir curDir ,BulletFactory factory) {
		super();
		this.cX = cX;
		this.cY = cY;
		this.singleHit = singleHit;
		this.playerBullet = playerBullet;
		this.vW = w;
		this.vH = h;
		this.curDir = curDir;
		this.damage = damage;
		this.actionImgs = factory.getActionImages();
		this.distance = factory.getDistance();
		model = new Model(this, Item.BULLET, cX,cY, ActionType.WALK , curDir, actionImgs[0][curDir.ordinal()]);
	}
	
	public void run() {
		flying();
		if(!flyable())  //���୸�F  �R��
			endFlying();
	}
	
	public void flying(){
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
		cX = model.getcX();  //��s�y��
		cY = model.getcY();
		model.setState(dX, dY, ActionType.WALK , curDir, iS);  
	}
	
	public boolean flyable(){
		//��ê���P�_�l�u�O�_���~�򭸦�A�٬O����
		return ! ( outOfBound(cX,cY) | conflictWithBarrier(cX,cY));
	}
	
	public boolean outOfBound(int x, int y){
		//�P�_�O�_�X��
		return x < -5 || y < -5 || x > MapBuilder.SIZEX*100+10 || y > MapBuilder.SIZEY*100+10;
	}
	public boolean conflictWithBarrier(int x , int y){
		int w = curDir == Dir.NORTH || curDir == Dir.SOUTH ? vW : vH;  //����
		int h = curDir == Dir.EAST || curDir == Dir.WEST ? vW : vH;  //���� �e���ۤ�
		//�P�_�O�_�����ê��
		Boolean conflict = false;
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (x+w >= (BARRIER_X_SET.get(i)+18) && x <= BARRIER_X_SET.get(i) + 92) 
			&& (y+h >= (BARRIER_Y_SET.get(i)+18) && y <= BARRIER_Y_SET.get(i) + 92) )
				conflict = true;
		return conflict;
	}
	
	public void endFlying(){
		/*���������n�����Ʊ�
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

	public int getvW() {
		return vW;
	}

	public int getvH() {
		return vH;
	}

	public boolean isSingleHit() {
		return singleHit;
	}

	public boolean isPlayerBullet() {
		return playerBullet;
	}
	
	
}
