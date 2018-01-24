package music;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class MusicPlayer {
	private Clip clip; 
	private AudioInputStream currentSound;
	private DataLine.Info dataLineInfo;
	private Object loadReference;   
	private AudioFormat format;
	private AudioPlayerCallback callbackTartet;
	private Object callbackObj ;
	private boolean isPause;
	public MusicPlayer(){
		
	}
	public void setCallbackTartet(AudioPlayerCallback cb, Object obj){
		callbackTartet = cb;
		callbackObj = obj;
	}
	
	public boolean loadAudio(String filePath){
		try {
			loadAudio(new File(filePath));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public void loadAudio(File file) throws Exception{      
		currentSound = AudioSystem.getAudioInputStream(file);    
		controlAudio();
	}
	protected void controlAudio() throws Exception {   
		format = currentSound.getFormat();   
		dataLineInfo = new DataLine.Info(Clip.class, format, ((int) currentSound.getFrameLength() * format.getFrameSize()));   
		clip = (Clip) AudioSystem.getLine(dataLineInfo);   
		clip.open(currentSound);   
		clip.addLineListener(   
				new LineListener() {   
					public void update(LineEvent event) {
						if (event.getType().equals(LineEvent.Type.STOP)){
							if(!isPause){
								if(callbackTartet != null){
									callbackTartet.audioPlayEnd(callbackObj);
								}
								close();
							}
						}   
					}   
				}   
		);   
	}
	public void play(){
		if(clip != null){
			clip.setFramePosition(0);  
			clip.loop(0);
		}
	}
	public void resume(){
		isPause = false;
		
		if(clip != null){
			clip.setFramePosition(clip.getFramePosition());
			clip.loop(0);
		}
		
	}
	public void pause(){
		isPause = true;
		if(clip != null){
			clip.stop();
		}
	}
	public void stop(){
		if(clip != null){
			clip.stop();   
		}
	}  
	public boolean isAudioLoaded(){   
		return loadReference!= null;   
	}   
	public Clip getClip() {   
		return clip;   
	}
	public void close(){   
		try {   
			if (clip != null)   
				clip.close();   
			if (currentSound != null)   
				currentSound.close();   
			loadReference = null;   
		} catch (Exception e){ 
			e.printStackTrace();   
		}
		currentSound = null;   
		clip = null;     
		dataLineInfo = null;   
		loadReference = null;
		callbackTartet = null;
		callbackObj = null;
	}

}

