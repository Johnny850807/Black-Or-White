package role;

import role.abstractFactory.RoleFactory;

public class AI extends Role {

	public AI(RoleFactory factory) {
		super(factory);
	}
	public AI(RoleFactory factory,int x ,int y){
		super(factory,x,y);
	}
}
