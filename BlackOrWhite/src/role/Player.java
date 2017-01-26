package role;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import role.abstractFactory.PlayerFactory;
import role.abstractFactory.RoleFactory;

public class Player extends Role {

	public Player() {
		super(new PlayerFactory());
	}

	public Player(int x ,int y){
		super(new PlayerFactory(),x,y, ActionType.HALT , Dir.NORTH , 11 , 13 , 48 , 36 , 500 , 0 , 20);
		/*offsetX = 11;
		offsetY = 13;
		feetW = 48;
		feetH = 36;
		hp = 500;
		atk = 0;
		df = 20;*/
	}
	@Override //單次執行 放棄使用執行緒
	public void run(){
		/*Thread template method
		 * 1. check if hurted (hook)
		 * 2. check if got requests (final)
		 */
		hurtedJudgement();
		
		if ( requests.size() > 0 )
			processRequest();
		else{
			if(curAct != ActionType.SHOOT)
				getMoved(curAct, curDir);
		}
		die();
	}
	
	private synchronized final void processRequest(){
		// handle the request ... and update the model
		Request request;
		while(requests.size() > 0)
		{

			try{
				request = requests.poll();
				if( request.act == curAct && request.act == ActionType.HALT)
					continue;
				if ( request.act == ActionType.SHOOT && isShootSpacing ){
					return;  //如果位於射擊緩衝時間 而且使用者點選射擊 就不理會
				}
				getMoved(request.act, request.dir);	
			}catch(NullPointerException err){
				err.printStackTrace();
				break;
			}
			catch(Exception err){
				err.printStackTrace();
				Log.d("命令佇列長度: "+requests.size());
				//break;
			}
			
		}
	}
	
	public final void addRequest(ActionType act , Dir dir){
		requests.offer(new Request(act,dir));
	}
	
	protected void die(){
		// while the player dies
		if ( hp <= 0 ){
			if(this == Controller.getController().getPlayer1())
				Controller.getController().player1SetDie();
			else if(this == Controller.getController().getPlayer2())
				Controller.getController().player2SetDie();
			model.delete();  //在控制器中刪除掉自己
		}
	}

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -4;
					case SOUTH:
						return 4;
					case EAST:
						return 6;
					case WEST:
						return -6;
				}
				break;
			default:
				return 0;
		}
		return 0;
	}
	
	
	
}
