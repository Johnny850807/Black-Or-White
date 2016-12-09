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
		//機率符合 或者  符合可以移動的時機 就移動
		if ( random.nextInt(100) >= 80 && ai.isTimeToChangeMove() ){
				ai.getMoved( ActionType.HALT , dir[random.nextInt(dir.length)]);
				ai.moveDurationCountDown(20); // HALT設定倒數20
		}
		else if( !ai.isTimeToChangeMove() )  //機率符合 但卻不符合移動時機 就維持原本動作
				ai.keepCurrentMove();
		else { // 機率不符合 但符合移動時機 那就往下一層動作傳遞
			movement.randomChoose(ai);
			return;
		}
		
	}
	


}
