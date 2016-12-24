package mvc.stage;

import mvc.Controller;

public class StageFactory {
	public static Stage createAllStages(Controller controller){
		Stage stage1 = new Stage1(controller);
		Stage stage2 = new Stage2(controller);
		 Stage stage3 = new Stage3(controller);
		  Stage stage4 = new Stage4(controller);
		  Stage stage5 = new Stage5(controller);
		  Stage bossStage = new BossStage(controller);
		stage1.setNextStage(stage2);
		  stage2.setNextStage(stage3);
		  stage3.setNextStage(stage4);
		  stage4.setNextStage(stage5);
		  stage5.setNextStage(bossStage);
		return bossStage;
	}
}
