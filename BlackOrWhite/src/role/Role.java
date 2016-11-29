package role;

import mvc.*;
import role.abstractFactory.RoleFactory;
import role.movements.AI_Movement;
import role.movements.Backable;
import weapon.guns.Gun;

public abstract class Role {
	private Model model;
	private ImageSequence[][] actionImgs;
	private Backable backable; //����ˮ`�᪺�欰
	private RoleFactory factory; //��H����u�t
	private Gun gun;  //����֦����j �Y�S����null
	private AI_Movement movement; //��ܰʧ@ ���a���ܼƬ�null
	public int x;
	public int y;
	public int hp;  //�ͩR
	public int df;  //���m�O
	public int atk;  //�Ǫ���Ĳ�I���� �Y���a�h��0
	public ActionType curAct;
	public Dir curDir;
	
	public Role(RoleFactory factory){
		this.factory = factory;
		
	}

	
	//���a��J���R�O
	protected class Request{
		public ActionType act;
		public Dir dir;
		public Request(ActionType act , Dir dir){
			this.act = act;
			this.dir = dir;
		}
	}
	
}
