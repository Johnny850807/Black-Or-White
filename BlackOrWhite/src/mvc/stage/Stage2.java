package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import mvc.SoundManager;
import role.*;

public class Stage2 extends Stage {

	public Stage2(Controller controller) {
		super(controller);
		for ( int i = 0 ; i < 3 ; i ++ )  
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3 ; i ++ )  
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3 ; i ++ )  
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
	}

	@Override
	public void playMusic() {
	}

	@Override
	public void playerControl() {

	}



}
