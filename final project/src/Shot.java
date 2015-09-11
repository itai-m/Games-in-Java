import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Shot extends Sprite{

	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	private final static int SPEED = 6;
	private final int SPRITE_WIDTH = 32;
	private final int SPRITE_HEIGHT = 48;
	
	private int height;
	private int width;
	private int step;
	
	public Shot(){
		super();
	}
	
	public Shot(int x, int y, int width, int height, int boardWidth, int boardHeight){
		//TODO
	}
	
	public void draw(Graphics2D g, ImageObserver ob){
		//TODO
	}

	///Get the height
	public int getHeight() {
		return height;
	}

	///Set the height
	public void setHeight(int height) {
		this.height = height;
	}

	///Get the width
	public int getWidth() {
		return width;
	}

	///Set the width
	public void setWidth(int width) {
		this.width = width;
	}
}
