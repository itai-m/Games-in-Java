import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class MovingBrick extends Sprite{
	
	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 4;
	
	private int kind;
	private int colSize;
	private int rowSize;
	private Image image;
	
	///Default constructor
	public MovingBrick(){
		super();
		kind = 0;
		initImages(0);
	}
	
	///Constructors
	public MovingBrick(float x, float y, int kind, int colSize, int rowSize, int boardWidth, int boardHeight){
		super(x * colSize, y * rowSize, 1, 0, 1, boardWidth, boardHeight);
		this.colSize = colSize;
		this.rowSize = rowSize;
		if ((kind == UP) || (kind == DOWN)){
			setSpeed(rowSize);
		}
		else if ((kind == LEFT) || (kind == RIGHT)){
			setSpeed(colSize);
		}
		this.kind = kind;
		initImages(kind);
	}
	
	///Initialization the images
	private void initImages(int kind){
		switch (kind) {
		case UP:
			image = Toolkit.getDefaultToolkit().getImage(Path + "upBirck.png");
			break;
		case DOWN:
			image = Toolkit.getDefaultToolkit().getImage(Path + "downBirck.png");
			break;
		case LEFT:
			image = Toolkit.getDefaultToolkit().getImage(Path + "leftBirck.png");
			break;
		case RIGHT:
			image = Toolkit.getDefaultToolkit().getImage(Path + "rightBirck.png");
			break;
		default:
			break;
		}
	}
	
	///Set the saving of board sizing
	public void setBoardSize(int boardWidth, int boardHeight, int colSize, int rowSize){
		super.setBoardSize(boardWidth, boardHeight);
		this.colSize = colSize;
		this.rowSize = rowSize;
		if ((kind == UP) || (kind == DOWN)){
			setSpeed(rowSize);
		}
		else if ((kind == LEFT) || (kind == RIGHT)){
			setSpeed(colSize);
		}
	}
	
	///Draw the brick
	public void draw(Graphics2D g, ImageObserver ob){
		//TODO
	}
}

