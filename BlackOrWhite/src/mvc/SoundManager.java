package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.print.attribute.standard.Media;
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
	
	public void playSound(String url){
		 try {   
			new Thread(new Runnable() {
				  // The wrapper thread is unnecessary, unless it blocks on the
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
		// 不播放背景音樂
	}

	
}
