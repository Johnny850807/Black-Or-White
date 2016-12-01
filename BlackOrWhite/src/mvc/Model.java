package mvc;

public class Model {
	private Controller controller; //更新之後要通知controller
	public int cX;
	public int cY;
	public ActionType act;
	public Dir dir;
	public ImageSequence iS;  //目前的分鏡動作
	
	public Model(int cX,int cY, ActionType act,Dir dir ,ImageSequence iS){
		this.cX = cX;
		this.cY = cY;
		this.act = act;
		this.dir = dir;
		this.iS = iS;
	}

	
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
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
	
	
}
