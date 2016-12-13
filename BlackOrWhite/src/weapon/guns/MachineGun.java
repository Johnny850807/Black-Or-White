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
		SoundManager.getSoundManager().playMachineSound();
				int x = role.x;
				int y = role.y;
				switch(role.curDir){
					case NORTH:
						x += 56;
						y += 2;
						break;
					case EAST:
						x += 87;
						y += 47;
						break;
					case WEST:
						y += 47;
						break;
					case SOUTH:
						x += 47;
						y += 57;
						break;
				}
				Bullet bullet = new MachineBullet( x , y , role.curDir , new MachineBulletFactory());
	
			//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
				return bullet;
	}

	@Override
	public int getSpacing() {
		return 0;
	}

}
