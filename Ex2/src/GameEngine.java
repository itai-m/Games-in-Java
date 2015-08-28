
public class GameEngine {

	private Board board;
	
	///Default constructor
	public GameEngine(){
		board = new Board();
	}
	
	///Constructor
	public GameEngine(int size){
		board = new Board(size);
	}
	
	///Return the board size
	public int getBoardSize(){
		return board.getSize();
	}
	
	///Get a number in one tile on the board
	public int getTile(int x, int y){
		return board.getNumber(x, y);
	}
	
	///Move all the board to the left
	public int moveLeft(){
		return board.moveAllTo(Board.LEFT);
	}
	
	///Move all the board to the right
	public int moveRight(){
		return board.moveAllTo(Board.RIGHT);
	}
	
	///Move all the board up
	public int moveUp(){
		return board.moveAllTo(Board.UP);
	}
	
	//Move all the board Down
	public int moveDown(){
		return board.moveAllTo(Board.DOWN);
	}
}
