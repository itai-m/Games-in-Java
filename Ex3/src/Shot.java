import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shot extends Sprite {

	private final int SIZE = 5;
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
	
	///Draw the shot
	public void draw(Graphics2D g){
		g.drawOval((int)getPosition().getX(), (int)getPosition().getY(), SIZE, SIZE);
	}
}
