package weapon.bullets;

import mvc.ImageSequence;
import weapon.bulletflying.BulletFlying;

public interface BulletFactory {
	BulletFlying getBulletFlying();
	ImageSequence[][] getActionImages();
}
