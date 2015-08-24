import java.util.Random;

public class Board {
	
	public final static int LEFT = 1;
	public final static int RIGHT = 2;
	public final static int DOWN = 3;
	public final static int UP = 4;
	public final static int WIN = 5;
	public final static int LOSE = 6;
	public final static int NOTHING = 7;
	
	private int size; ///Save the size of the board (the board is always square)
	private Tile [][] tiles; //Save all the tiles
	
	///Default constructor
	public Board (){
		size = 4;
		tiles = new Tile[size][size];
		this.initBoard();
		this.newRandomNum();
		this.newRandomNum();
	}
	
	///Constructor
	public Board(int _size){
		size = _size;
		tiles = new Tile[size][size];
		this.initBoard();
		this.newRandomNum();
		this.newRandomNum();
	}
	
	///Initialize all the tiles in the board
	private void initBoard(){
		for (int i = 0; i < size ; i++){
			for (int j = 0; j < size ; j++){
				tiles[i][j] = new Tile();
			}
		}
	}
	
	///Check if all the board is full
	private boolean isFull(){
		for (int i = 0; i < size ; i++){
			for (int j = 0; j < size ; j++){
				if (tiles[i][j].isEmpty()){
					return false;
				}
			}
		}
		return true;
	}
	
	//Add a random number to the board
	private void newRandomNum(){
		Random rand = new Random();
		while (true){
			int randomCol = rand.nextInt((size));
			int randomRow = rand.nextInt((size));
			int randomNum = rand.nextInt(2) + 1;
			if (tiles[randomCol][randomRow].isEmpty()){
				if (randomNum == 1){
					tiles[randomCol][randomRow].setTo1();
					return;
				}
				else{
					tiles[randomCol][randomRow].setTo2();
					return;
				}
			}
		}
	}
	
	///Get the size of the board
	public int getSize(){
		return size;
	}
	
	///Get the number of one tile in the board
	public int getNumber(int i, int j){
		return (tiles[i][j].getNumber());
	}
	
	///Move one tile all the way to the left, return true if the tile moved otherwise false 
	private boolean moveLeft(int i, int j){
		boolean findMove = false;
		if (tiles[i][j].isEmpty())
			return false;
		while (j != 0){
			if (tiles[i][j-1].isEmpty()){
				tiles[i][j].moveTo(tiles[i][j-1]);
				findMove = true;
			}
			else if (tiles[i][j].equal(tiles[i][j-1])){
				tiles[i][j-1].upgrade();
				tiles[i][j].reset();
				findMove = true;
				return findMove;
			}
			else{
				return findMove;
			}
			j--;
		}
		return findMove;
	}
	
	///Move all the board to the left
	private boolean moveAllLeft(){
		boolean findMove = false;
		for (int i = 0; i < size ; i++){
			for (int j = 1; j < size ; j++){
				if (moveLeft(i,j))
					findMove = true;
			}
		}
		return findMove;
	}
	
	///Move one tile all the way to the right, return true if the tile moved otherwise false
	private boolean moveRight(int i, int j){
		boolean findMove = false;
		if (tiles[i][j].isEmpty())
			return false;
		while (j != size-1){
			if (tiles[i][j+1].isEmpty()){
				tiles[i][j].moveTo(tiles[i][j+1]);
				findMove = true;
			}
			else if (tiles[i][j].equal(tiles[i][j+1])){
				tiles[i][j+1].upgrade();
				tiles[i][j].reset();
				findMove = true;
				return findMove;
			}
			else{
				return findMove;
			}
			j++;
		}
		return findMove;
	}
	
	///Move all the board to the right
	private boolean moveAllRight(){
		boolean findMove = false;
		for (int i = 0; i < size ; i++){
			for (int j = size-1; j >= 0 ; j--){
				if (moveRight(i,j))
					findMove = true;
			}
		}
		return findMove;
	}
	
	///Move one tile all the way to the up, return true if the tile moved otherwise false
	private boolean moveUp(int i, int j){
		boolean findMove = false;
		if (tiles[i][j].isEmpty())
			return false;
		while (i != 0){
			if (tiles[i-1][j].isEmpty()){
				tiles[i][j].moveTo(tiles[i-1][j]);
				findMove = true;
			}
			else if (tiles[i][j].equal(tiles[i-1][j])){
				tiles[i-1][j].upgrade();
				tiles[i][j].reset();
				findMove = true;
				return findMove;
			}
			else{
				return findMove;
			}
			i--;
		}
		return findMove;
	}
	
	///Move all the board to the up
	private boolean moveAllUp(){
		boolean findMove = false;
		for (int i = 0; i < size ; i++){
			for (int j = 1; j < size ; j++){
				if (moveUp(j,i))
					findMove = true;
			}
		}
		return findMove;
	}
	
	///Move one tile all the way to the up, return true if the tile moved otherwise false
	private boolean moveDown(int i, int j){
		boolean findMove = false;
		if (tiles[i][j].isEmpty())
			return false;
		while (i != size-1){
			if (tiles[i+1][j].isEmpty()){
				tiles[i][j].moveTo(tiles[i+1][j]);
				findMove = true;
			}
			else if (tiles[i][j].equal(tiles[i+1][j])){
				tiles[i+1][j].upgrade();
				tiles[i][j].reset();
				findMove = true;
				return findMove;
			}
			else{
				return findMove;
			}
			i++;
		}
		return findMove;
	}
	
	///Move all the board to the up
	private boolean moveAllDown(){
		boolean findMove = false;
		for (int i = 0; i < size ; i++){
			for (int j = size-1; j >= 0 ; j--){
				if (moveDown(j,i))
					findMove = true;
			}
		}
		return findMove;
	}
	
	///Move the board to the direction, return WIN if the player win, LOSE if the player lose and NOTHING otherwise 
	public int moveAllTo(int direction){
		boolean findMove = false;
		switch (direction){
		case LEFT:
			findMove = moveAllLeft();
			break;
		case RIGHT:
			findMove = moveAllRight();
			break;
		case UP:
			findMove = moveAllUp();
			break;
		case DOWN:
			findMove = moveAllDown();
			break;
		}
		if (this.checkWin()){
			return WIN;
		}
		
		if (findMove){
			this.newRandomNum();
		}
		if (this.isFull()){
			if (!this.checkIfCanMove()){
				return LOSE;
			}
		}
		
		return NOTHING;
	}
	
	///Check if the player win the game, true for win and false for lose
	private boolean checkWin(){
		for (int i = 0; i < size ; i++){
			for (int j = 0; j < size ; j++){
				if (tiles[i][j].checkWin()){
					return true;
				}
			}
		}
		return false;
	}
	
	///Check if there is a move to be done
	private boolean checkIfCanMove(){
		for (int i = 0 ; i < size-1 ; i++){
			for (int j = 0 ; j < size-1 ; j++){
				if ((tiles[i][j].equal(tiles[i+1][j]))||(tiles[i][j].equal(tiles[i][j+1]))){
					return true;
				}
			}
		}
		return false;
	}
}
