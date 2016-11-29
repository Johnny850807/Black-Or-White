package role.abstractFactory;

import mvc.ImageSequence;
import role.movements.AI_Movement;
import role.movements.Backable;
import role.movements.GoBack;
import weapon.guns.Gun;
import weapon.guns.Rifle;

public class PlayerFactory implements RoleFactory {

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
	public int getDf() {
		return 10;
	}

	@Override
	public int getAtk() {
		// player don't have a touching-injury
		return 0;
	}

	@Override
	public ImageSequence[][] getActionImages() {
		/* HALT,WALK,SHOOT,DIE 為動作索引順序  
		 * NORTH,EAST,SOUTH,WEST 為方向索引順序
		 * [動作][方向]
		 * 將此段複製貼上 以免錯誤
		 */
		ImageSequence[][] actionImgs = {
  /*Halt*/		{ new ImageSequence( "Black Or White/pics/Player/Halt/East","png",1) ,
					new ImageSequence( "Black Or White/pics/Player/Halt/East","png",1) ,
					new ImageSequence( "Black Or White/pics/Player/Halt/East","png",1) ,
					new ImageSequence( "Black Or White/pics/Player/Halt/East","png",1)} ,
  /*Walk*/		{ new ImageSequence( "Black Or White/pics/Player/Walk/East","png",1) ,
						new ImageSequence( "Black Or White/pics/Player/Walk/East","png",1) ,
						new ImageSequence( "Black Or White/pics/Player/Walk/East","png",1) ,
						new ImageSequence( "Black Or White/pics/Player/Walk/East","png",1)}
					};
		return actionImgs;
	}

	// test if the file path works
	public static void main(String[] argv){
		ImageSequence[][] actionImgs = new PlayerFactory().getActionImages();
	}
		

}
