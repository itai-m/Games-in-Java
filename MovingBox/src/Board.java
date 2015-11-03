
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class Board {

	private final static String WIN_DIR =  "Board\\";
	private final static String LINUX_DIR =  "Board/";
	
	public final static int EMPTY_TILE = 0;
	public final static int NONE_MOVING_TILE = 1;
	public final static int MB_UP = MovingBrick.UP;
	public final static int MB_DWON = MovingBrick.DOWN;
	public final static int MB_LEFT = MovingBrick.LEFT;
	public final static int MB_RIGHT = MovingBrick.RIGHT;
	public final static int DOOR = 9;
	public final static int ERROR = -1;
	public final static int MAX_LEVEL = 6;
	
	private final int INIT_SIZE = 10;
	private int[][] tiles;
	private LinkedList<MovingBrick> bricks;
	
	private int boardWidth;
	private int boardHeight;
	private int row;
	private int col;
	private int rowSize;
	private int colSize;
	private static Image brick1 = null;
	private static Image[] backgrounds = null;
	private static Image door = null;
	
	///Default constructor
	public Board(){
		tiles = new int[INIT_SIZE][INIT_SIZE];
		initImags();
		bricks = new LinkedList<MovingBrick>();
	}
	
	///Constructor
	public Board(int boardWidth, int boardHeight, int maplevel){
		MovingBrick.initImages();
		loadMap(maplevel);
		backgrounds = new Image[MAX_LEVEL];
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.rowSize = boardHeight / row;
		this.colSize = boardWidth / col;
		initImags();
	}
	
	///Load a map from the list
	public void loadMap(int mapNumber){
		switch (mapNumber) {
		case 0:
			loadExampleMap();
			break;
		case 1:
			loadMap1();
			break;
		case 2:
			loadMap2();
			break;
		case 3:
			loadMap3();
			break;
		case 4:
			loadMap4();
			break;
		case 5:
			loadMap5();
			break;
		default:
			break;
		}
		bricks = new LinkedList<MovingBrick>();
		addMovingBricks();
	}
	
	///Initialization the images
	private static void initImags(){
		String path = "";
		if (System.getProperty("os.name").equals("Linux")){
			path = TemplateHandler.getPath() + LINUX_DIR;
		}
		else{
			path = TemplateHandler.getPath() + WIN_DIR;
		}
		backgrounds[0]  = Toolkit.getDefaultToolkit().getImage(path + "background.png");
		for (int i = 1; i < MAX_LEVEL ; i++){
			backgrounds[i]  = Toolkit.getDefaultToolkit().getImage(path + "background" + i + ".jpg");
		}
		brick1 = Toolkit.getDefaultToolkit().getImage(path + "b1.png");
		door  = Toolkit.getDefaultToolkit().getImage(path + "door.png");
	}
	
	///Load map number 1
	private void loadMap1(){
		this.col = 13;
		this.row = 10;
		int [][] tilesExa =  {{NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				 			  {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE,DOOR},
				 			  {NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_LEFT,MB_LEFT,NONE_MOVING_TILE},
				 			  {NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				 			  {NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				 			  {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_DWON,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 			  {NONE_MOVING_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 			  {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,MB_UP,MB_UP,MB_UP,EMPTY_TILE,MB_UP,MB_UP,MB_LEFT,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE}};
		tiles = tilesExa.clone();
	}
	
	///Load map number 2
	private void loadMap2(){
		this.col = 13;
		this.row = 10;
		int [][] tilesExa =  {{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,MB_LEFT,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_DWON,EMPTY_TILE,MB_LEFT,MB_LEFT,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,MB_UP,MB_RIGHT,MB_LEFT,MB_LEFT,MB_DWON,MB_UP,MB_UP,EMPTY_TILE,MB_RIGHT,MB_DWON,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,MB_DWON,MB_UP,MB_DWON,MB_LEFT,DOOR,MB_RIGHT,MB_DWON,EMPTY_TILE,MB_RIGHT,MB_UP,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,MB_UP,MB_DWON,MB_DWON,MB_UP,MB_RIGHT,MB_DWON,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE, EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {EMPTY_TILE,EMPTY_TILE,MB_UP,MB_UP,MB_UP,EMPTY_TILE,MB_UP,MB_UP,MB_LEFT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 			  {EMPTY_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE}};
		tiles = tilesExa.clone();
	}
	
	///Load map number 3
	private void loadMap3(){
		this.col = 18;
		this.row = 16;
		int [][] tilesExa =  {{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,MB_LEFT,MB_DWON,MB_DWON,EMPTY_TILE,MB_LEFT,MB_LEFT,EMPTY_TILE,EMPTY_TILE,MB_LEFT,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_UP,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_DWON,EMPTY_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_UP,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,EMPTY_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_UP,EMPTY_TILE,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_UP,MB_UP,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				{EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_UP,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_DWON,EMPTY_TILE,MB_LEFT,MB_DWON,MB_LEFT,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_UP,MB_UP,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE,DOOR,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE}};
		tiles = tilesExa.clone();
	}
		
	///Load map number 4
	private void loadMap4(){
		this.col = 15;
		this.row = 7;
		int [][] tilesExa = {{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,DOOR,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,EMPTY_TILE,MB_DWON,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_UP,MB_RIGHT,EMPTY_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,MB_UP,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE}};
		tiles = tilesExa.clone();
	}
	
	private void loadMap5(){
		this.col = 14;
		this.row = 8;
		int [][] tilesExa = {{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_RIGHT,MB_LEFT,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,MB_LEFT,EMPTY_TILE,MB_DWON,MB_DWON,NONE_MOVING_TILE},
				{NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,MB_LEFT,EMPTY_TILE},
				{EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE,MB_DWON,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,DOOR,NONE_MOVING_TILE,EMPTY_TILE},
				{NONE_MOVING_TILE,NONE_MOVING_TILE,MB_UP,MB_RIGHT,EMPTY_TILE,MB_UP,NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE,EMPTY_TILE,MB_UP}};
		tiles = tilesExa.clone();
	}
	
	///Load an example map
	private void loadExampleMap(){
		this.row = 10;
		this.col = 13;
		int [][] tilesExa =  {{NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,MB_LEFT,MB_RIGHT,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {NONE_MOVING_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE,NONE_MOVING_TILE},
				 {EMPTY_TILE,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_UP,EMPTY_TILE,MB_UP,MB_UP,MB_LEFT,DOOR,EMPTY_TILE,EMPTY_TILE,EMPTY_TILE},
				 {NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,EMPTY_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,NONE_MOVING_TILE,MB_UP,NONE_MOVING_TILE}};
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
	public void draw(Graphics2D g, ImageObserver ob, int level){
		g.drawImage(backgrounds[level], 0, 0, boardWidth, boardHeight, ob);
		for (int i = 0; i < tiles.length ; i++){
			for (int j = 0 ; j < tiles[i].length ; j++){
				if (tiles[i][j] == NONE_MOVING_TILE) {
					g.drawImage(brick1, colSize * j, rowSize * i, colSize, rowSize, ob);
				}
				else if (tiles[i][j] == DOOR){
					g.drawImage(door, colSize * j, rowSize * i, colSize, rowSize, ob);
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
		if ((x > col) || (y > row) || (x < 0) || (y < 0))
			return ERROR;
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
	
	///Move a MovingBrick
	public void moveMB(int x, int y){
		for (int i = 0; i < bricks.size() ; i++){
			if ((bricks.get(i).getPosition().getX() == x) && (bricks.get(i).getPosition().getY() == y)){
				switch (bricks.get(i).getKind()) {
					case MB_UP:
						if ((y == 0) || (tiles[y - 1][x] != EMPTY_TILE))
							continue;
						tiles[y][x] = EMPTY_TILE;
						tiles[y - 1][x] = MB_UP;
						break;
					case MB_DWON:
						if ((y == row - 1) || (tiles[y + 1][x] != EMPTY_TILE))
							continue;
						tiles[y][x] = EMPTY_TILE;
						tiles[y + 1][x] = MB_DWON;
						break;
					case MB_LEFT:
						if ((x == 0) || (tiles[y][x - 1] != EMPTY_TILE))
							continue;
						tiles[y][x] = EMPTY_TILE;
						tiles[y][x - 1] = MB_LEFT;
						break;
					case MB_RIGHT:
						if ((x == col - 1) || (tiles[y][x + 1] != EMPTY_TILE))
							continue;
						tiles[y][x] = EMPTY_TILE;
						tiles[y][x + 1] = MB_RIGHT;
						break;
					default:
						break;
				}
				bricks.get(i).move();
			}
		}
	}
	
	///Get the tile id by a point
	public int getTileByPoint(int x, int y){
		if ((colSize == 0) || (rowSize == 0)){
			return -1;
		}
		int col = x / colSize;
		int row = y / rowSize;
		row = checkRow(row);
		col = checkCol(col);
		return getTile(col, row);
	}
	
	///Check the the col
	public int checkCol(int playerCol){
		if (playerCol >= col){
			playerCol = 0;
		}
		return playerCol;
	}
	
	///Check the the row 
	public int checkRow(int playerRow){
		if (playerRow >= row ){
			playerRow = 0;
		}
		return playerRow;
	}
}
