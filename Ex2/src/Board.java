import java.util.Random;

public class Board {

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
	
	///Move one tile all the way to the left
	private void moveLeft(int i, int j){
		while (j != 0){
			if (tiles[i][j-1].isEmpty()){
				tiles[i][j].moveTo(tiles[i][j-1]);
			}
			else if (tiles[i][j].equal(tiles[i][j-1])){
				tiles[i][j-1].upgrade();
				tiles[i][j].reset();
			}
			else{
				return;
			}
			j--;
		}
	}
	
	///Move all the board to the left
	public void moveAllLeft(){
		for (int i = 0; i < size ; i++){
			for (int j = 1; j < size ; j++){
				moveLeft(i,j);
			}
		}
		this.newRandomNum();
	}
	
	private void moveRight(int i, int j){
		
	}
}
