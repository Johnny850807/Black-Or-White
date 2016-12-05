package role;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import role.abstractFactory.PlayerFactory;
import role.abstractFactory.RoleFactory;

public class Player extends Role implements Runnable{

	public Player() {
		super(new PlayerFactory());
	}

	public Player(int x ,int y){
		super(new PlayerFactory(),x,y, ActionType.HALT , Dir.NORTH);
		offsetX = 21;
		offsetY = 90;
		feetW = 40;
		feetH = 10;
		hp = 500;
		atk = 0;
		df = 20;
	}

	@Override
	public void run(){
		/*Thread template method
		 * 1. check if hurted (hook)
		 * 2. check if got requests (final)
		 */
		Log.d("Player run!");
		while(!isDead){
			hurtedJudgement();
			
			if ( requests.size() > 0 )
				processRequest();
			else{
				if(curAct != ActionType.SHOOT)
					getMoved(curAct, curDir);
			}
			try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		die();
	}
	
	private final void processRequest(){
		// handle the request ... and update the model
		Request request;
		Log.d("process request");
		while(requests.size() > 0)
		{
			request = requests.poll();
			if ( request.act == ActionType.SHOOT && isShootSpacing )
				return;  //如果位於射擊緩衝時間 而且使用者點選射擊 就不理會
			super.getMoved(request.act, request.dir);	
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
