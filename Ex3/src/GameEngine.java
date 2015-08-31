import java.util.LinkedList;

public class GameEngine {

	private final float MOVMENT_ANGLE = 15;
	
	private Ship ship;
	private LinkedList<Shot> shots;
	
	///Default constructor
	public GameEngine(){
		ship = new Ship();
		shots = new LinkedList<Shot>();
	}
	
	///Constructor
	public GameEngine(float posX, float posY, float dirX, float dirY, float speed){
		ship = new Ship(posX, posY, dirX, dirY, speed);
		shots = new LinkedList<Shot>();
	}
	
	///Return the location of the ship
	public Vector2f getShipLocation(){
		return ship.getPosition();
	}
	
	///Update the game
	public void update(){
		ship.move();
		ship.speedDown();
	}
	
	///Speed up the ship
	public void speedUpShip(){
		ship.speedUp();
	}
	
	///Get ship angle
	public double getShipAngle(){
		return ship.GetAngle();
	}
	
	///Move ship to left
	public void moveShipLeft(){
		ship.rotate(-MOVMENT_ANGLE);
	}
	
	///Move ship to right
	public void moveShipRight(){
		ship.rotate(MOVMENT_ANGLE);
	}
	
	///Shot with the ship
	public void shot(){
		Shot shot = new Shot(ship.getPosition(), ship.getDirection(), ship.getSpeed() + 1);
		shots.add(shot);
	}
	
}