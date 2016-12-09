package role.movements;

import java.util.Random;

import mvc.ActionType;
import mvc.Dir;
import role.AI;

public interface AI_Movement {
	Random random = new Random();
	ActionType[] acts = ActionType.values();
	Dir[] dir = Dir.values();
	void randomChoose(AI ai);
}
