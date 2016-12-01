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
		Request request;

		while(requests.size() > 0)
		{
			request = requests.poll();
			curAct = request.act;
			curDir = request.dir;
			switch(request.act)
			{
				case HALT:
					curAct = ActionType.HALT;
					break;
				case WALK:
					curAct = ActionType.WALK;
					break;
				case SHOOT:
					curAct = ActionType.SHOOT;
					break;
			}
		}
	}
	
	public final void addRequest(ActionType act , Dir dir){
		requests.offer(new Request(act,dir));
	}
	
	protected void die(){
		// while the player dies
		
		
	}

	@Override
	int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -5;
					case SOUTH:
						return 5;
					case EAST:
						return 9;
					case WEST:
						return -9;
				}
				break;
			default:
				return 0;
		}
		return 0;
	}
	
}
