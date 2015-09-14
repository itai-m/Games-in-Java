import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

import javax.annotation.Generated;
import javax.swing.border.Border;

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
	public GameEngine(int boardWidth, int boardHeight, int col, int row){
		board = new Board(col, row, boardWidth, boardHeight);
		player = new Player((boardWidth / col) * 2, boardHeight - (boardHeight / row) * (5/2), col, row, boardWidth, boardHeight);
		shots = new LinkedList<Shot>();
	}
	
	
	///Update the game
	public void update(){
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).move();
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
		player.move();
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
	
	///Check if the player need to fall
	private void playerFalling(){
		int playerCol = (int)(player.getPosition().getX() / board.getColSize());
		int playerRow = (int)((player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()) / board.getRowSize());
		
		if (playerRow >= board.getRow()){
			playerRow = 0;
		}
		if (playerCol >= board.getCol()){
			playerCol = 0;
		}
		if (board.getTile(playerCol, playerRow) == Board.EMPTY_TILE){
			player.fall();
		}
		else if (playerRow * board.getRowSize() < player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()){
			player.setPosition(new Vector2f(player.getPosition().getX(), playerRow * board.getRowSize() - player.getHeight() / 2 ));
		}
		
	}
	
	///Check if the player can move right
	private boolean checkMoveRight(){
		int playerCol = (int)((player.getPosition().getX() + (player.getWidth() / 4) + player.getSpeed()) / board.getColSize());
		int playerRow = (int)(player.getPosition().getY() / board.getRowSize());
		if (board.getTile(playerCol, playerRow) == Board.EMPTY_TILE)
			return true;
		return false;
	}
	
	///Check if the player can move left
	private boolean checkMoveLeft(){
		int playerCol = (int)((player.getPosition().getX() - (player.getWidth() / 4) -player.getSpeed()) / board.getColSize());
		int playerRow = (int)(player.getPosition().getY() / board.getRowSize());
		if (board.getTile(playerCol, playerRow) == Board.EMPTY_TILE)
			return true;
		return false;
	}
	
	///Check collision detection square with point, (x,y) this is the left top point of the square
	private boolean CDSquareWithPoint(int x, int y, int width, int height, int pX, int pY){
		if ((x > pX) && (y > pY) && (x + width < pX) && (y + height < pY))
			return true;
		return false;
	}
}
