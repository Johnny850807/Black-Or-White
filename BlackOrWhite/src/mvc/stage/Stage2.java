package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import mvc.SoundManager;
import role.AI;
import role.AI_Ball;

public class Stage2 extends Stage {

	public Stage2(Controller controller) {
		super(controller);
		for ( int i = 0 ; i < 15 ; i ++ )  //第二關15隻
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
	}

	@Override
	public void playMusic() {
		//不播音樂
	}



}
