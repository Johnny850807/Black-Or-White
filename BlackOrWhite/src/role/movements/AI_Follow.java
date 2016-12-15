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
		
		for ( Role r : players )
		{
			boolean hori_Barrier = false;
			boolean verti_Barrier = false;
			int x = r.x + 25; //���魫���I
			int y = r.y + 25;


			char southY = '*'; // �U�@��a��
			char northY = '*'; // �U�@��a��
			char westX = '*'; // ���@��a��
			char eastX = '*'; // �k�@��a��
			//�Y�U�@��a�Ϭ���ê���h�����l�v
			if ( !((ai.y/100+1) >= MapBuilder.SIZEY || ai.x/100 < 0 || ai.x/100 > MapBuilder.SIZEX ))
				southY = map[(ai.y/100+1)].charAt(ai.x/100); // �U�@��a��
			if ( !((ai.y/100-1) < 0  || ai.x/100 < 0 || ai.x/100 > MapBuilder.SIZEX))
				northY = map[(ai.y/100-1)].charAt(ai.x/100); // �U�@��a��
			if ( !((ai.x/100-1) < 0 || ai.y/100 < 0 || ai.y/100 > MapBuilder.SIZEY))
				westX = map[ai.y/100].charAt(ai.x/100-1); // �U�@��a��
			if ( !((ai.x/100+1) >= MapBuilder.SIZEX || ai.y/100 < 0 || ai.y/100 > MapBuilder.SIZEY))
				eastX = map[ai.y/100].charAt(ai.x/100+1); // �U�@��a��
			
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
