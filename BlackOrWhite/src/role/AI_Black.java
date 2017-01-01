package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BlackFactory;
import role.abstractFactory.EvilFactory;
import role.abstractFactory.RoleFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_Black extends AI{
	public AI_Black(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Black(int x , int y , ActionType act , Dir dir) {
		super(new BlackFactory(),x,y,act,dir,1,5,53,77,1800,280,60);
		/*offsetX = 1;
		offsetY = 5;
		feetW = 53;
		feetH = 77;
		hp = 1800;
		atk = 280;
		df = 60;*/
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
			if(random.nextInt(100) > 97) //20%機關槍
				Controller.getController().fallGun(new FallenMachine(x,y));
			else if (random.nextInt(100) > 80)  //2%狙擊槍
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
		}
			
	}
	
	@Override
	public void moveDurationCountDown(){
		//每 moveDuration 個更新換一次動作
		moveCountDown = moveCountDown <= 0 ? moveDuration : moveCountDown - 3;
	}

	@Override
	protected void dieProcess() {
	}
}
