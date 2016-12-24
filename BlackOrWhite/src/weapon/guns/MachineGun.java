package weapon.guns;

import mvc.SoundManager;
import role.Role;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.bullets.MachineBullet;
import weapon.bullets.MachineBulletFactory;
import weapon.bullets.RifleBulletFactory;

public class MachineGun implements Gun{

	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		SoundManager.getSoundManager().playSound("sounds/shoot/machineGun.wav");
				int x = role.x;
				int y = role.y;
				switch(role.curDir){
					case NORTH:
						x += 29-6;
						y += 2-35;
						break;
					case EAST:
						x += 75;
						y += 22;
						break;
					case WEST:
						x += 6-35;
						y += 31-6;
						break;
					case SOUTH:
						x += 32-6;
						y += 72;
						break;
				}
				Bullet bullet = new MachineBullet( x , y , role.curDir , new MachineBulletFactory());
	
			//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
				return bullet;
	}

	@Override
	public int getSpacing() {
		return 100;
	}

}
