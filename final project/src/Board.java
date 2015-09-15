import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class Board {

	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	public final static int EMPTY_TILE = 0;
	public final static int NONE_MOVING_TILE = 1;
	public final static int MB_UP = MovingBrick.UP;
	public final static int MB_DWON = MovingBrick.DOWN;
	public final static int MB_LEFT = MovingBrick.LEFT;
	public final static int MB_RIGHT = MovingBrick.RIGHT;
	
	private final int INIT_SIZE = 10;
	private int[][] tiles;
	private LinkedList<MovingBrick> bricks;
	
	private int boardWidth;
	private int boardHeight;
	private int row;
	private int col;
	private int rowSize;
	private int colSize;
	private Image brick1 = null;
	private Image background = null;
	
	///Default constructor
	public Board(){
		tiles = new int[INIT_SIZE][INIT_SIZE];
		initImag();
		bricks = new LinkedList<MovingBrick>();
	}
	
	///Constructor
	public Board(int col, int row, int boardWidth, int boardHeight){
		tiles = new int[col][row];
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.rowSize = boardHeight / row;
		this.colSize = boardWidth / col;
		this.row = row;
		this.col = col;
		bricks = new LinkedList<MovingBrick>();
		initImag();
		loadExampleMap();
		addMovingBricks();
	}
	
	///Initialization the images
	private void initImag(){
		brick1 = Toolkit.getDefaultToolkit().getImage(Path + "b1.jpg");
		background  = Toolkit.getDefaultToolkit().getImage(Path + "background.jpg");
	}
	
	///Load an example map
	private void loadExampleMap(){
		int [][] tilesExa =  {{1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
						   {1, MovingBrick.UP, 1, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, MovingBrick.DOWN, MovingBrick.LEFT, MovingBrick.RIGHT, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
					  	   {1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0}};
		tiles = tilesExa.clone();
	}
	
	///Add moving bricks form the the map to the list
	private void addMovingBricks(){
		for (int i = 0; i < tiles.length ; i++){
			for (int j = 0 ; j < tiles[i].length ; j++){
				switch (tiles[i][j]) {
				case MovingBrick.UP:
					bricks.add(new MovingBrick(j, i, MovingBrick.UP, colSize, rowSize, boardWidth, boardHeight));
					break;
				case MovingBrick.DOWN:
					bricks.add(new MovingBrick(j, i, MovingBrick.DOWN, colSize, rowSize, boardWidth, boardHeight));
					break;
				case MovingBrick.LEFT:
					bricks.add(new MovingBrick(j, i, MovingBrick.LEFT, colSize, rowSize, boardWidth, boardHeight));
					break;
				case MovingBrick.RIGHT:
					bricks.add(new MovingBrick(j, i, MovingBrick.RIGHT, colSize, rowSize, boardWidth, boardHeight));
					break;
				default:
					break;
				}
			}
		}
	}
	
	///Draw the board
	public void draw(Graphics2D g, ImageObserver ob){
		g.drawImage(background, 0, 0, boardWidth, boardHeight, ob);
		for (int i = 0; i < tiles.length ; i++){
			for (int j = 0 ; j < tiles[i].length ; j++){
				if (tiles[i][j] == NONE_MOVING_TILE) {
					g.drawImage(brick1, colSize * j, rowSize * i, colSize, rowSize, ob);
				}
			}
		}
		for (int i = 0; i < bricks.size() ; i++){
			bricks.get(i).draw(g, ob);
		}
	}
	
	///Set the board height and width
	public void setBoardSize(int boardWidth, int boardHeight){
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.rowSize = boardHeight / row;
		this.colSize = boardWidth / col;
		for (int i = 0; i < bricks.size() ; i++){
			bricks.get(i).setBoardSize(boardWidth, boardHeight, colSize, rowSize);
		}
	}
	
	///Get one of the tile
	public int getTile(int x, int y){
		return tiles[y][x];
	}

	///Get the board width
	public int getBoardWidth() {
		return boardWidth;
	}

	///Set the board width
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	///Get the board height
	public int getBoardHeight() {
		return boardHeight;
	}

	///Set the board height
	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	///Get the row size
	public int getRowSize() {
		return rowSize;
	}

	///Get the col size
	public int getColSize() {
		return colSize;
	}

	///Get the row
	public int getRow() {
		return row;
	}

	///Get the col
	public int getCol() {
		return col;
	}
	
	///Get a MovingBrick
	public MovingBrick getMB(int x, int y){
		for (int i = 0; i < bricks.size() ; i++){
			if ((bricks.get(i).getPosition().getX() == x) && (bricks.get(i).getPosition().getY() == y)){
				return bricks.get(i);
			}
		}
		return null;
	}
}
