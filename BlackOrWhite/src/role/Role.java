package role;

import mvc.*;
import role.abstractFactory.RoleFactory;
import role.movements.AI_Movement;
import role.movements.Backable;
import weapon.guns.Gun;

public abstract class Role {
	private Model model;
	private ImageSequence[][] actionImgs;
	private Backable backable; //受到傷害後的行為
	private RoleFactory factory; //抽象角色工廠
	private Gun gun;  //角色擁有的槍 若沒有為null
	private AI_Movement movement; //選擇動作 玩家此變數為null
	public int x;
	public int y;
	public int hp;  //生命
	public int df;  //防禦力
	public int atk;  //怪物的觸碰攻擊 若玩家則為0
	public ActionType curAct;
	public Dir curDir;
	
	public Role(RoleFactory factory){
		this.factory = factory;
		
	}

	
	//玩家輸入的命令
	protected class Request{
		public ActionType act;
		public Dir dir;
		public Request(ActionType act , Dir dir){
			this.act = act;
			this.dir = dir;
		}
	}
	
}
