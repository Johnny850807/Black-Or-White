package weapon.guns;

import mvc.SoundManager;
import role.Role;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.bullets.MachineBulletFactory;
import weapon.bullets.SniperBullet;
import weapon.bullets.SniperBulletFactory;

public class SniperRifle implements Gun {

	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		SoundManager.getSoundManager().playSound("sounds/shoot/sniperRifle.wav");
			int x = role.x;
			int y = role.y;
			switch(role.curDir){
			case NORTH:
				x += 29-15;
				y += 2-98;
				break;
			case EAST:
				x += 75;
				y += 22;
				break;
			case WEST:
				x += 6-98;
				y += 31-15;
				break;
			case SOUTH:
				x += 32-15;
				y += 72;
				break;
		}
			Bullet bullet = new SniperBullet( x , y , role.curDir , new SniperBulletFactory());
			//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
			return bullet;
	}

	@Override
	public int getSpacing() {
		return 1900;
	}

}
