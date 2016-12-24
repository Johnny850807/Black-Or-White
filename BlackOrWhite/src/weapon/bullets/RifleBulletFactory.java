package weapon.bullets;

import mvc.ImageSequence;
import role.abstractFactory.PlayerFactory;


public class RifleBulletFactory implements BulletFactory{
	private static ImageSequence[][] actImgs = new ImageSequence[][]{{
		new ImageSequence( "pics/Bullet/BasicBullet/Vertical_B","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal_B","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Vertical_B","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal_B","png",1)}};

	@Override
	public ImageSequence[][] getActionImages() {
		return actImgs;
	}
	
	// test if the file path works
		public static void main(String[] argv){
			ImageSequence[][] actionImgs = new  RifleBulletFactory().getActionImages();
		}

	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		return 8;
	}

}
