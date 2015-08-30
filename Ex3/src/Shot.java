
public class Shot extends Sprite {

	///Default constructor
	public Shot(){
		super();
	}
	
	///Constructors
	public Shot(Vector2f position, Vector2f direction, float speed){
		super(position, direction, speed);
	}
	
	public Shot(float posX, float posY, float dirX, float dirY, float speed){
		super(posX, posY, dirX, dirY, speed);
	}
}
