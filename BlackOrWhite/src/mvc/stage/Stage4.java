package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import role.*;

public class Stage4 extends Stage{
	public Stage4(Controller controller) {
		super(controller,19);
		for ( int i = 0 ; i < 2 ; i ++ ) 
			monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ ) 
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));
		monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ ) 
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
	}

	@Override
	public void playMusic() {
		SoundManager.getSoundManager().playStageMusic("sounds/stage/stage1_ost.wav");
	}

	@Override
	public void playerControl() {
		controller.curePlayer(150);
	}

	
}
