package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import mvc.View;
import role.*;

public class BossStage extends Stage{
	public BossStage(Controller controller) {
		super(controller,25);
		int rate = View.crazyMode ? 2 : 1;
		
		for ( int i = 0 ; i < 2*rate ; i ++ )
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  

		monsters.add(new AI_CrazyCat(0,0,ActionType.HALT,Dir.NORTH));  //boss
		
		for ( int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		
		for ( int i = 0 ; i < 1*rate ; i ++ )
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		for ( int i = 0 ; i < 2*rate ; i ++ )
			monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		
		for ( int i = 0 ; i < 2*rate ; i ++ )
			monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		for ( int i = 0 ; i < 2*rate ; i ++ )
			monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		
		if(Controller.isNetWork()){
			rate *= 0.4;
			for ( int i = 0 ; i < 2*rate ; i ++ )
				monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
			
			for ( int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
			
			for ( int i = 0 ; i < 1*rate ; i ++ )
				monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
			for ( int i = 0 ; i < 2*rate ; i ++ )
				monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
			for ( int i = 0 ; i < 2*rate ; i ++ )
				monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  

		}
	}

	@Override
	public void playMusic() {
		if(View.crazyMode)
			SoundManager.getSoundManager().playStageMusic("sounds/stage/bossmusic2.wav");
		else
			SoundManager.getSoundManager().playStageMusic("sounds/stage/boss_music.wav");
	}

	@Override
	public void playerControl() {
		controller.curePlayer(380);
	}
}
