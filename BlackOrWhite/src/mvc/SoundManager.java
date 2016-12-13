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

	public static SoundManager getSoundManager(){
		return soundManager;
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
		 try {
			new Thread(new Runnable() {
				  // The wrapper thread is unnecessary, unless it bl  ocks on the
				  // Clip finishing; see comments.
				 Clip soundClip = AudioSystem.getClip();
				    public void run() {
				      try {
				    	soundClip.open(AudioSystem.getAudioInputStream(new File("sounds/shoot/rifle.wav").toURL()));
				  		soundClip.start();
				      } catch (Exception e) {
				        soundClip.close();
				     
				      }
				    }
				  }
			 ).start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void playMachineSound(){
		try {
			new Thread(new Runnable() {
				  // The wrapper thread is unnecessary, unless it bl  ocks on the
				  // Clip finishing; see comments.
				 Clip soundClip = AudioSystem.getClip();
				    public void run() {
				      try {
				    	soundClip.open(AudioSystem.getAudioInputStream(new File("sounds/shoot/machineGun.wav").toURL()));
				  		soundClip.start();
				      } catch (Exception e) {
				        soundClip.close();
				     
				      }
				    }
				  }
			 ).start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playSniperSound(){
		try {
			new Thread(new Runnable() {
				  // The wrapper thread is unnecessary, unless it bl  ocks on the
				  // Clip finishing; see comments.
				 Clip soundClip = AudioSystem.getClip();
				    public void run() {
				      try {
				    	soundClip.open(AudioSystem.getAudioInputStream(new File("sounds/shoot/sniperRifle.wav").toURL()));
				  		soundClip.start();
				      } catch (Exception e) {
				        soundClip.close();
				     
				      }
				    }
				  }
			 ).start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
