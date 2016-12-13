package weapon.guns.fallenWeapon;

import mvc.ImageSequence;
import weapon.guns.MachineGun;

public class FallenMachine extends FallenItem{
	private static ImageSequence[][] actionImgs  = new ImageSequence[][]{
		{ new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1) ,
			new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1)} ,
{ new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1) ,
				new ImageSequence( "pics/Fallen_Weapon/fall_machine","png",1)}};
				
	public FallenMachine(int x, int y) {
		super(x, y ,100,50,new MachineGun(),actionImgs[0][0]);
	}

	
	
}
