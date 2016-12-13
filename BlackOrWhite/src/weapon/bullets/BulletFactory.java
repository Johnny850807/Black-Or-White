package weapon.bullets;

import mvc.ImageSequence;

public interface BulletFactory {
	int getDistance();
	ImageSequence[][] getActionImages();

}
