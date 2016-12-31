package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.View;
import role.AI_Evil;
import role.AI_RifleTank;
import role.AI_SniperTank;
import role.AI_SnowBall;
import role.AI_SnowBallEX;

public class Stage5 extends Stage{
	public Stage5(Controller controller) {
		super(controller,22);
		int rate = View.crazyMode ? 2 : 1;
		
		for(int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
		for(int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));
		for(int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
		for(int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		for(int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));
		for(int i = 0 ; i < 2*rate ; i ++ )
			monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH)); 
		for(int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2*rate ; i ++ )  
			monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));
		for ( int i = 0 ; i < 2*rate ; i ++ )  
			monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		
		if(Controller.isNetWork()){
			rate *= 0.4;
			for(int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
			for(int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));
			for(int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH));
			for(int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
			for(int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));
			for(int i = 0 ; i < 2*rate ; i ++ )
				monsters.add(new AI_Evil(0,0,ActionType.HALT,Dir.NORTH)); 
			for(int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_SnowBallEX(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 2*rate ; i ++ )  
				monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));
			for ( int i = 0 ; i < 2*rate ; i ++ )  
				monsters.add(new AI_SnowBall(0,0,ActionType.HALT,Dir.NORTH));
		}
	}

	@Override
	public void playMusic() {
	}

	@Override
	public void playerControl() {
		controller.curePlayer(150);
	}
}
