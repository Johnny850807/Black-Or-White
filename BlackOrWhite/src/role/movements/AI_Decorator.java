package role.movements;

import java.util.Random;

import role.AI;

public abstract class AI_Decorator implements AI_Movement {
	protected AI_Movement movement; // decorated object
	protected Random random = new Random(); //the random object let subclass use conveniently
	
	public AI_Decorator(AI_Movement wrapped){
		this.movement = wrapped;
	}
	
	public abstract void randomChoose(AI ai);

}
