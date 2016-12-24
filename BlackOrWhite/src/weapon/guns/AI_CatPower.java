package weapon.guns;

import mvc.SoundManager;
import role.Role;
import weapon.bullets.Bullet;
import weapon.bullets.CatExplodeBullet;
import weapon.bullets.CatExplodeBulletFactory;
import weapon.bullets.SniperBullet;
import weapon.bullets.SniperBulletFactory;

public class AI_CatPower implements Gun {
	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		SoundManager.getSoundManager().playSound("sounds/shoot/sniperRifle.wav");
			int x = role.x;
			int y = role.y;

			Bullet bullet = new CatExplodeBullet( x+10 , y+10 , role.curDir , new CatExplodeBulletFactory());
			//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
			return bullet;
	}

	@Override
	public int getSpacing() {
		return 2500;
	}
	
}
