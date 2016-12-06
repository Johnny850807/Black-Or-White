package weapon.bullets;

import mvc.ImageSequence;

public interface BulletFactory {
	int getDamage();
	int getDistance();
	ImageSequence[][] getActionImages();

}
