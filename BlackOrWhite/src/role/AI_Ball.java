package role;

import mvc.ImageSequence;
import role.abstractFactory.RoleFactory;

public class AI_Ball extends AI {
	
	private static ImageSequence[][] actImgs; //singleton ! lazy instantiation

	public AI_Ball(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Ball(RoleFactory factory ,int x , int y) {
		super(factory,x,y);
	}

	@Override
	protected void die() {
		
		
	}

}
