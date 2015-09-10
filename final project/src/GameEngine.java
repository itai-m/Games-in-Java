import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class GameEngine {

	private Board board;
	private Player player;
	
	///Default constructor
	public GameEngine(){
		board = new Board();
		player = new Player();
	}
	
	///Constructors
	public GameEngine(int boardWidth, int boardHeight){
		board = new Board(10, 10, boardWidth, boardHeight);
		player = new Player(200, 200, boardWidth / 10, boardHeight / 10, boardWidth, boardHeight);
	}
	
	
	///Update the game
	public void update(){
		
	}
	
	///Resize the game
	public void setBoardSize(int boardWidth, int boardHeight){
		board.setBoardSize(boardWidth, boardHeight);
	}
	
	///Player shot
	public void shot(){
		
	}
	
	///Draw the game
	public void draw(Graphics2D g, ImageObserver ob){
		board.draw(g, ob);
		player.draw(g, ob);
	}
	
	///Move the player to the right
	public void MoveRight(){
		player.turnRight();
	}
	
	//Move the player to the up
	public void MoveUp(){
		player.turnUp();
	}
		
	//Move the player to the left
	public void MoveLeft(){
		player.turnLeft();
	}
	
	//Move the player to the down
	public void MoveDown(){
		player.turnDown();
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
