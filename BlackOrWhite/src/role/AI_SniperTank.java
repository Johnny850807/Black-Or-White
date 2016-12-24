package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.gameObject.GameObjects;
import role.abstractFactory.RifleTankFactory;
import role.abstractFactory.RoleFactory;
import role.abstractFactory.SniperTankFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_SniperTank extends AI{
	public AI_SniperTank(RoleFactory factory) {
		super(factory);
	}
	
	public AI_SniperTank(int x , int y , ActionType act , Dir dir) {
		super(new SniperTankFactory(),x,y,act,dir,1,5,83,75,600,70,60);
		/*offsetX = 1;
		offsetY = 5;    
		feetW = 83;
		feetH = 75;
		hp = 600;
		atk = 70;
		df = 60;*/
	} 

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -1;
					case SOUTH:
						return 1;
					case EAST:
						return 1;
					case WEST:
						return -1;
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
			if(random.nextInt(100) > 80)
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
			else if (random.nextInt(100) > 80)  
				Controller.getController().fallGun(new FallenMachine(x,y));
		}
			
	}

	@Override
	protected void dieProcess() {
	}
}
