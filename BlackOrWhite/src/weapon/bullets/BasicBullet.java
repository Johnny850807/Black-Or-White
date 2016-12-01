package weapon.bullets;

import mvc.Dir;
import mvc.Log;
import weapon.bulletflying.BulletFlying;

public class BasicBullet extends Bullet {

	public BasicBullet(int x,int y,Dir curDir,BulletFactory factory) {
		super(x,y,7,26,120, curDir, factory);
	}

	@Override
	public void run() {
		Log.d("Basic Bullet Run!");
		while(true){
			flying();
			if(!flyable())
			{
				Log.d("end flying");
				model.delete();
				break;
			}
			try {Thread.sleep(50);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

}
