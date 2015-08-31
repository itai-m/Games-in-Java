
public class Ship extends Sprite{

	
	///Default constructor
	public Ship(){
		super();
	}
	
	///Constructors
	public Ship(Vector2f position, Vector2f direction, float speed){
		super(position, direction, speed);
	}
	
	public Ship(float posX, float posY, float dirX, float dirY, float speed){
		super(posX, posY, dirX, dirY, speed);
	}
	
	public void speedUp(){
		speed += acceleration*2;
	}
	
}
