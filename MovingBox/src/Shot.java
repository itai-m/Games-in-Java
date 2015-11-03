import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shot extends Sprite{
	
	private final static int SPEED = 10;
	private final int[] SPRITE_WIDTH = {0, 50, 95, 145, 224, 307, 374};
	private final int STEP_PER_PIC = 14;
	private final int SPRITE_HEIGHT = 48;
	private final int MAX_STEP = STEP_PER_PIC * 8;
	private final double RATIO_TO_THE_BOARD = 0.05;
	
	private BufferedImage imag;
	private double height;
	private double width;
	private int step;
	
	///Default constructor
	public Shot(){
		super();
		initImages();
	}
	///Constructor
	public Shot(float posX, float posY, float dirX, float dirY, int boardWidth, int boardHeight){
		super(posX, posY, dirX, dirY, SPEED, boardWidth, boardHeight);
		this.height =  (boardHeight * RATIO_TO_THE_BOARD);
		this.width =  (boardWidth * RATIO_TO_THE_BOARD);
		step = 0;
		initImages();
	}
	
	///Initialization the images
	private void initImages(){
		try {
			if (getDirection().getY() != 0){
				imag = ImageIO.read( new File( TemplateHandler.getPath() + "shot2.png" ) );
			}
			else{
				imag = ImageIO.read( new File( TemplateHandler.getPath() + "shot.png" ) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	///Check if the shot pass is time
	public boolean isOver(){
		if (step >= MAX_STEP)
			return true;
		return false;
	}
	
	///Draw the shot
	public void draw(Graphics2D g, ImageObserver ob){
		int x1 = (int)getPosition().getX();
		int y1 = (int)getPosition().getY();
		int x2 = (int)getPosition().getX();
		int y2 = (int)getPosition().getY();
		if (getDirection().getX() != 0){
			y1 -= height / 2;
			y2 += height /2;
			if (getDirection().getX() == 1){
				x1 -= width / 2;
				x2 += width / 2;
			}
			else{
				x1 += width / 2;
				x2 -= width / 2;
			}
			if (step < STEP_PER_PIC){
				g.drawImage(imag, x1, y1, x2, y2, SPRITE_WIDTH[0], 0, SPRITE_WIDTH[1], SPRITE_HEIGHT, ob);
			}
			else if (step < STEP_PER_PIC * 2){
				g.drawImage(imag, x1, y1, x2, y2, SPRITE_WIDTH[1], 0, SPRITE_WIDTH[2], SPRITE_HEIGHT, ob);
			}
			else if (step < STEP_PER_PIC * 3){
				g.drawImage(imag, x1, y1, x2, y2, SPRITE_WIDTH[2], 0, SPRITE_WIDTH[3], SPRITE_HEIGHT, ob);
			}
			else if (step < STEP_PER_PIC * 4){
				g.drawImage(imag, x1, y1, x2, y2, SPRITE_WIDTH[3], 0, SPRITE_WIDTH[4], SPRITE_HEIGHT, ob);
			}
			else if (step < STEP_PER_PIC * 5){
				g.drawImage(imag, x1, y1, x2, y2, SPRITE_WIDTH[4], 0, SPRITE_WIDTH[5], SPRITE_HEIGHT, ob);
			}
			else if (step >= STEP_PER_PIC * 5){
				g.drawImage(imag, x1, y1, x2, y2, SPRITE_WIDTH[5], 0, SPRITE_WIDTH[6], SPRITE_HEIGHT, ob);
			}
			
		}
		else{ 
			x1 -= width / 2;
			x2 += width / 2;
			
			if (getDirection().getY() == 1){
				y1 -= height / 2;
				y2 += height /2;
			}
			else{
				y1 += height / 2;
				y2 -= height /2;
			}
			
			if (step < STEP_PER_PIC){
				g.drawImage(imag, x1, y1, x2, y2, 0, SPRITE_WIDTH[0], SPRITE_HEIGHT, SPRITE_WIDTH[1], ob);
			}
			else if (step < STEP_PER_PIC * 2){
				g.drawImage(imag, x1, y1, x2, y2, 0, SPRITE_WIDTH[1], SPRITE_HEIGHT, SPRITE_WIDTH[2], ob);
			}
			else if (step < STEP_PER_PIC * 3){
				g.drawImage(imag, x1, y1, x2, y2, 0, SPRITE_WIDTH[2], SPRITE_HEIGHT, SPRITE_WIDTH[3], ob);
			}
			else if (step < STEP_PER_PIC * 4){
				g.drawImage(imag, x1, y1, x2, y2, 0, SPRITE_WIDTH[3], SPRITE_HEIGHT, SPRITE_WIDTH[4], ob);
			}
			else if (step < STEP_PER_PIC * 5){
				g.drawImage(imag, x1, y1, x2, y2, 0, SPRITE_WIDTH[4], SPRITE_HEIGHT, SPRITE_WIDTH[5], ob);
			}
			else if (step >= STEP_PER_PIC * 5){
				g.drawImage(imag, x1, y1, x2, y2, 0, SPRITE_WIDTH[5], SPRITE_HEIGHT, SPRITE_WIDTH[6], ob);
			}
		}
		
	}

	///Get the height
	public double getHeight() {
		return height;
	}

	///Set the height
	public void setHeight(double height) {
		this.height = height;
	}

	///Get the width
	public double getWidth() {
		return width;
	}

	///Set the width
	public void setWidth(double width) {
		this.width = width;
	}
	
	///Move the shot
	public void move(){
		super.move();
		step++;
	}
	
	///Resizing the board size
	public void setBoardSize(int boardWidth, int boardHeight){
		super.setBoardSize(boardWidth, boardHeight);
		this.height =  (boardHeight * RATIO_TO_THE_BOARD);
		this.width =  (boardWidth * RATIO_TO_THE_BOARD);
	}
}
