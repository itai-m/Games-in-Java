import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Shot extends Sprite{

	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	private final static int SPEED = 10;
	private final int[] SPRITE_WIDTH = {0, 50, 95, 145, 224, 307, 374};
	private final int STEP_PER_PIC = 14;
	private final int SPRITE_HEIGHT = 48;
	private final int MAX_STEP = STEP_PER_PIC * 7;
	
	private Image imag;
	private int height;
	private int width;
	private int step;
	
	public Shot(){
		super();
		initImages();
	}
	
	public Shot(float posX, float posY, float dirX, float dirY, int width, int height, int boardWidth, int boardHeight){
		super(posX, posY, dirX, dirY, SPEED, boardWidth, boardHeight);
		this.height = height;
		this.width = width;
		step = 0;
		initImages();
	}
	
	///Initialization the images
	private void initImages(){
		imag = Toolkit.getDefaultToolkit().getImage(Path + "shot.png");
	}
	
	///Check if the shot pass is time
	public boolean isOver(){
		if (step >= MAX_STEP)
			return true;
		return false;
	}
	
	///Draw the shot
	public void draw(Graphics2D g, ImageObserver ob){
		int x = (int)getPosition().getX();
		int y = (int)getPosition().getY();
		int width = this.width;
		int height = this.height;
		if (getDirection().getX() == -1){
			width *= -1;
		}
		else if (getDirection().getY() == 1){
			width *= -1;
			height *= -1;
		}
		else if (getDirection().getY() == -1){
			
		}
		
		if (step < STEP_PER_PIC){
			g.drawImage(imag, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH[0], 0, SPRITE_WIDTH[1], SPRITE_HEIGHT, ob);
		}
		else if (step < STEP_PER_PIC * 2){
			g.drawImage(imag, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH[1], 0, SPRITE_WIDTH[2], SPRITE_HEIGHT, ob);
		}
		else if (step < STEP_PER_PIC * 3){
			g.drawImage(imag, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH[2], 0, SPRITE_WIDTH[3], SPRITE_HEIGHT, ob);
		}
		else if (step < STEP_PER_PIC * 4){
			g.drawImage(imag, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH[3], 0, SPRITE_WIDTH[4], SPRITE_HEIGHT, ob);
		}
		else if (step < STEP_PER_PIC * 5){
			g.drawImage(imag, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH[4], 0, SPRITE_WIDTH[5], SPRITE_HEIGHT, ob);
		}
		else if (step >= STEP_PER_PIC * 5){
			g.drawImage(imag, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH[5], 0, SPRITE_WIDTH[6], SPRITE_HEIGHT, ob);
		}
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
	
	///Move the shot
	public void move(){
		super.move();
		step++;
	}
}
