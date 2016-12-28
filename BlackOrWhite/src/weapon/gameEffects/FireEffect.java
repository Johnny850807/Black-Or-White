package weapon.gameEffects;

import mvc.ImageSequence;

public class FireEffect extends GameEffect {
	
	private static ImageSequence[][] actionImgs  = new ImageSequence[][]{
		{ new ImageSequence( "pics/Effect/Fire/Boom","png",4) ,
			new ImageSequence( "pics/Effect/Fire/Boom","png",4) ,
			new ImageSequence( "pics/Effect/Fire/Boom","png",4) ,
			new ImageSequence("pics/Effect/Fire/Boom","png",4)} ,
{ new ImageSequence( "pics/Effect/Fire/Boom","png",4) ,
				new ImageSequence( "pics/Effect/Fire/Boom","png",4) ,
				new ImageSequence( "pics/Effect/Fire/Boom","png",4) ,
				new ImageSequence( "pics/Effect/Fire/Boom","png",4)}};
				
				
	public FireEffect(int x, int y) {
		super(x, y, actionImgs[0][0]);
		// TODO Auto-generated constructor stub
	}

}
