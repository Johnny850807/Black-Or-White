package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BallFactory;
import role.abstractFactory.EvilFactory;
import role.abstractFactory.RoleFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_Evil extends AI {
	public AI_Evil(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Evil(int x , int y , ActionType act , Dir dir) {
		super(new EvilFactory(),x,y,act,dir,8,8,46,64,600,120,35);
		/*offsetX = 8;
		offsetY = 8;
		feetW = 46;
		feetH = 64;
		hp = 600;
		atk = 120;
		df = 35;*/
	}

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -15;
					case SOUTH:
						return 15;
					case EAST:
						return 15;
					case WEST:
						return -15;
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
			else if (random.nextInt(100) > 97)  //2%ª®À»ºj
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
		}
			
	}

	@Override
	protected void dieProcess() {
		SoundManager.getSoundManager().playSound("sounds/monster/ball_die.wav");
	}

}
