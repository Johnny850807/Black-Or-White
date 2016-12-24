package weapon.bullets;

import mvc.Dir;

public class CatExplodeBullet extends Bullet{
	public CatExplodeBullet(int x,int y,Dir curDir,BulletFactory factory) {
		super(x,y,80,104,180, true,false,curDir, factory);
	}
	
}
