import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Board {

	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	public final static int EMPTY_TILE = 0;
	
	private final int INIT_SIZE = 10;
	private int[][] tiles;
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
		initImag();
		loadExampleMap();
	}
	
	///Initialization the images
	private void initImag(){
		brick1 = Toolkit.getDefaultToolkit().getImage(Path + "b1.jpg");
		background  = Toolkit.getDefaultToolkit().getImage(Path + "background.jpg");
	}
	
	///Load an example map
	private void loadExampleMap(){
		int [][] tilesExa =  {{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
						   {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						   {1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
					  	   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
		tiles = tilesExa.clone();
	}
	
	///Draw the board
	public void draw(Graphics2D g, ImageObserver ob){
		g.drawImage(background, 0, 0, boardWidth, boardHeight, ob);
		for (int i = 0; i < tiles.length ; i++){
			for (int j = 0 ; j < tiles[i].length ; j++){
				switch (tiles[i][j]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.drawImage(brick1, colSize * j, rowSize * i, colSize, rowSize, ob);
					break;
				default:
					break;
				}
			}
		}
	}
	
	///Set the board height and width
	public void setBoardSize(int boardWidth, int boardHeight){
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.rowSize = boardHeight / row;
		this.colSize = boardWidth / col;
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
}
