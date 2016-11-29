package role;

import role.abstractFactory.RoleFactory;

public class Player extends Role {

	public Player(RoleFactory factory) {
		super(factory);
	}

	public Player(RoleFactory factory,int x ,int y){
		super(factory,x,y);
	}
	
}
