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
	private List<Role> players = new ArrayList<Role>(); //�ˤJ���a
	
	public AI_Follow(AI_Movement wrapped) {
		super(wrapped);
	}

	@Override
	public void randomChoose(AI ai) {
		Controller controller = Controller.getController();
		players.add(controller.getPlayer1());
		if ( controller.getPlayer2() != null )  //�˶i�s�b���a
			players.add(controller.getPlayer2());
		if ( ai.isTimeToChangeMove() ){  //�O�ɭ��ഫ�ʧ@
			if(!isInScope(ai))  //�p�G���a�b������ �N�l�� ���M�^��false
				movement.randomChoose(ai);  //�^��false�N���U�@�h�Q
			else{
				Log.d("Follow");
				ai.moveDurationCountDown(35);
			}
		}
		else
			ai.keepCurrentMove();
	}
	
	public boolean isInScope(AI ai){
		//�O�_�b������
		int rX,rY;  // ���a�y�Цb�a�ϤW������
		for ( Role r : players )
		{
			rX = r.x / 100;
			rY = r.y / 100;
		}
		return false;
	}
	

}
