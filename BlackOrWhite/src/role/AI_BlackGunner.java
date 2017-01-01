package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BlackGunnerFactory;
import role.abstractFactory.EvilFactory;
import role.abstractFactory.RoleFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_BlackGunner extends AI{
	public AI_BlackGunner(RoleFactory factory) {
		super(factory);
	}
	
	public AI_BlackGunner(int x , int y , ActionType act , Dir dir) {
		super(new BlackGunnerFactory(),x,y,act,dir,5,6,47,76,550,180,50);
		/*offsetX = 5;
		offsetY = 6;
		feetW = 47;
		feetH = 76;
		hp = 550;
		atk = 180;
		df = 50;*/
	}

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -3;
					case SOUTH:
						return 3;
					case EAST:
						return 3;
					case WEST:
						return -3;
				}
				break;
			default:
				return 0;
		}
		return 0;
	}

	@Override
	protected void throwGun() {
		Random random = new Random();
		if (GameObjects.getGameObjects().fallenItemSize() < 2 )
		{
			if(random.nextInt(100) > 80) //20%¾÷Ãöºj
				Controller.getController().fallGun(new FallenMachine(x,y));
			else if (random.nextInt(100) > 80)  //2%ª®À»ºj
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
		}
			
	}

	@Override
	protected void dieProcess() {
	}
}
