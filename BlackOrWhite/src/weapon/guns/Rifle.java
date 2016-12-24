package weapon.guns;

import mvc.Dir;
import mvc.Log;
import mvc.SoundManager;
import role.Role;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.bullets.RifleBulletFactory;

public class Rifle implements Gun {

	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		SoundManager.getSoundManager().playSound("sounds/shoot/rifle.wav");
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
		Bullet bullet = new BasicBullet( x , y , role.curDir , new RifleBulletFactory());
		Log.d("子彈++");
		
	//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
		return bullet;
	}

	@Override
	public int getSpacing() {
		//射擊間格
		return 750;
	}
	

}
