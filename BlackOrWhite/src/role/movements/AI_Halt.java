package role.movements;

import mvc.ActionType;
import role.AI;

public class AI_Halt extends AI_Decorator {

	public AI_Halt(AI_Movement wrapped) {
		super(wrapped);
	}

	@Override
	public void randomChoose(AI ai) {
		// 20% to halt about a while ...
		//���v�ŦX �Ϊ�  �ŦX�i�H���ʪ��ɾ� �N����
		if ( random.nextInt(100) >= 80 && ai.isTimeToChangeMove() ){
				ai.getMoved( ActionType.HALT , dir[random.nextInt(dir.length)]);
				ai.moveDurationCountDown(20); // HALT�]�w�˼�20
		}
		else if( !ai.isTimeToChangeMove() )  //���v�ŦX ���o���ŦX���ʮɾ� �N�����쥻�ʧ@
				ai.keepCurrentMove();
		else { // ���v���ŦX ���ŦX���ʮɾ� ���N���U�@�h�ʧ@�ǻ�
			movement.randomChoose(ai);
			return;
		}
		
	}
	


}
