package role;

import java.util.LinkedList;
import java.util.Queue;

import mvc.*;
import role.abstractFactory.RoleFactory;
import role.movements.AI_Movement;
import role.movements.Backable;
import weapon.guns.Gun;

public abstract class Role {
	protected Model model;
	protected ImageSequence[][] actionImgs;
	protected Backable backable; //����ˮ`�᪺�欰
	protected RoleFactory factory; //��H����u�t
	protected Gun gun;  //����֦����j �Y�S����null
	protected AI_Movement movement; //��ܰʧ@ ���a���ܼƬ�null
	protected Queue<Request> requests;
	public int x;
	public int y;
	public int hp;  //�ͩR
	public ActionType curAct;
	public Dir curDir;
	public boolean isDead; //�P�_�O�_���`  �Y���`�n�Ұʦ��`�ͩR�g��
	
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
		this.model = new Model(x,y,act,dir,actionImgs[curAct.ordinal()][curDir.ordinal()]);
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	protected void hurtedJudgement(){
		// hook method , do something while hurted.
		
	}
	
}
