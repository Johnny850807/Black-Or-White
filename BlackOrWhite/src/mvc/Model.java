package mvc;

public class Model {
	private Controller controller; //��s����n�q��controller
	public int cX;
	public int cY;
	public ActionType act;
	public Dir dir;
	public ImageSequence iS;  //�ثe������ʧ@
	
	

	
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
