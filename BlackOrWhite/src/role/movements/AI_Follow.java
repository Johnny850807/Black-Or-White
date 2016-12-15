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
		
		for ( Role r : players )
		{
			boolean hori_Barrier = false;
			boolean verti_Barrier = false;
			int x = r.x + 25; //身體重心點
			int y = r.y + 25;


			char southY = '*'; // 下一格地圖
			char northY = '*'; // 下一格地圖
			char westX = '*'; // 左一格地圖
			char eastX = '*'; // 右一格地圖
			//若下一格地圖為障礙物則取消追逐
			if ( !((ai.y/100+1) >= MapBuilder.SIZEY || ai.x/100 < 0 || ai.x/100 > MapBuilder.SIZEX ))
				southY = map[(ai.y/100+1)].charAt(ai.x/100); // 下一格地圖
			if ( !((ai.y/100-1) < 0  || ai.x/100 < 0 || ai.x/100 > MapBuilder.SIZEX))
				northY = map[(ai.y/100-1)].charAt(ai.x/100); // 下一格地圖
			if ( !((ai.x/100-1) < 0 || ai.y/100 < 0 || ai.y/100 > MapBuilder.SIZEY))
				westX = map[ai.y/100].charAt(ai.x/100-1); // 下一格地圖
			if ( !((ai.x/100+1) >= MapBuilder.SIZEX || ai.y/100 < 0 || ai.y/100 > MapBuilder.SIZEY))
				eastX = map[ai.y/100].charAt(ai.x/100+1); // 下一格地圖
			
			if ( x >= ai.x && x <= ai.x+ai.getFeetW() && y <= ai.y && ( northY != '*' && northY != '0')){
				ai.getMoved( ActionType.WALK , Dir.NORTH );
				return true;
			}
			else if ( x >= ai.x && x <= ai.x+ai.getFeetW() && y > ai.y && ( southY != '*' && southY != '0')){
				ai.getMoved( ActionType.WALK , Dir.SOUTH );
				return true;
			}
			else if ( y >= ai.x && y <= ai.x+ai.getFeetH() && x <= ai.x && ( westX != '*' && westX != '0')){
				ai.getMoved( ActionType.WALK , Dir.WEST );
				return true;
			}
			else if ( y >= ai.x && y <= ai.x+ai.getFeetH() && x > ai.x && ( eastX != '*' && eastX != '0')){
				ai.getMoved( ActionType.WALK , Dir.EAST );
				return true;
			}
		}
		return false;
	}
	

}
