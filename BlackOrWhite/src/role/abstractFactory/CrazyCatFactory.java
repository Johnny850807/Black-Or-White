package role.abstractFactory;

import mvc.ImageSequence;
import mvc.Log;
import role.movements.AI_Follow;
import role.movements.AI_Halt;
import role.movements.AI_Movement;
import role.movements.AI_Shooting;
import role.movements.AI_Walk;
import role.movements.Backable;
import role.movements.Block;
import role.movements.GoBack;
import weapon.guns.AI_CatPower;
import weapon.guns.AI_Rifle;
import weapon.guns.Gun;

public class CrazyCatFactory implements RoleFactory {

	/* Hello , This is the template of the designation of an AI monster , 
	 * If you are creating your own monster ,
	 * Please update your monster's information 
	 * on the GitHub -> property.png or in the README.md
	 * */
	
	private static ImageSequence[][] actImgs; //singleton ! lazy instantiation
	
	@Override
	public Gun getGun() {
		//he has a gun but not finished
		return new AI_CatPower();
	}

	@Override
	public AI_Movement getMovement() {
		// And Shoot action
		return new AI_Shooting(new AI_Follow(new AI_Halt(new AI_Walk())));
	}

	@Override
	public Backable getBackable() {
		return new GoBack();
	}



	@Override
	public ImageSequence[][] getActionImages() {

		/* Singleton Pattern
		 * HALT,WALK,SHOOT,DIE ���ʧ@���޶���  
		 * NORTH,EAST,SOUTH,WEST ����V���޶���
		 * [�ʧ@][��V]
		 * �N���q�ƻs�K�W �H�K���~
		 * 
		 * �Ǫ����Ϥ��٨S�]�m!!!
		 */
		
		if ( actImgs == null ){
			actImgs = new ImageSequence[][]{
  /*Halt*/		{ new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",32) ,
					new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",32) ,
					new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",32) ,
					new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",32)} ,
  /*Walk*/		{ new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",32) ,
						new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",32) ,
						new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",32) ,
						new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",32)},
  				{ new ImageSequence( "pics/AI/CrazyCat/Shoot/West","png",88) ,
							new ImageSequence( "pics/AI/CrazyCat/Shoot/West","png",88) ,
							new ImageSequence( "pics/AI/CrazyCat/Shoot/East","png",88) ,
							new ImageSequence( "pics/AI/CrazyCat/Shoot/East","png",88)}
					};
		}
		
		return actImgs;
	}

	// test if the file path works
	public static void main(String[] argv){
		ImageSequence[][] actionImgs = new BallFactory().getActionImages();
	}

}
