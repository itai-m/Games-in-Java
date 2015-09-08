import java.awt.Graphics;

public class Sprite {

	protected final static float acceleration = 2;
	
	private final int INIT_WIDTH = 1200;
	private final int INIT_HEIGHT = 800;
	
	private Vector2f position;
	private Vector2f direction;
	private int boardWidth;
	private int boardHeight;
	protected float speed;
	
	
	///Default constructor
	public Sprite(){
		position = new Vector2f(0, 0);
		direction = new Vector2f(1, 0);
		this.boardHeight = INIT_HEIGHT;
		this.boardWidth = INIT_WIDTH;
		speed = 1;
	}
	
	///Constructors
	public Sprite(Vector2f position, Vector2f direction, float speed, int boardWidth, int boardHeight){
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.position = position;
		this.direction = direction;
		direction.normalize();
		this.speed = speed;
	}
	
	public Sprite(float posX, float posY, float dirX, float dirY, float speed, int boardWidth, int boardHeight){
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		position = new Vector2f(posX, posY);
		direction = new Vector2f(dirX, dirY);
		direction.normalize();
		this.speed = speed;
	}
	
	///Rotate the sprite
	public void rotate(float angle){
		direction = direction.rotate(angle);
	}
	
	///Get the position
	public Vector2f getPosition() {
		return position;
	}

	///Set the position
	public void setPosition(Vector2f position) {
		this.position = position.normalize();
	}

	///Get the direction
	public Vector2f getDirection() {
		return direction;
	}

	///Set the direction
	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}

	///Get the speed
	public float getSpeed() {
		return speed;
	}

	///Set the speed
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	///Add the speed the acceleration
	public void speedUp(){
		speed += acceleration;
	}
	
	///Subtract the speed the acceleration
	public void speedDown(){
		if (speed <= 0)
			speed = 0;
		else
			speed -= acceleration;
	}
	
	///Get the sprite angle
	public double GetAngle(){
		return direction.getAngle();
	}
	
	///Move to the In the direction of the direction vector
	public void move(){
		position = position.add(direction.mul(speed));
		if (position.getX() > boardWidth){
			position.setX(0);
		}
		else if (position.getX() < 0){
			position.setX(boardWidth);
		}
		if (position.getY() > boardHeight){
			position.setY(0);
		}
		else if (position.getY() < 0){
			position.setY(boardHeight);
		}
	}
	
	///Check if the sprite stop
	public boolean isStop(){
		if (speed == 0)
			return true;
		return false;
	}
	
	///Set the board height and width
	public void setBoardSize(int boardWidth, int boardHeight){
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
	}
	
	///get the board width
	public int getBoardWidth() {
		return boardWidth;
	}

	///set the board width
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	///get the board height
	public int getBoardHeight() {
		return boardHeight;
	}

	///set the board height
	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	///Draw the Sprite
	public void draw(Graphics g) {
	}
}
