
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
	public static final int WIN = 1;
	public static final int LOSE = 2;
	public static final int NOTHING = 0;
	private final int STEP_OF_INVULNERABLE = 30;
	
	
	
	private Ship ship;
	private LinkedList<Shot> shots;
	private LinkedList<Astroid> astroids;
	private int invulnerableSteps;
	
	///Default constructor
	public GameEngine(){
		ship = new Ship();
		initGame();
	}
	
	///Constructor
	public GameEngine(int boardWidth, int boardHeight, float dirX, float dirY, float speed){
		ship = new Ship(boardWidth, boardHeight, dirX, dirY, speed, NUMBER_OF_LIFE);
		initGame();
	}
	
	///Initializing the game
	private void initGame(){
		shots = new LinkedList<Shot>();
		astroids = new LinkedList<Astroid>();
		addAsteroids(NUM_OF_ASTROIDS);
		invulnerableSteps = 0;
	}
	
	///Set the board height and width
	public void setBoardSize(int boardWidth, int boardHeight){
		ship.setBoardSize(boardWidth, boardHeight);
		for (int i = 0; i < shots.size() ; i++)
			shots.get(i).setBoardSize(boardWidth, boardHeight);
		for (int i = 0; i < astroids.size() ; i++)
			astroids.get(i).setBoardSize(boardWidth, boardHeight);
	}
	
	
	///Update the game
	public int update(){
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
		if (invulnerableSteps <= 0){
			collisionDetection(false);
		}
		else{
			invulnerableSteps--;
			collisionDetection(true);
		}
		if (isWin()){
			return WIN;
		}
		else if (isLose()){
			return LOSE;
		}
		else{
			return NOTHING;
		}
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
		Shot shot = new Shot(ship.getPosition().getX(),ship.getPosition().getY(), ship.getDirection().getX(), ship.getDirection().getY(), SHOT_SPEED, ship.getBoardWidth(), ship.getBoardHeight());
		shots.add(shot);
	}
	
	///Draw all the game
	public void draw(Graphics2D g, ImageObserver ob){
		/*
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, boardWidth, boardHeight);
		*/
		for (Shot shot : shots){
			shot.draw(g);
		}
		for (Astroid ast : astroids){
			ast.draw(g, ob);
		}
		if ((invulnerableSteps % 2) == 0)
			ship.draw(g, ob);
	}
	
	///Create asteroids and add them to the game
	public void addAsteroids(int num){
		for (int i = 0; i < num ; i++){
			Astroid astOne = new Astroid(0, 0, 1, 0, ASTROIDS_SPEED, ASTROIDS_SIZE, ship.getBoardWidth(), ship.getBoardHeight());
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
	private void collisionDetection(boolean invulnerable){
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
				if (!invulnerable){
					///check if there is collision asteroids with ship
					float shipX = ship.getPosition().getX();
					float shipY = ship.getPosition().getY();
					float shipSize = Ship.SHIPSIZE / 2;
					if ((CDAstroid(astroids.get(i), shipX - shipSize, shipY - shipSize)) ||
						(CDAstroid(astroids.get(i), shipX - shipSize, shipY + shipSize)) ||
						(CDAstroid(astroids.get(i), shipX + shipSize, shipY - shipSize)) ||
						(CDAstroid(astroids.get(i), shipX + shipSize, shipY + shipSize)) )
					{
						ship.lifeDown();
						invulnerableSteps = STEP_OF_INVULNERABLE;
					}
				}
			}
		}
	}
	
	///Check if the player win
	private boolean isWin(){
		if (astroids.size() == 0)
			return true;
		return false;
	}
	
	///Check if the player lose
	private boolean isLose(){
		if (ship.isDead())
			return true;
		return false;
	}
	
	///get ship board width
	public int shipBoardWidth(){
		return ship.getBoardWidth();
	}
	
	///get ship board height
	public int shipBoardHeight(){
		return ship.getBoardHeight();
	}
	
}
