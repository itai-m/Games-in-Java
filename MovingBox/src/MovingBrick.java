import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MovingBrick extends Sprite{
	
	private final String Path =  System.getProperty("user.dir") + "\\image\\";
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	public static final int RIGHT = 5;
	
	private int kind;
	private int colSize;
	private int rowSize;
	private BufferedImage image;
	
	///Default constructor
	public MovingBrick(){
		super();
		kind = 0;
		initImages(0);
	}
	
	///Constructors
	public MovingBrick(float x, float y, int kind, int colSize, int rowSize, int boardWidth, int boardHeight){
		super(x, y, 1, 0, 1, boardWidth, boardHeight);
		this.colSize = colSize;
		this.rowSize = rowSize;
		this.kind = kind;
		switch (kind) {
		case UP:
			setDirection(new Vector2f(0, -1));
			break;
		case DOWN:
			setDirection(new Vector2f(0, 1));
			break;
		case LEFT:
			setDirection(new Vector2f(-1, 0));
			break;
		case RIGHT:
			setDirection(new Vector2f(1, 0));
			break;
		default:
			break;
		}
		initImages(kind);
	}
	
	///Initialization the images
	private void initImages(int kind){
		BufferedImage tempBrikcImage = null;
		BufferedImage tempArrowImage = null;
		try {
			tempBrikcImage = ImageIO.read( new File( Path + "woodenBrikc.jpg" ) );
			tempArrowImage = ImageIO.read( new File( Path + "BlueArrow.png" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = new BufferedImage(tempBrikcImage.getWidth(), tempBrikcImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		
		switch (kind) {
		case UP:
			tempArrowImage = rotateImage(tempArrowImage, 270);
			break;
		case DOWN:
			tempArrowImage = rotateImage(tempArrowImage, 90);
			break;
		case LEFT:
			tempArrowImage = rotateImage(tempArrowImage, 180);
			break;
		case RIGHT:
			tempArrowImage = rotateImage(tempArrowImage, 0);
			break;
		default:
			break;
		}
		g2d.drawImage(tempBrikcImage, 0, 0, null);
		g2d.drawImage(tempArrowImage, tempBrikcImage.getWidth() / 4, tempBrikcImage.getHeight() / 4, tempBrikcImage.getWidth() / 2, tempBrikcImage.getHeight() / 2, null);
	}
	
	///Get the kind of the brick
	public int getKind() {
		return kind;
	}

	///Set the saving of board sizing
	public void setBoardSize(int boardWidth, int boardHeight, int colSize, int rowSize){
		super.setBoardSize(boardWidth, boardHeight);
		this.colSize = colSize;
		this.rowSize = rowSize;
	}
	
	///Draw the brick
	public void draw(Graphics2D g, ImageObserver ob){
		g.drawImage(image, (int)getPosition().getX() * colSize, (int)getPosition().getY() * rowSize, colSize, rowSize, ob);
	}
	
	
}

