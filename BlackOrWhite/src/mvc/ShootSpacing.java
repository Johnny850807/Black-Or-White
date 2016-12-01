package mvc;

import mvc.View.ButtonsPanel;

class ShootSpacing extends Thread{
	private ButtonsPanel panel;
	public ShootSpacing(ButtonsPanel panel){
		this.panel = panel;
	}
	@Override
	public void run(){
		panel.isShootSpacing  = true;
		try {Thread.sleep(750);} catch (InterruptedException e) {e.printStackTrace();}
		panel.isShootSpacing = false;
	}
}