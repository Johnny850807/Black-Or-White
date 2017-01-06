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
		List<Role> players = new ArrayList<Role>(); //�ˤJ���a
		Controller controller = Controller.getController();
		if ( controller.getPlayer1() != null )  //�˶i�s�b���a
			players.add(controller.getPlayer1());
		if ( controller.getPlayer2() != null )  //�˶i�s�b���a
			players.add(controller.getPlayer2());
		if ( ai.isTimeToChangeMove() ){  //�O�ɭ��ഫ�ʧ@
			if( !isInScope(ai,players))  //�p�G���a�b������ �N�l�� ���M�^��false
				movement.randomChoose(ai);  //�^��false�N���U�@�h�Q
			else{
				Log.d("Follow");
				ai.moveDurationCountDown(35);
			}
		}
		else
			ai.keepCurrentMove();
	}
	
	public boolean isInScope(AI ai,List<Role> players){
		//�O�_�b������
		int aX,aY;  // Ai�y�Цb�a�ϤW������
		int rX,rY;  // ���a�y�Цb�a�ϤW������
		for ( Role r : players )
		{
			aX = (ai.x+ai.getOffsetX()+20) / 100;
			aY = (ai.y+ai.getOffsetY()+20) / 100;
			rX = (r.x+r.getOffsetX()) / 100;
			rY = (r.y+r.getOffsetY()) / 100;

			Log.d("Site Player :("+rX+","+rY+")  AI :("+aX+","+aY+")");
			//�P�_��ê�� �O�_�b�ӱ��u�W
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
