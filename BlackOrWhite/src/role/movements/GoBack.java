package role.movements;

import mvc.Dir;
import mvc.Model;
import role.*;

public class GoBack implements Backable {

	@Override
	public void goBack(Role role) {
		// �������h
		Model model = role.getModel();
		Dir dir = model.getDir();
		Dir oppositeDir = dir.getOppositeDir();
		role.getMoved(role.curAct, oppositeDir);
		role.getMoved(role.curAct, oppositeDir);
		role.getMoved(role.curAct, oppositeDir);
		role.getMoved(role.curAct, oppositeDir);
		role.getMoved(role.curAct, oppositeDir);
		role.getMoved(role.curAct, oppositeDir);
	}

}
