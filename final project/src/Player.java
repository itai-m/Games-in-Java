import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Player extends Sprite{
	
	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	private final static int SPEED = 6;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWN = 4;
	private final int SPRITE_WIDTH = 32;
	private final int SPRITE_HEIGHT = 48;
	private final int SPRITE_DOWN_ROW = 0;
	private final int SPRITE_UP_ROW = 3;
	private final int SPRITE_LEFT_ROW = 1;
	private final int SPRITE_RIGHT_ROW = 2;
	private final int SPRITE_COLS = 4;
	private final int FALL_SPEED = 10;

	private int height;
	private int width;
	private int turnTo;
	private Image player;
	private int step;
	
	///Default constructor
	public Player (){
		super();
		turnTo = RIGHT;
		height = 10;
		width = 10;
		step = 0;
		initImages();
	}
	
	///Constructor
	public Player(int x, int y, int width, int height, int boardWidth, int boardHeight){
		super(x, y, 0, 1, SPEED, boardWidth, boardHeight);
		this.height = height;
		this.width = width;
		turnTo = DOWN;
		step = 0;
		initImages();
	}
	
	///Initialization the images
	private void initImages(){
		player = Toolkit.getDefaultToolkit().getImage(Path + "player.png");
	}

	///Turn to the left, return false if is already turn to the left, otherwise true
	public boolean turnLeft(){
		super.setDirection(new Vector2f (-1, 0));
		return turnToSide(LEFT);
	}

	///Turn to the right, return false if is already turn to the right, otherwise true
	public boolean turnRight(){
		super.setDirection(new Vector2f (1, 0));
		return turnToSide(RIGHT);
	}

	///Turn to the up, return false if is already turn to the up, otherwise true
	public boolean turnUp(){
		super.setDirection(new Vector2f (0, -1));
		return turnToSide(UP);
	}

	///Turn to the down, return false if is already turn to the down, otherwise true
	public boolean turnDown(){
		super.setDirection(new Vector2f (0, 1));
		return turnToSide(DOWN);
	}

	///Turn to side, if already on that side return false, otherwise true
	private boolean turnToSide(int side){
		if (turnTo == side){
			step = (step + 1) % SPRITE_COLS;
			return false;
		}
		step = 0;
		turnTo = side;
		return true;
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
	
	///Draw the Player
	public void draw(Graphics2D g, ImageObserver ob){
		int x = (int)getPosition().getX();
		int y = (int)getPosition().getY();
		switch (turnTo) {
		case DOWN:
			g.drawImage(player, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH * step, SPRITE_HEIGHT * SPRITE_DOWN_ROW, SPRITE_WIDTH * (step+1), SPRITE_HEIGHT * (SPRITE_DOWN_ROW+1), ob);
			break;
		case UP:
			g.drawImage(player, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH * step, SPRITE_HEIGHT * SPRITE_UP_ROW, SPRITE_WIDTH * (step+1), SPRITE_HEIGHT * (SPRITE_UP_ROW+1), ob);
			break;
		case LEFT:
			g.drawImage(player, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH * step, SPRITE_HEIGHT * SPRITE_LEFT_ROW, SPRITE_WIDTH * (step+1), SPRITE_HEIGHT * (SPRITE_LEFT_ROW+1), ob);
			break;
		case RIGHT:
			g.drawImage(player, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH * step, SPRITE_HEIGHT * SPRITE_RIGHT_ROW, SPRITE_WIDTH * (step+1), SPRITE_HEIGHT * (SPRITE_RIGHT_ROW+1), ob);
			break;
		default:
			break;
		}
	}
	
	///Move the player down (falling)
	public void fall(){
		float x = getPosition().getX();
		float y = getPosition().getY() + FALL_SPEED;
		setPosition(new Vector2f(x,y));
	}

	///Get the fall speeding
	public int getFALL_SPEED() {
		return FALL_SPEED;
	}
}
