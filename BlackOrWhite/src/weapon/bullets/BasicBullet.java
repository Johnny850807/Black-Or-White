package weapon.bullets;

import mvc.Dir;
import mvc.Log;


public class BasicBullet extends Bullet {

	public BasicBullet(int x,int y,Dir curDir,BulletFactory factory) {
		super(x,y,7,26,120, curDir, factory);
	}

	

}
