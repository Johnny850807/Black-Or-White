package weapon.bullets;

import mvc.Dir;
import mvc.Log;
import weapon.bullets.Bullet.Bullet_Effect;


public class BasicBullet extends Bullet {

	public BasicBullet(int x,int y,Dir curDir,BulletFactory factory) {
		super(x,y,7,26,120, true,true,curDir, factory,Bullet_Effect.EFFECT_BALL);
	}
	public BasicBullet(int x,int y,Dir curDir,BulletFactory factory,boolean isPlayer) {
		super(x,y,7,26,120, true,isPlayer,curDir, factory,Bullet_Effect.EFFECT_BALL);
	}

	

}
