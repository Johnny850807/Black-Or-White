package role;

import role.abstractFactory.RoleFactory;

public abstract class AI extends Role {

	public AI(RoleFactory factory) {
		super(factory);
	}
	
	public AI(RoleFactory factory,int x ,int y){
		super(factory,x,y);
	}
	
	
	protected abstract void die(); // do something while dying , maybe throws a gun over the map
}
