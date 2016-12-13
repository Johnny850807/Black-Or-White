package role;

import java.util.Random;

import mvc.*;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BallFactory;
import role.abstractFactory.RoleFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;
					//�~��AI 
public class AI_Ball extends AI {
	//���|�ܪ��ƭ�
	public static int df;  //���m�O
	public static int atk;  //�Ǫ���Ĳ�I���� �Y���a�h��0

	public AI_Ball(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Ball(int x , int y , ActionType act , Dir dir) {
		super(new BallFactory(),x,y,act,dir,1,3,64,59,300,80,20);
		/*offsetX = 1;
		offsetY = 3;
		feetW = 64;
		feetH = 59;
		hp = 300;
		atk = 0;
		df = 20;*/
	} 

	@Override
	protected int getMovingDistance(ActionType act , Dir dir) {
		switch(act)
		{
			case WALK:
				switch(dir){
					case NORTH:
						return -8;
					case SOUTH:
						return 8;
					case EAST:
						return 8;
					case WEST:
						return -8;
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
			if(random.nextInt(100) > 84) //15%�����j
				Controller.getController().fallGun(new FallenMachine(x,y));
			else if (random.nextInt(100) > 97)  //2%�����j
				Controller.getController().fallGun(new FallenSniperRifle(x,y));
		}
			
	}

	@Override
	protected void dieProcess() {
		SoundManager.getSoundManager().playSound("sounds/monster/ball_die.wav");
	}

}
