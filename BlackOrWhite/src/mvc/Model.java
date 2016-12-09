package mvc;

import role.Role;
import weapon.bullets.Bullet;

public class Model {
	private Controller controller = Controller.getController(); //��s����n�q��controller
	private Item type;  //�O�l�u�٬O����
	private int cX;
	private int cY;
	private ActionType act;
	private Dir dir;
	private ImageSequence iS;  //�ثe������ʧ@
	private Object parent;  //�O���־֦��o��model
	
	public Model(Object parent,Item type,int cX,int cY, ActionType act,Dir dir ,ImageSequence iS){
		this.parent = parent;
		this.type = type;
		this.cX = cX;
		this.cY = cY;
		this.act = act;
		this.dir = dir;
		this.iS = iS;
		if (type == Item.BULLET)
			controller.addBullet((Bullet)parent);
	}

	public void setState(int deltaX,int deltaY, ActionType act,Dir dir ,ImageSequence iS){
		this.cX += deltaX;
		this.cY += deltaY;
		this.act = act;
		this.dir = dir;
		this.iS = iS;
	/*	if ( type == Item.ROLE )
			controller.updateModel((Role)parent);
		else
			controller.updateModel((Bullet)parent);*/
	}
	
	//���o��model�bcontroller�������ͩR�P��
	public void delete(){
		if ( type == Item.ROLE )
			controller.deleteModel((Role)parent);
		else
			controller.deleteModel((Bullet)parent);
	}
	
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public ActionType getAct() {
		return act;
	}
	
	public Dir getDir() {
		return dir;
	}

	public ImageSequence getiS() {
		return iS;
	}

	public Object getParent() {
		if (type == Item.ROLE)
			return (Role)parent;
		return (Bullet)parent;
	}

	public int getcX() {
		return cX;
	}

	public int getcY() {
		return cY;
	}

	
}
