package role.movements;

import java.util.ArrayList;
import java.util.List;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.Log;
import mvc.Map1Director;
import mvc.MapBuilder;
import role.AI;
import role.Player;
import role.Role;

public class AI_Follow extends AI_Decorator {
	private String[] map = Map1Director.getMapString();
	private List<Role> players = new ArrayList<Role>(); //裝入玩家
	
	public AI_Follow(AI_Movement wrapped) {
		super(wrapped);
	}

	@Override
	public void randomChoose(AI ai) {
		Controller controller = Controller.getController();
		players.add(controller.getPlayer1());
		if ( controller.getPlayer2() != null )  //裝進存在玩家
			players.add(controller.getPlayer2());
		if ( ai.isTimeToChangeMove() ){  //是時候轉換動作
			if(!isInScope(ai))  //如果玩家在視野內 就追蹤 不然回傳false
				movement.randomChoose(ai);  //回傳false就往下一層想
			else{
				Log.d("Follow");
				ai.moveDurationCountDown(35);
			}
		}
		else
			ai.keepCurrentMove();
	}
	
	public boolean isInScope(AI ai){
		//是否在視野中
		int rX,rY;  // 玩家座標在地圖上的索引
		for ( Role r : players )
		{
			rX = r.x / 100;
			rY = r.y / 100;
		}
		return false;
	}
	

}
