package mvc.stage;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import mvc.SoundManager;
import mvc.View;
import role.AI;
import role.AI_Ball;
import role.AI_CrazyCat;
import role.AI_Evil;
import role.AI_RifleTank;
import role.AI_SniperTank;
import role.AI_SnowBall;
import role.AI_SnowBallEX;

public class Stage1 extends Stage {
	public Stage1(Controller controller) {
		super(controller,9);
		int amount = View.crazyMode  ? 20 : 10;
		if(View.crazyMode)
			Log.d("困難模式");
		for ( int i = 0 ; i < amount ; i ++ )  //第一關10隻
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
