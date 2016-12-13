package weapon.bullets;

import mvc.ImageSequence;

public class SniperBulletFactory implements BulletFactory{
	private static ImageSequence[][] actImgs = new ImageSequence[][]{{
		new ImageSequence( "pics/Bullet/BasicBullet/Vertical","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Vertical","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal","png",1)}};

	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		return 52;
	}

	@Override
	public ImageSequence[][] getActionImages() {
		// TODO Auto-generated method stub
		return actImgs;
	}

}
