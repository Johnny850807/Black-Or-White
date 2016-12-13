package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	private static SoundManager soundManager = new SoundManager();
	private static Clip backgroundMusic;  //背景音樂 一次只能撥一首 
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
	public void playSound(String url){
		 try {   
			new Thread(new Runnable() {
				  // The wrapper thread is unnecessary, unless it bl  ocks on the
				  // Clip finishing; see comments.
				 Clip soundClip = AudioSystem.getClip();
				    public void run() {
				      try {
				    	soundClip.open(AudioSystem.getAudioInputStream(new File(url).toURL()));
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
	
	public void playStageMusic(String url){
		//播放背景音樂 一次只能存在一首

		try {
			if ( backgroundMusic != null && backgroundMusic.isOpen() )
				backgroundMusic.close();
			backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(AudioSystem.getAudioInputStream(new File(url).toURL()));
			backgroundMusic.loop(-1);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
}
