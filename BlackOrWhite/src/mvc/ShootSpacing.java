package mvc;

import mvc.View.ButtonsPanel;
import role.Player;
import role.Role;

 public class ShootSpacing extends Thread{
	private Role player;
	public ShootSpacing(Role player){
		this.player = player;
	}
	@Override
	public void run(){
		player.setShootSpacing(true);  
		try {Thread.sleep(player.getGun().getSpacing());} catch (InterruptedException e) {e.printStackTrace();}
		player.setShootSpacing(false);  
	}
}