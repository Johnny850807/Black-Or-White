package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.View;
import role.AI_Ball;
import role.AI_SnowBall;
import role.AI_SnowBallEX;

public class Stage3 extends Stage{

	public Stage3(Controller controller) {
		super(controller,8);
		int rate = View.crazyMode ? 2 : 1;
		for ( int i = 0 ; i < 1*rate ; i ++ ) 
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2*rate ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 1*rate ; i ++ ) 
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2*rate ; i ++ )  
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3*rate ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 1*rate ; i ++ ) 
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		if(Controller.isNetWork()){
			float frate = 0.7f*rate;
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 4*frate ; i ++ )  
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 1*frate ; i ++ ) 
				monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 2*frate ; i ++ )  
				monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 3*frate ; i ++ )  
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		}
	}
	@Override
	public void playMusic() {	
		if(View.crazyMode)
			SoundManager.getSoundManager().playStageMusic("sounds/stage/japan.wav");
		else
		SoundManager.getSoundManager().playStageMusic("sounds/stage/stage1_ost.wav");
	}
	@Override
	public void playerControl() {
		controller.curePlayer(30);
	}

}
