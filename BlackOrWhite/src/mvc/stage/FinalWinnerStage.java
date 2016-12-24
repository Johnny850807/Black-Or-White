package mvc.stage;

import mvc.Controller;

public class FinalWinnerStage extends Stage{

	public FinalWinnerStage(Controller controller) {
		super(controller, 1000);
	}

	@Override
	public void playMusic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerControl() {
		// TODO Auto-generated method stub
		controller.showGameFinalWinMessage();
	}

}
