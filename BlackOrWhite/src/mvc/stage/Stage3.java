package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import role.AI_Ball;
import role.AI_SnowBall;

public class Stage3 extends Stage{

	public Stage3(Controller controller) {
		super(controller);
		for ( int i = 0 ; i < 11 ; i ++ )  //²Ä¤@Ãö10°¦
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		
	}
	@Override
	public void playMusic() {	
	}
	@Override
	public void playerControl() {
		controller.curePlayer(30);
	}

}
