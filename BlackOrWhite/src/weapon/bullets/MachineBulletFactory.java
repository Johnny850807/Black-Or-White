package weapon.bullets;

import mvc.ImageSequence;

public class MachineBulletFactory implements BulletFactory {
	private static ImageSequence[][] actImgs = new ImageSequence[][]{{
		new ImageSequence( "pics/Bullet/BasicBullet/Vertical_B","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal_B","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Vertical_B","png",1)
		,new ImageSequence( "pics/Bullet/BasicBullet/Horizontal_B","png",1)}};

	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public ImageSequence[][] getActionImages() {
		return actImgs;
	}

}
