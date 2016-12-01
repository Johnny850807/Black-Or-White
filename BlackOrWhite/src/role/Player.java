package role;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import role.abstractFactory.PlayerFactory;
import role.abstractFactory.RoleFactory;

public class Player extends Role implements Runnable{

	public Player() {
		super(new PlayerFactory());
	}

	public Player(int x ,int y){
		super(new PlayerFactory(),x,y, ActionType.HALT , Dir.NORTH);
	}

	@Override
	public void run(){
		/*Thread template method
		 * 1. check if hurted (hook)
		 * 2. check if got requests (final)
		 */
		while(!isDead){
			hurtedJudgement();
			
			if ( requests.size() > 0 )
				processRequest();
		}
		
		die();
	}
	
	private final void processRequest(){
		// handle the request ... and update the model
	}
	
	public final void addRequest(ActionType act , Dir dir){
		requests.offer(new Request(act,dir));
	}
	
	protected void die(){
		// while the player dies
		
		
	}
	
}
