
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class GameEngine {

	private final float MOVMENT_ANGLE = 15;
	private final float SHOT_SPEED = 40;
	public static final String Path =  System.getProperty("user.dir") + "\\src\\";;
	private final int NUM_OF_ASTROIDS = 4;
	private final int ASTROIDS_SPEED = 10;
	private final int ASTROIDS_SIZE = 4;
	private final int NUMBER_OF_LIFE = 3;
	
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
		ship = new Ship(posX, posY, dirX, dirY, speed, NUMBER_OF_LIFE);
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
		collisionDetection();
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
	
	///Create asteroids and add them to the game
	public void addAsteroids(int num){
		for (int i = 0; i < num ; i++){
			Astroid astOne = new Astroid(0, 0, 1, 0, ASTROIDS_SPEED, ASTROIDS_SIZE);
			astOne.randDirection();
			astroids.add(astOne);
		}
	}
	
	///Check collision detection with asteroid
	private boolean CDAstroid(Astroid ast, float pointX, float pointY){
		float dx = ast.getPosition().getX() - pointX;
		float dy = ast.getPosition().getY() - pointY;
		if (new Vector2f(dx, dy).lenght() <= Astroid.DRAW_SIZE * ast.getSize())
			return true;
		else 
			return false;
		
	}
	
	///Collision detection shots with asteroids
	private void collisionDetection(){
		for (int i = 0; i < astroids.size() ; i++){
			for (int j = 0; j < shots.size() ; j++){
				///check if there is collision asteroids with shots 
				if (CDAstroid(astroids.get(i), shots.get(j).getPosition().getX(), shots.get(j).getPosition().getY())){
					astroids.add(astroids.get(i).split());
					shots.remove(j);
				}
			}
			///check if asteroids is destroyed
			if (astroids.get(i).isDestroyed()){
				astroids.remove(i);
			}
			else{
				///check if there is collision asteroids with ship
				float shipX = ship.getPosition().getX();
				float shipY = ship.getPosition().getY();
				float shipSize = ship.SHIPSIZE / 2;
				if ((CDAstroid(astroids.get(i), shipX - shipSize, shipY - shipSize)) ||
					(CDAstroid(astroids.get(i), shipX - shipSize, shipY + shipSize)) ||
					(CDAstroid(astroids.get(i), shipX + shipSize, shipY - shipSize)) ||
					(CDAstroid(astroids.get(i), shipX + shipSize, shipY + shipSize)) )
				{
					ship.lifeDown();
				}
			}
		}
	}
	
	
}
