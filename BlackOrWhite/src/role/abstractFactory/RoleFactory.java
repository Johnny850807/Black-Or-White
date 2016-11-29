package role.abstractFactory;

import mvc.ImageSequence;
import role.movements.AI_Movement;
import role.movements.Backable;
import weapon.guns.Gun;

public interface RoleFactory {
	Gun getGun();
	ImageSequence[][] getActionImages();
	AI_Movement getMovement();
	Backable getBackable();
	int getHp();
	/*int getDf();
	int getAtk();  不會變的數值 不用工廠給了*/
}
