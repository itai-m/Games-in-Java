import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Board {

	private final String Path =  System.getProperty("user.dir") + "\\src\\";
	
	private final int INIT_SIZE = 10;
	private int[][] tiles;
	private int boardWidth;
	private int boardHeight;
	private int height;
	private int width;
	private int rowSize;
	private int colSize;
	private Image brick1 = null;
	private Image background = null;
	
	///Default constructor
	public Board(){
		tiles = new int[INIT_SIZE][INIT_SIZE];
		initBricks();
	}
	
	
	public Board(int width, int height, int boardWidth, int boardHeight){
		tiles = new int[width][height];
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.rowSize = boardHeight / height;
		this.colSize = boardWidth / width;
		this.height = height;
		this.width = width;
		initBricks();
		loadExampleMap();
	}
	
	private void initBricks(){
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
		this.rowSize = boardHeight / height;
		this.colSize = boardWidth / width;
	}
}
