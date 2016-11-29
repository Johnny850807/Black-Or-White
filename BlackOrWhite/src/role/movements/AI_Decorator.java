package role.movements;

import role.AI;

public abstract class AI_Decorator implements AI_Movement {
	protected AI_Movement movement; // decorated object
	
	public AI_Decorator(AI_Movement wrapped){
		this.movement = wrapped;
	}
	
	public abstract void randomChoose(AI ai);

}
