package role.movements;

import mvc.ActionType;
import role.AI;

public class AI_Halt extends AI_Decorator {

	public AI_Halt(AI_Movement wrapped) {
		super(wrapped);
	}

	@Override
	public void randomChoose(AI ai) {
		// 20% to halt about a while ...
		
		if ( random.nextInt(100) >= 80 && ai.isTimeToChangeMove() )
				ai.getMoved( ActionType.HALT , dir[random.nextInt(dir.length)]);
		else
			movement.randomChoose(ai);
	}
	


}
