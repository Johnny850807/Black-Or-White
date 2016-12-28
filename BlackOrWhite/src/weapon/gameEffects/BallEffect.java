package weapon.gameEffects;

import mvc.ImageSequence;

public class BallEffect extends GameEffect {

	private static ImageSequence[][] actionImgs  = new ImageSequence[][]{
		{ new ImageSequence( "pics/Effect/Ball/Boom","png",12) ,
			new ImageSequence( "pics/Effect/Ball/Boom","png",12) ,
			new ImageSequence( "pics/Effect/Ball/Boom","png",12) ,
			new ImageSequence("pics/Effect/Ball/Boom","png",12)} ,
{ new ImageSequence( "pics/Effect/Ball/Boom","png",12) ,
				new ImageSequence( "pics/Effect/Ball/Boom","png",12) ,
				new ImageSequence( "pics/Effect/Ball/Boom","png",12) ,
				new ImageSequence( "pics/Effect/Ball/Boom","png",12)}};
				
	public BallEffect(int x, int y) {
		super(x, y, actionImgs[0][0]);
		// TODO Auto-generated constructor stub
	}


}
