package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.View;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BallFactory;
import role.abstractFactory.CrazyCatFactory;
import role.abstractFactory.RoleFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_CrazyCat extends AI {
	public AI_CrazyCat(RoleFactory factory) {
		super(factory);
	}
	
	public AI_CrazyCat(int x , int y , ActionType act , Dir dir) {
		super(new CrazyCatFactory(),x,y,act,dir,28,12,70,81,3800,120,60);
		/*offsetX = 28;
		offsetY = 12;
		feetW = 91;
		feetH = 81;
		hp = 3800;
		atk = 120;
		df = 60;*/
	} 

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		int distance = View.crazyMode ? 6 : 4;
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -1*distance;
					case SOUTH:
						return distance;
					case EAST:
						return distance;
					case WEST:
						return -1*distance;
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
			if(random.nextInt(100) > 84) //15%¾÷Ãöºj
				Controller.getController().fallGun(new FallenMachine(x,y));
			else if (random.nextInt(100) > 97)  //2%ª®À»ºj
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
		}
			
	}

	@Override
	protected void dieProcess() {
		//SoundManager.getSoundManager().playSound("sounds/monster/ball_die.wav");
	}
}
