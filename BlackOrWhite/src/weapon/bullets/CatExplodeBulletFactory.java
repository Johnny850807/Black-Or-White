package weapon.bullets;

import mvc.ImageSequence;

public class CatExplodeBulletFactory implements BulletFactory{
	private static ImageSequence[][] actImgs = new ImageSequence[][]{{
		new ImageSequence( "pics/Bullet/Cat_Bullet/East","png",20)
		,new ImageSequence( "pics/Bullet/Cat_Bullet/East","png",20)
		,new ImageSequence( "pics/Bullet/Cat_Bullet/East","png",20)
		,new ImageSequence( "pics/Bullet/Cat_Bullet/East","png",20)}};

	@Override
	public ImageSequence[][] getActionImages() {
		return actImgs;
	}

	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		return 8;
	}
}
