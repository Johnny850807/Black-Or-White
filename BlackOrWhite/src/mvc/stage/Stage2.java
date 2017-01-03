package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import mvc.SoundManager;
import mvc.View;
import role.*;

public class Stage2 extends Stage {

	public Stage2(Controller controller) {
		super(controller,7);
		int rate = View.crazyMode ? 2 : 1;
		for ( int i = 0 ; i < 3*rate ; i ++ )  
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2*rate ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3*rate ; i ++ )  
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3*rate ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3*rate ; i ++ )  
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3*rate ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		
		if(Controller.isNetWork()){
			float frate = 0.7f*rate;
			for ( int i = 0 ; i < 3*frate ; i ++ )  
				monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 2*frate ; i ++ )  
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 2*frate ; i ++ )  
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 3*frate ; i ++ )  
				monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 2*frate ; i ++ )  
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 3*frate ; i ++ )  
				monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		}
	}

	@Override
	public void playMusic() {
	}

	@Override
	public void playerControl() {

	}



}
