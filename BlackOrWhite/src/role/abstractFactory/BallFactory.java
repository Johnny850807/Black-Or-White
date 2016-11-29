package role.abstractFactory;

import mvc.ImageSequence;
import role.movements.AI_Halt;
import role.movements.AI_Movement;
import role.movements.AI_Walk;
import role.movements.Backable;
import role.movements.GoBack;
import weapon.guns.Gun;

public class BallFactory implements RoleFactory {

	/* Hello , This is the template of designing a AI monster , 
	 * If you are creating your own monster ,
	 * Please update your monster's information 
	 * on the GitHub -> property.png or in the README.md
	 * */
	@Override
	public Gun getGun() {
		return null;
	}

	@Override
	public AI_Movement getMovement() {
		// the basic ball monster behave walk and halt
		// small cute guy doesn't have any idea with
		// hurting the human being ...
		return new AI_Halt(new AI_Walk());
	}

	@Override
	public Backable getBackable() {
		return new GoBack();
	}

	@Override
	public int getHp() {
		return 300;
	}

	@Override
	public int getDf() {
		return 20;
	}

	@Override
	public int getAtk() {
		return 24;
	}

	@Override
	public ImageSequence[][] getActionImages() {
		// TODO Auto-generated method stub
		return null;
	}

}
