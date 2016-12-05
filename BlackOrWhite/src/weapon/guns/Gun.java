package weapon.guns;

import role.Role;
import weapon.bullets.Bullet;

public interface Gun {
	Bullet gunShooting(Role role);
	int getSpacing();
}
