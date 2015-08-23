
public class Maze {

	private Block [][] blocks;
	private int col;
	private int row;
	
	///Default constructor
	public Maze(){
		col = 10;
		row = 10;
		initBlocks();
	}
	
	///Constructors
	public Maze(int _col, int _row){
		col = _col;
		row = _row;
		initBlocks();
	}
	
	public Maze(int size){
		col = size;
		row = size;
		initBlocks();
	}
	
	///Initialization the array of the blocks
	private void initBlocks(){
		blocks = new Block[col][row];
		for (int i = 0; i < col ; i++){
			for (int j = 0; j < row ; j++){
				blocks[i][j] = new Block();
			}
		}
	}
	
	public void set
}
