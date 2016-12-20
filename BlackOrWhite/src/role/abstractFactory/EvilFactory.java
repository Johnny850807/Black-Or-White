package role.abstractFactory;

import mvc.ImageSequence;
import mvc.Log;
import role.movements.AI_Halt;
import role.movements.AI_Movement;
import role.movements.AI_Walk;
import role.movements.Backable;
import role.movements.Block;
import role.movements.GoBack;
import weapon.guns.Gun;

public class EvilFactory implements RoleFactory {
	
	private static ImageSequence[][] actImgs; //singleton ! lazy instantiation
	
	@Override
	public Gun getGun() {
		return null;
	}

	@Override
	public AI_Movement getMovement() {
		return new AI_Halt(new AI_Walk());
	}

	@Override
	public Backable getBackable() {
		return new Block();
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
  /*Halt*/		{ new ImageSequence( "pics/AI/Evil/Walk/East","png",8) ,
					new ImageSequence( "pics/AI/Evil/Walk/East","png",8) ,
					new ImageSequence( "pics/AI/Evil/Walk/West","png",8) ,
					new ImageSequence( "pics/AI/Evil/Walk/West","png",8)} ,
  /*Walk*/		{ new ImageSequence( "pics/AI/Evil/Walk/East","png",4) ,
						new ImageSequence( "pics/AI/Evil/Walk/East","png",8) ,
						new ImageSequence( "pics/AI/Evil/Walk/West","png",8) ,
						new ImageSequence( "pics/AI/Evil/Walk/West","png",8)}
					};
			Log.d("create ball image");
		}
		
		return actImgs;
	}

	// test if the file path works
	public static void main(String[] argv){
		ImageSequence[][] actionImgs = new BallFactory().getActionImages();
	}
}
