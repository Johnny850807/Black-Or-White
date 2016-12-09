package role.movements;

import java.util.Random;

import mvc.ActionType;
import mvc.Dir;
import role.AI;

public class AI_Walk implements AI_Movement {
	@Override
	public void randomChoose(AI ai) {
		// the Final choice --> to Walk !!
		// Walk on the specific direction about a while ...
		if (ai.isTimeToChangeMove())
			ai.getMoved( ActionType.WALK , dir[random.nextInt(dir.length)]);
		else
			ai.keepCurrentMove();
		ai.moveDurationCountDown();
	}

}
