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
		return null;
	}

	@Override
	public AI_Movement getMovement() {
		// And Shoot action
		return new AI_Halt(new AI_Walk());
	}

	@Override
	public Backable getBackable() {
		return new GoBack();
	}



	@Override
	public ImageSequence[][] getActionImages() {

		/* Singleton Pattern
		 * HALT,WALK,SHOOT,DIE 為動作索引順序  
		 * NORTH,EAST,SOUTH,WEST 為方向索引順序
		 * [動作][方向]
		 * 將此段複製貼上 以免錯誤
		 * 
		 * 怪物的圖片還沒設置!!!
		 */
		
		if ( actImgs == null ){
			actImgs = new ImageSequence[][]{
  /*Halt*/		{ new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",4) ,
					new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",4) ,
					new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",4) ,
					new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",4)} ,
  /*Walk*/		{ new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",4) ,
						new ImageSequence( "pics/AI/CrazyCat/Walk/East","png",4) ,
						new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",4) ,
						new ImageSequence( "pics/AI/CrazyCat/Walk/West","png",4)}
					};
		}
		
		return actImgs;
	}

	// test if the file path works
	public static void main(String[] argv){
		ImageSequence[][] actionImgs = new BallFactory().getActionImages();
	}

}
