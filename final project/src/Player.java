
public class Player extends Sprite{
	
	private final static int SPEED = 4;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWN = 4;

	private int height;
	private int width;
	private int turnTo;
	
	///Default constructor
	public Player (){
		super();
		turnTo = RIGHT;
		height = 10;
		width = 10;
	}
	
	///Constructor
	public Player(int x, int y, int width, int height, int boardWidth, int boardHeight){
		super(x, y, 0, 1, SPEED, boardWidth, boardHeight);
		this.height = height;
		this.width = width;
		turnTo = RIGHT;
	}

	///Turn to the left, return false if is already turn to the left, otherwise true
	public boolean turnLeft(){
		super.setDirection(new Vector2f (0, -1));
		return turnToSide(LEFT);
	}

	///Turn to the right, return false if is already turn to the right, otherwise true
	public boolean turnRight(){
		super.setDirection(new Vector2f (0, 1));
		return turnToSide(RIGHT);
	}

	///Turn to the up, return false if is already turn to the up, otherwise true
	public boolean turnUp(){
		super.setDirection(new Vector2f (-1, 0));
		return turnToSide(UP);
	}

	///Turn to the down, return false if is already turn to the down, otherwise true
	public boolean turnDown(){
		super.setDirection(new Vector2f (1, 0));
		return turnToSide(DOWN);
	}

	///Turn to side, if already on that side return false, otherwise true
	private boolean turnToSide(int side){
		if (turnTo == side)
			return false;
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
}