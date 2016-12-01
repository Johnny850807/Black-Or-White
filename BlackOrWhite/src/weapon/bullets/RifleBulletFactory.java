package weapon.bullets;

import mvc.ImageSequence;
import role.abstractFactory.PlayerFactory;
import weapon.bulletflying.BulletFlying;
import weapon.bulletflying.NormalFlying;

public class RifleBulletFactory implements BulletFactory{
	private static ImageSequence[][] actImgs = new ImageSequence[][]{{
		new ImageSequence( "pics/Bullet/BasicBullet/Vertical","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Vertical","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal","png",1)}};
	@Override
	public BulletFlying getBulletFlying() {
		return new NormalFlying();
	}

	@Override
	public ImageSequence[][] getActionImages() {
		return actImgs;
	}
	
	// test if the file path works
		public static void main(String[] argv){
			ImageSequence[][] actionImgs = new  RifleBulletFactory().getActionImages();
		}

}
