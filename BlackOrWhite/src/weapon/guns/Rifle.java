package weapon.guns;

import mvc.Dir;
import role.Role;
import weapon.bulletflying.NormalFlying;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.bullets.RifleBulletFactory;

public class Rifle implements Gun {

	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		int x = role.x;
		int y = role.y;
		switch(role.curDir){
			case NORTH:
				x += 56;
				y += 2;
				break;
			case EAST:
				y += 47;
				break;
			case WEST:
				x += 87;
				y += 47;
				break;
			case SOUTH:
				x += 47;
				y += 57;
				break;
		}
		Bullet bullet = new BasicBullet( x , y , role.curDir , new RifleBulletFactory());
		new Thread(bullet).start();
	//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
		return bullet;
	}

	@Override
	public int getSpacing() {
		//射擊間格
		return 750;
	}
	

}
