package weapon.bullets;

import mvc.Dir;

public class SniperBullet extends Bullet{

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory) {
		super(cX,cY,16,31,400,true, true,curDir, factory);
	}

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory, boolean isPlayer) {
		super(cX,cY,16,31,400,true, isPlayer,curDir, factory);
	}

}
