import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class GameEngine {

	private Board board;
	private Player player;
	private LinkedList<Shot> shots;
	
	///Default constructor
	public GameEngine(){
		board = new Board();
		player = new Player();
		shots = new LinkedList<Shot>();
	}
	
	///Constructors
	public GameEngine(int boardWidth, int boardHeight){
		board = new Board(10, 10, boardWidth, boardHeight);
		player = new Player((boardWidth / 10) * 2, boardHeight - (boardHeight / 10) * (5/2), boardWidth / 15, boardHeight / 15, boardWidth, boardHeight);
		shots = new LinkedList<Shot>();
	}
	
	
	///Update the game
	public void update(){
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).move();
		}
	}
	
	///Resize the game
	public void setBoardSize(int boardWidth, int boardHeight){
		board.setBoardSize(boardWidth, boardHeight);
	}
	
	///Player shot
	public void shot(){
		Shot tempShot = new Shot(player.getPosition().getX(), player.getPosition().getY(), player.getDirection().getX(), player.getDirection().getY(), (int)(player.getWidth() / 1.3), (int)(player.getHeight() / 1.3), player.getBoardWidth(), player.getBoardHeight());
		shots.add(tempShot);
	}
	
	///Draw the game
	public void draw(Graphics2D g, ImageObserver ob){
		board.draw(g, ob);
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).draw(g, ob);
		}
		player.draw(g, ob);
	}
	
	///Move the player to the right
	public void MoveRight(){
		player.turnRight();
		player.move();
	}
	
	//Move the player to the up
	public void MoveUp(){
		player.turnUp();
		player.move();
	}
		
	//Move the player to the left
	public void MoveLeft(){
		player.turnLeft();
		player.move();
	}
	
	//Move the player to the down
	public void MoveDown(){
		player.turnDown();
		player.move();
	}
	
	///Get the board width
	public int getBoardWidth() {
		return board.getBoardWidth();
	}
	
	///Get the board height
	public int getBoardHeight() {
		return board.getBoardHeight();
	}
}
