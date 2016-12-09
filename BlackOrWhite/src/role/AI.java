package role;

import mvc.ActionType;
import mvc.Dir;
import role.abstractFactory.RoleFactory;

public abstract class AI extends Role {

	public AI(RoleFactory factory) {
		super(factory);
	}
	
	public AI(RoleFactory factory,int x ,int y , ActionType act , Dir dir){
		super(factory,x,y,act,dir);
	}
	
	@Override
	public void run(){
		/*Thread template method
		 * 1. check if hurted (hook)
		 * 2. choose the movement (final)
		 */
		while(!isDead){
			hurtedJudgement();
			chooseMovement();
		}
		
		die();
	}
	
	private final void chooseMovement(){
		
	}
	
	protected abstract void die(); // do something while dying , maybe throws a gun over the map
}
