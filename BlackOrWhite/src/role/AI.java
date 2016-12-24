package role;

import java.awt.Dimension;

import mvc.ActionType;
import mvc.Dir;
import role.abstractFactory.RoleFactory;

public abstract class AI extends Role {
	protected final int moveDuration = 50;  //動作總長度
	protected int moveCountDown;  //計算中時間條
	public AI(RoleFactory factory) {
		super(factory);
	}
	
	public AI(RoleFactory factory,int x, int y , ActionType act , Dir dir , 
			int offsetX , int offsetY , int feetW , int feetH , int hp , int atk , int df){
		super(factory,x,y,act,dir,offsetX,offsetY,feetW,feetH,hp,atk,df);
	}
	public void moveDurationCountDown(int countdown){
		//每 moveDuration 個更新換一次動作
		moveCountDown = (moveCountDown-countdown) < 0 ? moveDuration : moveCountDown-countdown;
	}
	public void moveDurationCountDown(){
		//每 moveDuration 個更新換一次動作
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
	
	//持續目前動作
	public void keepCurrentMove(){
		if ( curAct != ActionType.SHOOT)
			getMoved(curAct,curDir);
	}
	
	public void setDimension(Dimension d){
		//設置座標並更新
		x = (int) d.getWidth();
		y = (int) d.getHeight();
		model.setState(x, y, curAct, curDir, model.getiS());
	}
	
	protected  void die(){
		if ( hp <= 0 ){
			model.delete();  //在控制器中刪除掉自己
			dieProcess();
			throwGun();
		}
	}
	protected abstract void throwGun();  //如果怪物死亡有機率掉槍
	protected abstract void dieProcess();  //死亡後要做的事情
}
