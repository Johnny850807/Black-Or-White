package role.movements;

import role.AI;

public class AI_Halt extends AI_Decorator {

	public AI_Halt(AI_Movement wrapped) {
		super(wrapped);
	}

	@Override
	public void randomChoose(AI ai) {
		// 40% to halt about a while ...
		if ( random.nextInt(100) >= 60 )
			halt();
		else
			movement.randomChoose(ai);
	}
	
	public void halt(){
		// halt algorithm
	}

}
