package weapon.guns;

import role.Role;
import weapon.bulletflying.NormalFlying;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;

public class Rifle implements Gun {

	@Override
	public Bullet gunShooting(Role role) {
		// 座標點還要根據圖案中槍的位子
		return new BasicBullet( role.x , role.y , new NormalFlying());
	}
	

}
