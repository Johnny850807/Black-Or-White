package weapon.guns;

import mvc.Log;
import mvc.SoundManager;
import role.Role;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.bullets.MachineBullet;
import weapon.bullets.MachineBulletFactory;
import weapon.bullets.RifleBulletFactory;

public class AI_Rifle implements Gun{

	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		SoundManager.getSoundManager().playSound("sounds/shoot/rifle.wav");
		int x = role.x;
		int y = role.y;
		switch(role.curDir){
			case NORTH:
				x += 27;
				break;
			case EAST:
				x += 89;
				y += 35;
				break;
			case WEST:
				x += 4;
				y += 30;
				break;
			case SOUTH:
				x += 35;
				y += 89;
				break;
		}
		Bullet bullet = new BasicBullet( x , y , role.curDir , new RifleBulletFactory(),false);
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

