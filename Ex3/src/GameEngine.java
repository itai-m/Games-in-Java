
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class GameEngine {

	private final float MOVMENT_ANGLE = 15;
	private final float SHOT_SPEED = 40;
	public static final String Path =  System.getProperty("user.dir") + "\\src\\";;
	private final int NUM_OF_ASTROIDS = 4;
	
	private Ship ship;
	private LinkedList<Shot> shots;
	private LinkedList<Astroid> astroids;
	
	///Default constructor
	public GameEngine(){
		ship = new Ship();
		shots = new LinkedList<Shot>();
		astroids = new LinkedList<Astroid>();
		addAsteroids(NUM_OF_ASTROIDS);
	}
	
	///Constructor
	public GameEngine(float posX, float posY, float dirX, float dirY, float speed){
		ship = new Ship(posX, posY, dirX, dirY, speed);
		shots = new LinkedList<Shot>();
		astroids = new LinkedList<Astroid>();
		addAsteroids(NUM_OF_ASTROIDS);
	}
	
	///Return the location of the ship
	public Vector2f getShipLocation(){
		return ship.getPosition();
	}
	
	///Update the game
	public void update(){
		ship.move();
		ship.speedDown();
		for (int i = 0 ; i < shots.size() ; i++){
			if (shots.get(i).isDone()){
				shots.remove(i);
			}
			else{
				shots.get(i).move();
			}
		}
		for (int i = 0 ; i< astroids.size() ; i++){
			astroids.get(i).move();
		}
		
		/*for (Shot shot : shots){
			if (shot.isDone()){
				shots.remove(shot);
			}
			else{
				shot.move();
			}
		}*/
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
		Shot shot = new Shot(ship.getPosition().getX(),ship.getPosition().getY(), ship.getDirection().getX(), ship.getDirection().getY(), SHOT_SPEED);
		shots.add(shot);
	}
	
	///Draw all the game
	public void draw(Graphics2D g, ImageObserver ob){
		
		for (Shot shot : shots){
			shot.draw(g);
		}
		for (Astroid ast : astroids){
			ast.draw(g, ob);
		}
		ship.draw(g, ob);
	}
	
	///Create asteroids
	public void addAsteroids(int num){
		Astroid astOne = new Astroid(50, 50, 10, 10, 2, 4);
		astroids.add(astOne);
	}
	
}
