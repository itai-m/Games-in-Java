
public class Sound extends Thread{

	private final String PATH =  System.getProperty("user.dir") + "\\audio\\";
	
	private AudioPlayer audio;
	private boolean mute;
	
	///Constructor
	public Sound(){
		mute = false;
		audio = new AudioPlayer();
		initSound();
		
	}
	
	///Initialization the sound files
	private void initSound(){
		
	}
	
	///Play the shot sound
	public void shotSound(){
		if (!mute){
			audio.play(PATH + "Laser.wav");
		}
	}
	
	///Initialization one sound file
	private void initFile(){
		
	}
	
	///Change the status of the mute
	public void changeMute(){
		mute = !mute;
	}

	///Check if the sound is mute
	public boolean isMute() {
		return mute;
	}
}
