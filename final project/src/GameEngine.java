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
		player = new Player(Main.initializedWidth/9, Main.initializedHight/9, 10, 10, Main.initializedWidth, Main.initializedHight);
		shots = new LinkedList<Shot>();
	}
	
	///Constructors
	public GameEngine(int boardWidth, int boardHeight, int col, int row){
		board = new Board(col, row, boardWidth, boardHeight);
		player = new Player((boardWidth / col) * 2, boardHeight - (boardHeight / row) * (5/2), col, row, boardWidth, boardHeight);
		shots = new LinkedList<Shot>();
	}
	
	
	///Update the game
	public void update(){
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).move();
			if (CDShotBrick(shots.get(i))){
				shots.remove(i);
			}
			else if (shots.get(i).isOver()){
				shots.remove(i);
			}
		}
		playerFalling();
	}
	
	///Resize the game
	public void setBoardSize(int boardWidth, int boardHeight){
		board.setBoardSize(boardWidth, boardHeight);
		player.setBoardSize(boardWidth, boardHeight);
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
		if (checkMoveRight())
			player.move();
	}
	
	//Move the player to the up
	public void MoveUp(){
		player.turnUp();
	}
		
	//Move the player to the left
	public void MoveLeft(){
		player.turnLeft();
		if (checkMoveLeft())
			player.move();
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
	
	///Check if the player need to fall
	private void playerFalling(){
		int playerRow = (int)((player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()) / board.getRowSize());
		playerRow = checkRow(playerRow);
		
		int tile1 = getTileByPoint((int)(player.getPosition().getX() - player.getWidth() / 4),(int)(player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()));
		int tile2 = getTileByPoint((int)(player.getPosition().getX() + player.getWidth() / 4),(int)(player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()));

		if ((tile1 == Board.EMPTY_TILE) && (tile2 == Board.EMPTY_TILE)){
			player.fall();
		}
		else if (playerRow == 0){
			player.setPosition(new Vector2f(player.getPosition().getX(), board.getBoardHeight() - player.getHeight() / 2 ));
		}
		else if (playerRow * board.getRowSize() < player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()){
			player.setPosition(new Vector2f(player.getPosition().getX(), playerRow * board.getRowSize() - player.getHeight() / 2 ));
		}
		
	}
	
	///Check if the player can move right
	private boolean checkMoveRight(){
		int tile = getTileByPoint((int)((player.getPosition().getX() + (player.getWidth() / 4) + player.getSpeed())), (int)(player.getPosition().getY()));
		if (tile == Board.EMPTY_TILE)
			return true;
		return false;
	}
	
	///Check the the col
	private int checkCol(int playerCol){
		if (playerCol >= board.getCol()){
			playerCol = 0;
		}
		return playerCol;
	}
	
	///Check the the row 
	private int checkRow(int playerRow){
		if (playerRow >= board.getRow() ){
			playerRow = 0;
		}
		return playerRow;
	}
	
	///Check if the player can move left
	private boolean checkMoveLeft(){
		int tile = getTileByPoint((int)(player.getPosition().getX() - (player.getWidth() / 4) -player.getSpeed()), (int)player.getPosition().getY());
		if (tile == Board.EMPTY_TILE)
			return true;
		return false;
	}
	
	///Get the tile id by a point
	private int getTileByPoint(int x, int y){
		int col = x / board.getColSize();
		int row = y / board.getRowSize();
		row = checkRow(row);
		col = checkCol(col);
		return board.getTile(col, row);
	}
	
	///Check collision detection with the shot and a brick, return true if there is collision
	private boolean CDShotBrick(Shot shot){
		int x = (int)shot.getPosition().getX();
		int y = (int)shot.getPosition().getY();
		int tile = getTileByPoint(x, y);
		if (tile == Board.EMPTY_TILE){
			return false;
		}
		int col = x / board.getColSize();
		int row = y / board.getRowSize();
		row = checkRow(row);
		col = checkCol(col);
		board.moveMB(col, row);

		return true;
	}
}
