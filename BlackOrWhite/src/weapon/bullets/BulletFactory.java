package weapon.bullets;

import mvc.ImageSequence;
import weapon.bulletflying.BulletFlying;

public interface BulletFactory {
	BulletFlying getBulletFlying();
	ImageSequence[][] getActionImages();

}//對 我在這邊模擬遊戲引擎 所以程式碼一大堆
