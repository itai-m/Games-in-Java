import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MovingBrick extends Sprite{
	
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	public static final int RIGHT = 5;
	
	private int kind;
	private int colSize;
	private int rowSize;
	private static BufferedImage imageUp;
	private static BufferedImage imageDown;
	private static BufferedImage imageLeft;
	private static BufferedImage imageRight;
	
	///Default constructor
	public MovingBrick(){
		super();
		kind = 0;
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
	}
	
	///Initialization the images
	public static void initImages(){
		BufferedImage tempBrikcImage = null;
		BufferedImage tempArrowImage = null;
		try {
			tempBrikcImage = ImageIO.read( new File( TemplateHandler.getPath() + "brikc.png" ) );
			tempArrowImage = ImageIO.read( new File( TemplateHandler.getPath() + "arrow.png" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Load the right box
		imageRight = new BufferedImage(tempBrikcImage.getWidth(), tempBrikcImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = imageRight.createGraphics();
		g2d.drawImage(tempBrikcImage, 0, 0, null);
		g2d.drawImage(tempArrowImage, tempBrikcImage.getWidth() / 4, tempBrikcImage.getHeight() / 4, tempBrikcImage.getWidth() / 2, tempBrikcImage.getHeight() / 2, null);
		
		//Load the down box
		tempArrowImage = rotateImage(tempArrowImage, 90);
		imageDown = new BufferedImage(tempBrikcImage.getWidth(), tempBrikcImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = imageDown.createGraphics();
		g2d.drawImage(tempBrikcImage, 0, 0, null);
		g2d.drawImage(tempArrowImage, tempBrikcImage.getWidth() / 4, tempBrikcImage.getHeight() / 4, tempBrikcImage.getWidth() / 2, tempBrikcImage.getHeight() / 2, null);
		
		//Load the left box
		tempArrowImage = rotateImage(tempArrowImage, 90);
		imageLeft = new BufferedImage(tempBrikcImage.getWidth(), tempBrikcImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = imageLeft.createGraphics();
		g2d.drawImage(tempBrikcImage, 0, 0, null);
		g2d.drawImage(tempArrowImage, tempBrikcImage.getWidth() / 4, tempBrikcImage.getHeight() / 4, tempBrikcImage.getWidth() / 2, tempBrikcImage.getHeight() / 2, null);
		
		//Load the down box
		tempArrowImage = rotateImage(tempArrowImage, 90);
		imageUp = new BufferedImage(tempBrikcImage.getWidth(), tempBrikcImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = imageUp.createGraphics();
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
		switch (kind) {
		case UP:
			g.drawImage(imageUp, (int)getPosition().getX() * colSize, (int)getPosition().getY() * rowSize, colSize, rowSize, ob);
			break;
		case DOWN:
			g.drawImage(imageDown, (int)getPosition().getX() * colSize, (int)getPosition().getY() * rowSize, colSize, rowSize, ob);
			break;
		case LEFT:
			g.drawImage(imageLeft, (int)getPosition().getX() * colSize, (int)getPosition().getY() * rowSize, colSize, rowSize, ob);
			break;
		case RIGHT:
			g.drawImage(imageRight, (int)getPosition().getX() * colSize, (int)getPosition().getY() * rowSize, colSize, rowSize, ob);
			break;
		default:
			break;
		}
	}
	
	
}

