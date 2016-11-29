package role;

import role.abstractFactory.RoleFactory;

public class Player extends Role implements Runnable{

	public Player(RoleFactory factory) {
		super(factory);
	}

	public Player(RoleFactory factory,int x ,int y){
		super(factory,x,y);
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
	
	public final void addRequest(Request request){
		requests.offer(request);
	}
	
	protected void die(){
		// while the player dies
		
		
	}
	
}
