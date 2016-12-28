package weapon.bullets;

import mvc.Dir;
import weapon.bullets.Bullet.Bullet_Effect;

public class SniperBullet extends Bullet{

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory) {
		super(cX,cY,16,31,400,true, true,curDir, factory,Bullet_Effect.EFFECT_FIRE);
	}

	public SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory, boolean isPlayer) {
		super(cX,cY,16,31,400,true, isPlayer,curDir, factory,Bullet_Effect.EFFECT_FIRE);
	}

}
