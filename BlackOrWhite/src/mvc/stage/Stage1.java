package mvc.stage;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import role.AI;
import role.AI_Ball;

public class Stage1 extends Stage {
	public Stage1(Controller controller) {
		super(controller);
		for ( int i = 0 ; i < 10 ; i ++ )  //²Ä¤@Ãö10°¦
			monsters.add(new AI_Ball(0,0,ActionType.HALT,Dir.NORTH));
	}
}
