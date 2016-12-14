package role;

import java.util.Random;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.gameObject.GameObjects;
import role.abstractFactory.BallFactory;
import role.abstractFactory.RoleFactory;
import role.abstractFactory.SnowBallFactory;
import weapon.guns.fallenWeapon.FallenMachine;
import weapon.guns.fallenWeapon.FallenSniperRifle;

public class AI_SnowBall extends AI {

	//���|�ܪ��ƭ�
		public static int df;  //���m�O
		public static int atk;  //�Ǫ���Ĳ�I���� �Y���a�h��0

		public AI_SnowBall(RoleFactory factory) {
			super(factory);
		}
		
		public AI_SnowBall(int x , int y , ActionType act , Dir dir) {
			super(new SnowBallFactory(),x,y,act,dir,1,10,70,55,300,70,15);
			/*offsetX = 1;
			offsetY = 10;
			feetW = 70;
			feetH = 55;
			hp = 300;
			atk = 70;
			df = 33;*/
		} 

		@Override
		protected int getMovingDistance(ActionType act , Dir dir) {
			switch(act)
			{
				case WALK:
					switch(dir){
						case NORTH:
							return -12;
						case SOUTH:
							return 12;
						case EAST:
							return 12;
						case WEST:
							return -12;
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
				if (random.nextInt(100) > 90)
					Controller.getController().fallGun(new FallenSniperRifle(x,y));
				else if(random.nextInt(100) > 95) 
					Controller.getController().fallGun(new FallenMachine(x,y));
			}
				
		}

		@Override
		protected void dieProcess() {
			SoundManager.getSoundManager().playSound("sounds/monster/ball_die.wav");
		}

}
