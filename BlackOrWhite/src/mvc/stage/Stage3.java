package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import role.AI_Ball;
import role.AI_SnowBall;
import role.AI_SnowBallEX;

public class Stage3 extends Stage{

	public Stage3(Controller controller) {
		super(controller);
		
		monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2 ; i ++ )  
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 3 ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		
	}
	@Override
	public void playMusic() {	
	}
	@Override
	public void playerControl() {
		controller.curePlayer(30);
	}

}
