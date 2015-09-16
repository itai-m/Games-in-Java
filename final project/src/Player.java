import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Player extends Sprite{
	
	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	private final static int SPEED = 5;
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
	private final int FALL_SPEED = 20;
	private final double PROPORCEN_TO_BAORD = 1.5;

	private int height;
	private int width;
	private int turnTo;
	private Image player;
	private int step;
	
	private int row;
	private int col;
	
	
	///Constructor
	public Player(int x, int y, int col, int row, int boardWidth, int boardHeight){
		super(x, y, 0, 1, SPEED, boardWidth, boardHeight);
		this.col = col;
		this.row = row;
		this.height = (int) (boardHeight / (row * PROPORCEN_TO_BAORD));
		this.width = (int) (boardWidth / (col * PROPORCEN_TO_BAORD));
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
		if (y > getBoardHeight())
			y = 0;
		setPosition(new Vector2f(x,y));
	}

	///Get the fall speeding
	public int getFALL_SPEED() {
		return FALL_SPEED;
	}
	
	///Set the board width and height
	public void setBoardSize(int boardWidth, int boardHeight){
		int widthDelta = getBoardWidth() - boardWidth;
		int heighthDelta = getBoardHeight() - boardHeight;
		widthDelta /= col / PROPORCEN_TO_BAORD ;
		heighthDelta /= row / PROPORCEN_TO_BAORD ;
		setPosition(new Vector2f(getPosition().getX() - widthDelta, getPosition().getY() - heighthDelta));
		super.setBoardSize(boardWidth, boardHeight);
		this.height = (int) (boardHeight / (row * PROPORCEN_TO_BAORD));
		this.width = (int) (boardWidth / (col * PROPORCEN_TO_BAORD));
	}
	
	///Get the location by col
	public int getColLoc(){
		return (int) (getPosition().getX() / (getBoardWidth() / col));
	}
	
	///Get the location by row
	public int getRowLoc(){
		return (int) (getPosition().getY() / (getBoardHeight() / row));
	}
}
