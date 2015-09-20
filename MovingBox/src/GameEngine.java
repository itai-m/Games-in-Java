import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class GameEngine {
	
	private final int START_LEVEL = 0;
	private final int FINAL_LEVEL = 4;

	private Board board;
	private Player player;
	private LinkedList<Shot> shots;
	private int level;
	private Sound sound;
	private LinkedList<Explosion> explosions;

	
	///Default constructor
	public GameEngine(){
		board = new Board();
		player = new Player(Main.initializedWidth/9, Main.initializedHight/9, 10, 10, Main.initializedWidth, Main.initializedHight);
		shots = new LinkedList<Shot>();
		explosions = new LinkedList<Explosion>();
		level = 0;
		sound = new Sound();
	}
	
	///Constructors
	public GameEngine(int boardWidth, int boardHeight){
		level = START_LEVEL;
		player = new Player(0,0, 1, 1, boardWidth, boardHeight);
		initLevel(level, boardWidth, boardHeight);
		sound = new Sound();
	}
	
	///Initialization the levels
	private void initLevel(int level, int boardWidth, int boardHeight){
		board = new Board(boardWidth, boardHeight, level);
		player.setPosition(new Vector2f((int)((board.getBoardWidth() / board.getCol()) * 1.5), board.getBoardHeight() - (board.getBoardHeight() / board.getRow()) * (5/3)));
		player.setColAndRow(board.getCol(), board.getRow());
		shots = new LinkedList<Shot>();
		explosions = new LinkedList<Explosion>();
	}
	
	///Update the game
	public void update(){
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).move();
			if (CDShotBrick(shots.get(i))){
				Explosion temp = new Explosion((int)shots.get(i).getPosition().getX(), (int)shots.get(i).getPosition().getY(), 0, 0, board.getColSize(), board.getRowSize(), board.getBoardWidth(), board.getBoardHeight());
				explosions.add(temp);
				shots.remove(i);
			}
			else if (shots.get(i).isOver()){
				shots.remove(i);
			}
		}
		for (int i = 0; i < explosions.size() ; i++){
			explosions.get(i).move();
			if (explosions.get(i).isOver()){
				explosions.remove(i);
			}
		}
		playerFalling();
	}
	
	///Resize the game
	public void setBoardSize(int boardWidth, int boardHeight){
		board.setBoardSize(boardWidth, boardHeight);
		player.setBoardSize(boardWidth, boardHeight);
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).setBoardSize(boardWidth, boardHeight);
		}
	}
	
	///Player shot
	public void shot(){
		Shot tempShot = new Shot(player.getPosition().getX(), player.getPosition().getY(), player.getDirection().getX(), player.getDirection().getY(), (int)(player.getWidth() / 1.3), (int)(player.getHeight() / 1.3), player.getBoardWidth(), player.getBoardHeight());
		shots.add(tempShot);
		sound.shotSound();
	}
	
	///Draw the game
	public void draw(Graphics2D g, ImageObserver ob){
		board.draw(g, ob);
		for (int i = 0; i < shots.size() ; i++){
			shots.get(i).draw(g, ob);
		}
		for (int i = 0; i < explosions.size() ; i++){
			explosions.get(i).draw(g, ob);
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
		playerRow = board.checkRow(playerRow);
		
		int tile1 = board.getTileByPoint((int)(player.getPosition().getX() - player.getWidth() / 4),(int)(player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()));
		int tile2 = board.getTileByPoint((int)(player.getPosition().getX() + player.getWidth() / 4),(int)(player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()));

		if ((tile1 == Board.DOOR) || (tile2 == Board.DOOR)){
			leverWon();
			player.fall();
		}
		else if ((tile1 == Board.EMPTY_TILE) && (tile2 == Board.EMPTY_TILE)){
			player.fall();
		}
		else if (playerRow == Board.EMPTY_TILE){
			player.setPosition(new Vector2f(player.getPosition().getX(), board.getBoardHeight() - player.getHeight() / 2 ));
		}
		else if (playerRow * board.getRowSize() < player.getPosition().getY() + player.getHeight() / 2 + player.getFALL_SPEED()){
			player.setPosition(new Vector2f(player.getPosition().getX(), playerRow * board.getRowSize() - player.getHeight() / 2 ));
		}
		
	}
	
	///Check if the player can move right
	private boolean checkMoveRight(){
		int tile = board.getTileByPoint((int)((player.getPosition().getX() + (player.getWidth() / 4) + player.getSpeed())), (int)(player.getPosition().getY()));
		if (tile == Board.DOOR){
			leverWon();
			return true;
		}
		if (tile == Board.EMPTY_TILE)
			return true;
		return false;
	}
	
	
	
	///Check if the player can move left
	private boolean checkMoveLeft(){
		int tile = board.getTileByPoint((int)(player.getPosition().getX() - (player.getWidth() / 4) -player.getSpeed()), (int)player.getPosition().getY());
		if (tile == Board.DOOR){
			leverWon();
			return false;
		}
		if (tile == Board.EMPTY_TILE)
			return true;
		return false;
	}
	
	
	
	///Check collision detection with the shot and a brick, return true if there is collision
	private boolean CDShotBrick(Shot shot){
		int x = (int)shot.getPosition().getX();
		int y = (int)shot.getPosition().getY();
		int tile = board.getTileByPoint(x, y);
		if (tile == Board.EMPTY_TILE){
			return false;
		}
		
		int col = x / board.getColSize();
		int row = y / board.getRowSize();
		row = board.checkRow(row);
		col = board.checkCol(col);
		
		switch (tile) {
			case Board.MB_UP:
				if((player.getColLoc() == col) && (player.getRowLoc() == row - 1)){
					if ((board.getTile(col, row - 2) == Board.EMPTY_TILE) || (board.getTile(col, row - 2) == Board.DOOR)){
						player.setPosition(new Vector2f(player.getPosition().getX(), player.getPosition().getY() - board.getRowSize()));
					}
					else{
						return true;
					}
				}
				break;
			case Board.MB_LEFT:
				if((player.getColLoc() == col - 1) && (player.getRowLoc() == row)){
					if ((board.getTile(col - 2, row) == Board.EMPTY_TILE) || (board.getTile(col - 2, row) == Board.DOOR)){
						player.setPosition(new Vector2f(player.getPosition().getX() - board.getColSize(), player.getPosition().getY()));
					}
					else{
						return true;
					}
				}
				break;
			case Board.MB_RIGHT:
				if((player.getColLoc() == col + 1) && (player.getRowLoc() == row)){
					if ((board.getTile(col + 2, row) == Board.EMPTY_TILE) || (board.getTile(col + 2, row) == Board.DOOR)){
						player.setPosition(new Vector2f(player.getPosition().getX() + board.getColSize(), player.getPosition().getY()));
					}
					else{
						return true;
					}
				}
				break;
			default:
				break;
		}
		board.moveMB(col, row);

		return true;
	}
	
	///Player win the level
	private void leverWon(){
		level++;
		if (level >= FINAL_LEVEL + 1){
			//TODO: win mag
			System.out.println("player win");
		}
		else{	
			initLevel(level, board.getBoardWidth(), board.getBoardHeight());
		}
	}
	
	///Reset the level
	public void resetLevel(){
		board.loadMap(level);
		player.setPosition(new Vector2f((int)((board.getBoardWidth() / board.getCol()) * 1.5), board.getBoardHeight() - (board.getBoardHeight() / board.getRow()) * (5/3)));
	}
	
	///Change the appearance of the player
	public void changePlayerAppearance(){
		player.ChangeAppearance();
	}
}