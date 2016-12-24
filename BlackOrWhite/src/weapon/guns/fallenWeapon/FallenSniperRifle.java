package weapon.guns.fallenWeapon;

import mvc.ImageSequence;
import weapon.guns.MachineGun;
import weapon.guns.SniperRifle;

public class FallenSniperRifle extends FallenItem{
	private static ImageSequence[][] actionImgs = new ImageSequence[][]{
		{ new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1)} ,
{ new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_sniper","png",1)}};
	public FallenSniperRifle(int x, int y) {
		super(x, y ,120,32,new SniperRifle(),actionImgs[0][0]);
	}
	public static void main(String[] args) {
		new FallenSniperRifle(5,3);
	}

}
