
public class Sound {

	private final String Path =  System.getProperty("user.dir") + "\\audio\\";
	
	
	///Constructor
	public Sound(){
		initSound();
	}
	
	///Initialization the sound files
	private void initSound(){
		
	}
	
	///Play the shot sound
	public void shotSound(){
		AudioPlayer.play(Path + "Laser.wav");
	}
}
