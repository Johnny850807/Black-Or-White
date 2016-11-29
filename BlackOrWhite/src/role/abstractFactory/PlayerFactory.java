package role.abstractFactory;

import mvc.ImageSequence;
import role.movements.AI_Movement;
import role.movements.Backable;
import role.movements.GoBack;
import weapon.guns.Gun;
import weapon.guns.Rifle;

public class PlayerFactory implements RoleFactory {

	private static ImageSequence[][] actImgs; //singleton ! lazy instantiation
	
	@Override
	public Gun getGun() {
		// Default Rifle gun
		return new Rifle();
	}

	@Override
	public AI_Movement getMovement() {
		// Player don't have to acquire a movement
		return null;
	}

	@Override
	public Backable getBackable() {
		return new GoBack();
	}

	@Override
	public int getHp() {
		return 500;
	}

	@Override
	public ImageSequence[][] getActionImages() {
		
		/* Singleton Pattern
		 * HALT,WALK,SHOOT,DIE 為動作索引順序  
		 * NORTH,EAST,SOUTH,WEST 為方向索引順序
		 * [動作][方向]
		 * 將此段複製貼上 以免錯誤
		 */
		
		if ( actImgs == null ){
			actImgs = new ImageSequence[][]{
  /*Halt*/		{ new ImageSequence( "pics/Player/Halt/East","png",1) ,
					new ImageSequence( "pics/Player/Halt/East","png",1) ,
					new ImageSequence( "pics/Player/Halt/East","png",1) ,
					new ImageSequence( "pics/Player/Halt/East","png",1)} ,
  /*Walk*/		{ new ImageSequence( "pics/Player/Walk/East","png",1) ,
						new ImageSequence( "pics/Player/Walk/East","png",1) ,
						new ImageSequence( "pics/Player/Walk/East","png",1) ,
						new ImageSequence( "pics/Player/Walk/East","png",1)}
					};
		}
		
		return actImgs;
	}

	// test if the file path works
	public static void main(String[] argv){
		ImageSequence[][] actionImgs = new PlayerFactory().getActionImages();
	}
		

}
