package role;

import role.abstractFactory.RoleFactory;

public class AI_Ball extends AI {

	public AI_Ball(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Ball(RoleFactory factory ,int x , int y) {
		super(factory,x,y);
	}

}
