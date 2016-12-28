package weapon.gameEffects;

import mvc.ImageSequence;

public class PowerEffect extends GameEffect {
	private static ImageSequence[][] actionImgs  = new ImageSequence[][]{
		{ new ImageSequence( "pics/Effect/Power/Boom","png",16) ,
			new ImageSequence( "pics/Effect/Power/Boom","png",16) ,
			new ImageSequence( "pics/Effect/Power/Boom","png",16) ,
			new ImageSequence("pics/Effect/Power/Boom","png",16)} ,
{ new ImageSequence( "pics/Effect/Power/Boom","png",16) ,
				new ImageSequence( "pics/Effect/Power/Boom","png",16) ,
				new ImageSequence( "pics/Effect/Power/Boom","png",16) ,
				new ImageSequence( "pics/Effect/Power/Boom","png",16)}};
				
	public PowerEffect(int x, int y) {
		super(x, y, actionImgs[0][0]);
		// TODO Auto-generated constructor stub
	}
}
