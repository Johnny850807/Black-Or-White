package weapon.guns;

import mvc.Dir;
import mvc.Log;
import role.Role;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;
import weapon.bullets.RifleBulletFactory;

public class Rifle implements Gun {

	@Override
	public Bullet gunShooting(Role role) {
		// �y���I�٭n�ھڹϮפ��j����l
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
		Bullet bullet = new BasicBullet( x , y , role.curDir , new RifleBulletFactory());
		new Thread(bullet).start();
		Log.d("�l�u++");
	//	try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
		return bullet;
	}

	@Override
	public int getSpacing() {
		//�g������
		return 750;
	}
	

}
