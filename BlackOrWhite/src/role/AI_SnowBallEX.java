package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.gameObject.GameObjects;
import role.abstractFactory.RoleFactory;
import role.abstractFactory.SnowBallExFactory;
import role.abstractFactory.SnowBallFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_SnowBallEX extends AI {

	//不會變的數值
	public static int df;  //防禦力
	public static int atk;  //怪物的觸碰攻擊 若玩家則為0
	
	public AI_SnowBallEX(int x , int y , ActionType act , Dir dir) {
		super(new SnowBallExFactory(),x,y,act,dir,1,10,70,55,330,55,90);
		/*offsetX = 1;
		offsetY = 10;
		feetW = 70;
		feetH = 55;
		hp = 330;
		atk = 55;
		df = 90;*/
	} 

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -2;
					case SOUTH:
						return 2;
					case EAST:
						return 2;
					case WEST:
						return -2;
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
			if (random.nextInt(100) > 79) 
				Controller.getController().fallGun(new FallenMachine(x,y));
			else if(random.nextInt(100) > 90) 
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
		}
			
	}

	@Override
	protected void dieProcess() {
		SoundManager.getSoundManager().playSound("sounds/monster/ball_die.wav");
	}

}
