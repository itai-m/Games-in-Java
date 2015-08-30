
public class Ship {
	
	private final float acceleration = 5;

	private Vector2f position;
	private Vector2f direction;
	private float speed;
	
	///Default constructor
	public Ship(){
		position = new Vector2f(0, 0);
		direction = new Vector2f(0, 0);
		speed = 0;
	}
	
	///Constructors
	public Ship(Vector2f position, Vector2f direction, float speed){
		this.position = position;
		this.direction = direction;
		direction.normalize();
		this.speed = speed;
	}
	
	public Ship(float posX, float posY, float dirX, float dirY, float speed){
		position = new Vector2f(posX, posY);
		direction = new Vector2f(dirX, dirY);
		direction.normalize();
		this.speed = speed;
	}

	///Rotate the ship
	public void rotateShip(float angle){
		direction.rotate(angle);
	}
	
	///Move to the In the direction of the direction vector
	public void move(){
		Vector2f temp = new Vector2f(direction.getX(), direction.getY());
		temp.mul(speed);
		position.add(temp);
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
		speed -= acceleration;
	}
}
