package weapon.guns;

import mvc.SoundManager;
import role.Role;
import weapon.bullets.Bullet;
import weapon.bullets.FastBulletFactory;
import weapon.bullets.MachineBullet;
import weapon.bullets.MachineBulletFactory;

public class MachineGunEX implements Gun{
	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		SoundManager.getSoundManager().playSound("sounds/shoot/machineGun.wav");
				int x1 = role.x;
				int y1 = role.y;
				int x2 = 0;
				int y2 = 0;
				int diff = 10;
				switch(role.curDir){
					case NORTH:
						x1 += 29-6 -diff;
						y1 += 2-35;
						x2 = x1 + diff*2;
						y2 = y1;
						break;
					case EAST:
						x1 += 75;
						y1 += 22-diff;
						x2 = x1;
						y2 = y1+diff*2;
						break;
					case WEST:
						x1 += 6-35;
						y1 += 31-6-diff;
						x2 = x1;
						y2 = y1+diff*2;
						break;
					case SOUTH:
						x1 += 32-6;
						y1 += 72;
						x2 = x1 + diff*2;
						y2 = y1;
						break;
				}
				Bullet bullet = new MachineBullet( x1 , y1 , role.curDir , new FastBulletFactory(),false);
				Bullet bullet2 = new MachineBullet( x2 , y2 , role.curDir , new FastBulletFactory(),false);
			//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
				return bullet;
	}

	@Override
	public int getSpacing() {
		return 80;
	}
}
