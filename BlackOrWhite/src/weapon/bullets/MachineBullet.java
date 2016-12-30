package weapon.bullets;

import mvc.Dir;
import weapon.bullets.Bullet.Bullet_Effect;

public class MachineBullet extends Bullet{

	public MachineBullet(int cX, int cY,Dir curDir, BulletFactory factory) {
		super(cX,cY,7,26, 100, true,true, curDir, factory,Bullet_Effect.EFFECT_BALL);
		// TODO Auto-generated constructor stub
	}
	
	//EX
	public MachineBullet(int cX, int cY,Dir curDir, BulletFactory factory,boolean isSingle) {
		super(cX,cY,7,26, 15, isSingle,true, curDir, factory,Bullet_Effect.EFFECT_BALL);
		// TODO Auto-generated constructor stub
	}

}
