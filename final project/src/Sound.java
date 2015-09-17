
public class Sound extends Thread{

	private final String Path =  System.getProperty("user.dir") + "\\audio\\";
	
	private AudioPlayer audio;
	
	///Constructor
	public Sound(){
		audio = new AudioPlayer();
		initSound();
		
	}
	
	///Initialization the sound files
	private void initSound(){
		
	}
	
	///Play the shot sound
	public void shotSound(){
		audio.play(Path + "Laser.wav");
	}
}
