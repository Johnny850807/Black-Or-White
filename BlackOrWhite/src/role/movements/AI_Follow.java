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

	
	public AI_Follow(AI_Movement wrapped) {
		super(wrapped);
	}

	@Override
	public void randomChoose(AI ai) {
		List<Role> players = new ArrayList<Role>(); //裝入玩家
		Controller controller = Controller.getController();
		if ( controller.getPlayer1() != null )  //裝進存在玩家
			players.add(controller.getPlayer1());
		if ( controller.getPlayer2() != null )  //裝進存在玩家
			players.add(controller.getPlayer2());
		if ( ai.isTimeToChangeMove() ){  //是時候轉換動作
			if( !isInScope(ai,players))  //如果玩家在視野內 就追蹤 不然回傳false
				movement.randomChoose(ai);  //回傳false就往下一層想
			else{
				Log.d("Follow");
				ai.moveDurationCountDown(35);
			}
		}
		else
			ai.keepCurrentMove();
	}
	
	public boolean isInScope(AI ai,List<Role> players){
		//是否在視野中
		int aX,aY;  // Ai座標在地圖上的索引
		int rX,rY;  // 玩家座標在地圖上的索引
		for ( Role r : players )
		{
			aX = (ai.x+ai.getOffsetX()+20) / 100;
			aY = (ai.y+ai.getOffsetY()+20) / 100;
			rX = (r.x+r.getOffsetX()) / 100;
			rY = (r.y+r.getOffsetY()) / 100;

			Log.d("Site Player :("+rX+","+rY+")  AI :("+aX+","+aY+")");
			//判斷障礙物 是否在該條線上
			//run south
			for ( int i = aY+1 ; i >= 0 && i < MapBuilder.SIZEY && aX == rX && rY > aY  ; i ++ )
			{
				if( i == rY ){
					ai.getMoved(ActionType.WALK, Dir.SOUTH);
					return true;
				}
				if ( map[i].charAt(aX) == '0' || map[i].charAt(aX) == '*' )
					return false;
			}
			//run north
			for ( int i = aY-1 ; i >= 0 && i < MapBuilder.SIZEY && aX == rX && rY < aY ; i -- )
			{
				if( i == rY ){
					ai.getMoved(ActionType.WALK, Dir.NORTH);
					return true;
				}
				if ( map[i].charAt(aX) == '0' || map[i].charAt(aX) == '*' )
					return false;
			}
			//run east
			for ( int i = aX+1 ; i >= 0 && i < MapBuilder.SIZEX && aY == rY && rY > aY ; i ++ )
			{
				if( i == rX ){
					ai.getMoved(ActionType.WALK, Dir.EAST);
					return true;
				}
				if ( map[aY].charAt(i) == '0' || map[aY].charAt(i) == '*' )
					return false;
			}
			//run west
			for ( int i = aX-1 ; i >= 0 && i < MapBuilder.SIZEX && aY == rY  && rY < aY ; i -- )
			{
				if( i == rX ){
					ai.getMoved(ActionType.WALK, Dir.WEST);
					return true;
				}
				if ( map[aY].charAt(i) == '0' || map[aY].charAt(i) == '*' )
					return false;
			}
			
			//judge player site

			
		}

		return false;
	}
	

}
