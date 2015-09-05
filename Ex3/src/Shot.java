
import java.awt.Color;
import java.awt.Graphics2D;

public class Shot extends Sprite {

	private final int SIZE = 5;
	private final int MAX_STEPS = 20;
	
	private int steps;
	
	///Default constructor
	public Shot(){
		super();
		steps = MAX_STEPS;
	}
	
	///Constructors
	public Shot(Vector2f position, Vector2f direction, float speed, int boardWidth, int boardHeight){
		super(position, direction, speed, boardWidth, boardHeight);
		steps = MAX_STEPS;
	}
	
	public Shot(float posX, float posY, float dirX, float dirY, float speed, int boardWidth, int boardHeight){
		super(posX, posY, dirX, dirY, speed, boardWidth,  boardHeight);
		steps = MAX_STEPS;
	}
	
	///Draw the shot
	public void draw(Graphics2D g){
		g.setColor(Color.WHITE);
		g.drawOval((int)getPosition().getX(), (int)getPosition().getY(), SIZE, SIZE);
	}

	///Move the shot
	public void move(){
		super.move();
		steps--;
	}
	
	///Check if the shot is done
	public boolean isDone(){
		if (steps <= 0)
			return true;
		else
			return false;
	}
}
