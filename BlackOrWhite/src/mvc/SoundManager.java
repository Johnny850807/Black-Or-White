package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	private static SoundManager soundManager = new SoundManager();
	private static Clip soundClip;

	public SoundManager(){
		initiate();
	}
	public static SoundManager getSoundManager(){
		return soundManager;
	}
	private void initiate(){
		try {
			soundClip = AudioSystem.getClip();
			try {
				soundClip.open(AudioSystem.getAudioInputStream(new File("sounds/shoot/rifle.wav").toURL()));
			} catch (IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} 
	}
	/*public void playMainMusic(){
		 new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try {
			  		rifleSound.start();
			  	
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();

	}*/
	public void playRifleSound(){
		 new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it bl  ocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try {
			    	/**if(soundClip.isOpen()){
			    		soundClip.close();
			    	}*/
			
			  		soundClip.loop(1);
			      } catch (Exception e) {
			        soundClip.close();
			     
			      }
			    }
			  }
		 ).start();
	}
	public void playMachineSound(){
		 new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it bl  ocks on the
			  // Clip finishing; see comments.
			 public void run() {
			      try {
			    	/**if(soundClip.isOpen()){
			    		soundClip.close();
			    	}*/
			
			  		soundClip.loop(-1);
			      } catch (Exception e) {
			        soundClip.close();
			     
			      }
			    }
			  }).start();
	}
}
