package role;

import mvc.ImageSequence;
import role.abstractFactory.RoleFactory;

public class AI_Ball extends AI {
	//���|�ܪ��ƭ�
	public static int df;  //���m�O
	public static int atk;  //�Ǫ���Ĳ�I���� �Y���a�h��0

	public AI_Ball(RoleFactory factory) {
		super(factory);
	}
	
	public AI_Ball(RoleFactory factory ,int x , int y) {
		super(factory,x,y);
	}

	@Override
	protected void die() {
		
		
	}

}
