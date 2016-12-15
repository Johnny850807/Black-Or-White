package mvc.stage;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import role.AI;
import role.AI_Ball;
import role.AI_SnowBall;
import role.AI_SnowBallEX;

public class Stage1 extends Stage {
	public Stage1(Controller controller) {
		super(controller);
		for ( int i = 0 ; i < 10 ; i ++ )  //²Ä¤@Ãö10°¦
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
		
	}

	@Override
	public void playMusic() {
		SoundManager.getSoundManager().playStageMusic("sounds/stage/naruto_soundtrack.wav");
	}

	@Override
	public void playerControl() {
	}
}
