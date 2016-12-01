package mvc;

import role.Role;

public class Model {
	private Controller controller = Controller.getController(); //��s����n�q��controller
	private int cX;
	private int cY;
	private ActionType act;
	private Dir dir;
	private ImageSequence iS;  //�ثe������ʧ@
	private Role parent;  //�O���־֦��o��model
	
	public Model(Role parent,int cX,int cY, ActionType act,Dir dir ,ImageSequence iS){
		this.parent = parent;
		this.cX = cX;
		this.cY = cY;
		this.act = act;
		this.dir = dir;
		this.iS = iS;
	}

	public void setState(int deltaX,int deltaY, ActionType act,Dir dir ,ImageSequence iS){
		this.cX += deltaX;
		this.cY += deltaY;
		this.act = act;
		this.dir = dir;
		this.iS = iS;
		controller.updateModel(parent);
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

	public Role getParent() {
		return parent;
	}

	public int getcX() {
		return cX;
	}

	public int getcY() {
		return cY;
	}

	
}
