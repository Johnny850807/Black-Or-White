package role.abstractFactory;

import mvc.ImageSequence;
import mvc.Log;
import role.movements.AI_Follow;
import role.movements.AI_Halt;
import role.movements.AI_Movement;
import role.movements.AI_Walk;
import role.movements.Backable;
import role.movements.GoBack;
import weapon.guns.Gun;

public class SnowBallExFactory implements RoleFactory {

	/* Hello , This is the template of the designation of an AI monster , 
	 * If you are creating your own monster ,
	 * Please update your monster's information 
	 * on the GitHub -> property.png or in the README.md
	 * */
	
	private static ImageSequence[][] actImgs; //singleton ! lazy instantiation
	
	@Override
	public Gun getGun() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageSequence[][] getActionImages() {
		if ( actImgs == null ){
			actImgs = new ImageSequence[][]{
  /*Halt*/		{ new ImageSequence( "pics/AI/SnowBall_EX/Walk/East","png",1) ,
					new ImageSequence( "pics/AI/SnowBall_EX/Walk/East","png",1) ,
					new ImageSequence( "pics/AI/SnowBall_EX/Walk/West","png",1) ,
					new ImageSequence( "pics/AI/SnowBall_EX/Walk/West","png",1)} ,
  /*Walk*/		{ new ImageSequence( "pics/AI/SnowBall_EX/Walk/East","png",1) ,
						new ImageSequence( "pics/AI/SnowBall_EX/Walk/East","png",1) ,
						new ImageSequence( "pics/AI/SnowBall_EX/Walk/West","png",1) ,
						new ImageSequence( "pics/AI/SnowBall_EX/Walk/West","png",1)}
					};
			Log.d("create ball image");
		}
		
		return actImgs;
	}

	@Override
	public AI_Movement getMovement() {
		return new AI_Follow(new AI_Halt(new AI_Walk()));
	}

	@Override
	public Backable getBackable() {
		// TODO Auto-generated method stub
		return new GoBack();
	}

}
