package role;

import mvc.*;
import role.abstractFactory.RoleFactory;
					//繼承AI 
public class AI_Ball extends AI {
	//不會變的數值
	public static int df;  //防禦力
	public static int atk;  //怪物的觸碰攻擊 若玩家則為0

	public AI_Ball(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Ball(RoleFactory factory ,int x , int y , ActionType act , Dir dir) {
		super(factory,x,y,act,dir);
	} 

	@Override
	protected void die() {
	//這邊的程式碼 是繼承AI的	
		
	}

	@Override
	int getMovingDistance(ActionType act , Dir dir) {
		//每次移動距離
		return 5;
	}

}
