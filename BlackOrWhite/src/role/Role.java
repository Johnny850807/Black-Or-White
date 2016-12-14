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
	public static ArrayList<Integer> BARRIER_X_SET = Map1Director.BARRIER_X_SET;  //��ê���y�ж��X
	public static ArrayList<Integer> BARRIER_Y_SET = Map1Director.BARRIER_Y_SET;
	//�u�t�Ͳ�
	protected int hp;  //�ͩR
	protected ImageSequence[][] actionImgs;
	protected Backable backable; //����ˮ`�᪺�欰
	protected RoleFactory factory; //��H����u�t
	protected Gun gun;  //����֦����j �Y�S����null
	protected AI_Movement movement; //��ܰʧ@ ���a���ܼƬ�null
	//�ݭn�l���O�ۤv�w�q��
	protected int atk;  //�����O
	protected int df;  //���m�O
	protected int offsetX;  //�o�ӬO�P�wplayer���}�b�����t��
	protected int offsetY;  //�~��P�_�O�_�੹�e��
	protected int feetW;  //�}���e�� (�P�_����)
	protected int feetH;  //�}������
	//�غc�l�Ѽ�
	public int x;
	public int y;
	public ActionType curAct;
	public Dir curDir;
	public boolean isDead = false; //�P�_�O�_���`  �Y���`�n�Ұʦ��`�ͩR�g��
	public boolean isShootSpacing = false; //�g�����Z�O�_�w�Ĥ�
	public boolean isBeingHurted = false;  //�O�_�Q�ˮ`��(�קK�s�����)
	
	//���a��J���R�O
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
		//�p�G�O���a �]�m�ͩR����n��s�ͩR��
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
	
	//�u������ʧ@ �Ǧ^�O�_�i����
	public boolean getMoved(ActionType act , Dir dir){
		curAct = act;
		curDir = dir;
		int dX = 0,dY = 0; //�첾
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
				ShootSpacing sh = new ShootSpacing(this);  //�g������
				sh.start();
				
				break;
		}
		x = model.getcX();
		y = model.getcY();
		int rX = x + offsetX + dX, rY = y + offsetY + dY;  //���G��l
		if (!moveable(rX,rY)){ //�Y���i���ʫh�첾0
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
		//�P�_�}��O�_�i�H�~�򲾰�
		return !(outOfBound(x,y) || conflictWithBarrier(x,y));
	}
	public boolean outOfBound(int x, int y){
		//�P�_�O�_�X��
		return x < -5 || y < -5 || x > MapBuilder.SIZEX*100 - 40 || y > MapBuilder.SIZEY*100;
	}
	public boolean conflictWithSomething(int oX , int oY , int oW , int oH){
		//�P�_�O�_����Y�ӪF��
		Boolean conflict = false;
			if ( (x+offsetX+feetW >= oX && x <= oX + oW) 
			&& (y+offsetY+feetH >= oY && y <= oY + oH) )
				conflict = true;
		return conflict;
	}
	public boolean conflictWithBarrier(int x , int y){
		//�P�_�O�_�����ê��
		Boolean conflict = false;
		for ( int i = 0 ; i < BARRIER_X_SET.size() ; i ++ )
			if ( (x+feetW >= BARRIER_X_SET.get(i) && x <= BARRIER_X_SET.get(i) + 100) 
			&& (y+feetH >= BARRIER_Y_SET.get(i) && y <= BARRIER_Y_SET.get(i) + 100) )
				conflict = true;
		return conflict;
	}
	public void getDamaged(Bullet bullet){
		//�H�����l�u �u���Ǫ��~�|�������
		if ( this instanceof AI && !isBeingHurted ){
			Log.d(bullet.getDamage()+"");
			hp = (hp - (bullet.getDamage() - df)) < 0 ? 0 : (hp - (bullet.getDamage() - df));
			Log.d("�������"+x+","+y);
			updateHp();
			bullet.getModel().delete();
		}
		else //�Ǫ��l�u
			;
		
	}
	/*public void getDamaged(AI_Bullet bullet){
		//�Ǫ����l�u
		if ( this instanceof Player)
			hp = (hp - (bullet.getDamage() - df)) < 0 ? 0 : (hp - (bullet.getDamage() - df));
		updateHp();
	}*/
	public void getDamaged(AI ai){
		//����Ǫ���Ĳ����
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
	protected abstract int getMovingDistance(ActionType act , Dir dir);  //�^�ǸӨ���C�����ʶZ��
	
	
	
}
