package weapon.guns.fallenWeapon;

import mvc.ImageSequence;
import weapon.guns.MachineGun;
import weapon.guns.SniperRifle;

public class FallenSniperRifle extends FallenItem{
	private static ImageSequence[][] actionImgs = new ImageSequence[][]{
		{ new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1)} ,
{ new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_sniper0","png",1)}

};
	public FallenSniperRifle(int x, int y) {
		super(x, y ,100,56,new SniperRifle(),actionImgs[0][0]);
	}


}
