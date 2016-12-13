package weapon.bullets;

import mvc.Dir;

public class SniperBullet extends Bullet{

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory) {
		super(cX,cY,7,26,330, curDir, factory);
	}

}
