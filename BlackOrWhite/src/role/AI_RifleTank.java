package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BallFactory;
import role.abstractFactory.RifleTankFactory;
import role.abstractFactory.RoleFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_RifleTank extends AI {

	public AI_RifleTank(RoleFactory factory) {
		super(factory);
	}
	
	public AI_RifleTank(int x , int y , ActionType act , Dir dir) {
		super(new RifleTankFactory(),x,y,act,dir,6,14,58,74,800,150,60);
		/*offsetX = 6;
		offsetY = 14;    
		feetW = 58;
		feetH = 74;
		hp = 800;
		atk = 150;
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
