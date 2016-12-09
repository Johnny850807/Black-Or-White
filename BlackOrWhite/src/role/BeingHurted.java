package role;

public class BeingHurted extends Thread {
	private Role role;
	private float second;
	public BeingHurted(Role role , float second){
		this.role = role;
		this.second = second;
		role.isBeingHurted = true;
	}
	@Override
	public void run(){
		try{
			Thread.sleep((int)(second*1000));
			role.isBeingHurted = false;
		}catch(InterruptedException err){
			err.printStackTrace();
		}
	}
}
