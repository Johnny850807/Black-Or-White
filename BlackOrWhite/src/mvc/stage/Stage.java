package mvc.stage;

import mvc.Controller;

public abstract class Stage implements Runnable{
	protected Stage nextStage;
	protected Controller controller;
	public Stage(Controller controller){
		this.controller = controller;
	}
	public Stage getNextStage() {
		return nextStage;
	}
	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
