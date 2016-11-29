package weapon.guns;

import role.Role;
import weapon.bulletflying.NormalFlying;
import weapon.bullets.BasicBullet;
import weapon.bullets.Bullet;

public class Rifle implements Gun {

	@Override
	public Bullet gunShooting(Role role) {
		// �y���I�٭n�ھڹϮפ��j����l
		return new BasicBullet( role.x , role.y , new NormalFlying());
	}
	

}
