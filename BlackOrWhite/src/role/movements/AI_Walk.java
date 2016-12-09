package role.movements;

import java.util.Random;

import mvc.ActionType;
import mvc.Dir;
import mvc.Model;
import role.AI;

public class AI_Walk implements AI_Movement {
	@Override
	public void randomChoose(AI ai) {
		// the Final choice --> to Walk !!
		// Walk on the specific direction about a while ...
		if (ai.isTimeToChangeMove()){
			 walkAlgorithm(ai);
		}
		else
			ai.keepCurrentMove();
		ai.moveDurationCountDown();
	}
	
	private void walkAlgorithm(AI ai){
		//判斷怪物在地圖四個方位的移動機率
		Model model = ai.getModel();
		int x = model.getcX() , y = model.getcY();
		Dir[] dirEast = {Dir.NORTH,Dir.SOUTH,Dir.WEST,Dir.WEST,Dir.WEST,Dir.WEST,Dir.EAST};
		Dir[] dirWest = {Dir.NORTH,Dir.SOUTH,Dir.EAST,Dir.EAST,Dir.EAST,Dir.EAST,Dir.EAST};
		Dir[] dirNorth = {Dir.EAST,Dir.WEST,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.NORTH};
		Dir[] dirSouth = {Dir.EAST,Dir.WEST,Dir.NORTH,Dir.NORTH,Dir.NORTH,Dir.WEST,Dir.SOUTH};
		//east
		if ( x < 400 && y > 200 && y < 600 )
			ai.getMoved( ActionType.WALK , dirEast[random.nextInt(dirEast.length)]);
		//west
		else if ( x > 800 && y > 200 && y < 600 )
			ai.getMoved( ActionType.WALK , dirWest[random.nextInt(dirWest.length)]);
		//north
		else if ( y <= 200 )
			ai.getMoved( ActionType.WALK , dirNorth[random.nextInt(dirNorth.length)]);
		//south
		else if ( y >= 600 )
			ai.getMoved( ActionType.WALK , dirSouth[random.nextInt(dirSouth.length)]);
		else
			ai.getMoved( ActionType.WALK , dir[random.nextInt(dir.length)]);
	}

}
