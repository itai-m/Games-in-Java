import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.lang.invoke.MutableCallSite;

public class Sound extends Thread{

	private final String SOUND_PATH =  System.getProperty("user.dir") + "\\audio\\";
	private final String IMAGES_PATH =  System.getProperty("user.dir") + "\\image\\";
	
	private AudioPlayer audio;
	private boolean mute;
	private Image muteImage;
	private Image noMuteImage;
	
	///Constructor
	public Sound(){
		super();
		mute = false;
		audio = new AudioPlayer();
		initSound();
		initImages();
	}
	
	///Initialization the sound files
	private void initSound(){
		
	}
	
	///Initialization the images files
	private void initImages(){
		muteImage = Toolkit.getDefaultToolkit().getImage(IMAGES_PATH + "SoundOff.png");
		noMuteImage = Toolkit.getDefaultToolkit().getImage(IMAGES_PATH + "SoundOn.png");
	}
	
	///Play the shot sound
	public void shotSound(){
		if (!mute){
			audio.play(SOUND_PATH + "Laser.wav");
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
	
	///Draw the sound symbol
	public void draw(Graphics2D g, ImageObserver ob,int width, int height){
		if (mute){
			g.drawImage(muteImage, 0, 0, width, height, ob);
		}
		else{
			g.drawImage(noMuteImage, 0, 0, width, height, ob);
		}
	}
	
	///Run the Thread
	public void run() {
		super.run(); 
	}
}
