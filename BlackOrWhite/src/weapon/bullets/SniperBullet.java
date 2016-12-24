package weapon.bullets;

import mvc.Dir;

public class SniperBullet extends Bullet{

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory) {
		super(cX,cY,7,26,330,true, true,curDir, factory);
	}

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory, boolean isPlayer) {
		super(cX,cY,7,26,330,true, isPlayer,curDir, factory);
	}

}
