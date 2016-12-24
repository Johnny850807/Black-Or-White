package mvc.stage;

import mvc.ActionType;
import mvc.Controller;
import mvc.Dir;
import mvc.SoundManager;
import role.*;

public class BossStage extends Stage{
	public BossStage(Controller controller) {
		super(controller,25);
		

		monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH)); 
		
		monsters.add(new AI_CrazyCat(0,0,ActionType.HALT,Dir.NORTH));  //boss
		
		monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		
		monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		
		monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_RifleTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		monsters.add(new AI_SniperTank(0,0,ActionType.HALT,Dir.NORTH));  
		 
		
	}

	@Override
	public void playMusic() {
		SoundManager.getSoundManager().playStageMusic("sounds/stage/boss_music.wav");
	}

	@Override
	public void playerControl() {
		controller.curePlayer(380);
	}
}
