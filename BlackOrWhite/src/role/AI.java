package role;

import java.awt.Dimension;

import mvc.ActionType;
import mvc.Dir;
import role.abstractFactory.RoleFactory;

public abstract class AI extends Role {
	protected final int moveDuration = 50;  //�ʧ@�`����
	protected int moveCountDown;  //�p�⤤�ɶ���
	public AI(RoleFactory factory) {
		super(factory);
	}
	
	public AI(RoleFactory factory,int x, int y , ActionType act , Dir dir , 
			int offsetX , int offsetY , int feetW , int feetH , int hp , int atk , int df){
		super(factory,x,y,act,dir,offsetX,offsetY,feetW,feetH,hp,atk,df);
	}
	public void moveDurationCountDown(int countdown){
		//�C moveDuration �ӧ�s���@���ʧ@
		moveCountDown = (moveCountDown-countdown) < 0 ? moveDuration : moveCountDown-countdown;
	}
	public void moveDurationCountDown(){
		//�C moveDuration �ӧ�s���@���ʧ@
		moveCountDown = moveCountDown == 0 ? moveDuration : moveCountDown - 1;
	}
	public boolean isTimeToChangeMove(){
		return moveCountDown == moveDuration;
	}
	@Override
	public void run(){
		/*Thread template method
		 * 1. check if hurted (hook)
		 * 2. choose the movement (final)
		 */
		hurtedJudgement();
		chooseMovement();
		moveDurationCountDown();
		die();
	}
	
	private final void chooseMovement(){
		movement.randomChoose(this);
	}
	
	//����ثe�ʧ@
	public void keepCurrentMove(){
		if ( curAct != ActionType.SHOOT)
			getMoved(curAct,curDir);
	}
	
	public void setDimension(Dimension d){
		//�]�m�y�Шç�s
		x = (int) d.getWidth();
		y = (int) d.getHeight();
		model.setState(x, y, curAct, curDir, model.getiS());
	}
	
	protected  void die(){
		if ( hp <= 0 ){
			model.delete();  //�b������R�����ۤv
			dieProcess();
			throwGun();
		}
	}
	protected abstract void throwGun();  //�p�G�Ǫ����`�����v���j
	protected abstract void dieProcess();  //���`��n�����Ʊ�
}
