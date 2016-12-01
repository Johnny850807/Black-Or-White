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
	
	//�u������ʧ@
	protected void getMoved(ActionType act , Dir dir){
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
			try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
				break;
		}
		x = model.getcX();
		y = model.getcY();
		int rX = x + offsetX + dX, rY = y + offsetY + dY;  //���G��l
		Boolean conflict = false; //�����ê��
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
	
	abstract int getMovingDistance(ActionType act , Dir dir);  //�^�ǸӨ���C�����ʶZ��
	
	
	
}
