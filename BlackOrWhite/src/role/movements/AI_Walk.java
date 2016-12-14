package role.movements;

import java.util.Random;

import mvc.ActionType;
import mvc.Dir;
import mvc.Log;
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
		//�P�_�Ǫ��b�a�ϥ|�Ӥ�쪺���ʾ��v
		Model model = ai.getModel();
		int x = model.getcX() , y = model.getcY();
		Dir[] dirEast = {Dir.NORTH,Dir.SOUTH,Dir.WEST,Dir.WEST,Dir.WEST,Dir.WEST,Dir.EAST};
		Dir[] dirWest = {Dir.NORTH,Dir.SOUTH,Dir.EAST,Dir.EAST,Dir.EAST,Dir.EAST,Dir.EAST};
		Dir[] dirNorth = {Dir.EAST,Dir.WEST,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.SOUTH,Dir.NORTH};
		Dir[] dirSouth = {Dir.EAST,Dir.WEST,Dir.NORTH,Dir.NORTH,Dir.NORTH,Dir.WEST,Dir.SOUTH};
		Dir rDir;  //���G��V
		//east
		if ( x < 400 && y > 200 && y < 600 )
			rDir = dirEast[random.nextInt(dirEast.length)];
		//west
		else if ( x > 720 && y > 200 && y < 600 )
			rDir = dirWest[random.nextInt(dirWest.length)];
		//north
		else if ( y <= 200 )
			rDir = dirNorth[random.nextInt(dirNorth.length)];
		//south
		else if ( y >= 600 )
			rDir = dirSouth[random.nextInt(dirSouth.length)];
		else
			rDir = dir[random.nextInt(dir.length)];
		//�p�G��󤣥i���ʪ����A�N����V
		while( !ai.getMoved( ActionType.WALK , dir[random.nextInt(dir.length)]) ){
			ai.getMoved( ActionType.WALK , dir[random.nextInt(dir.length)]);
			ai.moveDurationCountDown(20);
			Log.d("���i����");
		}
		
	}

}
