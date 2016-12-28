package weapon.bullets;

import mvc.Dir;

public class AI_SniperBullet extends Bullet{
	public AI_SniperBullet(int cX, int cY, Dir curDir, BulletFactory factory) {
		super(cX,cY,16,31,200,true, false,curDir, factory,Bullet_Effect.EFFECT_FIRE);
	}

}
