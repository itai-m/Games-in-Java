import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Explosion extends Sprite{
	
	private final String WIN_PATH =  System.getProperty("user.dir") + "\\image\\";
	private final String LINUX_PATH =  System.getProperty("user.dir") + "/image/";
	private final int SPRITE_WIDTH = 64;
	private final int SPRITE_HEIGHT = 64;
	private final int NUMBER_OF_COL = 4;
	private final int NUMBER_OF_ROW = 4;
	private final int NUMBER_OF_FRAME = NUMBER_OF_ROW * NUMBER_OF_COL;
	
	private int step;
	private Image image;
	private int height;
	private int width;
	
	public Explosion(){
		step = 0;
	}
	
	///Constructor
	public Explosion(int posX, int posY, int dirX, int dirY, int width, int height, int boardWidth, int boardHeight){
		super(posX, posY, dirX, dirY, posY, boardWidth, boardHeight);
		step = 0;
		this.width = width;
		this.height = height;
		initImages();
	}
	
	///Initialization the images
	private void initImages(){
		String path = "";
		if (System.getProperty("os.name").equals("Linux")){
			path = LINUX_PATH;
		}
		else{
			path = WIN_PATH;
		}
		image = Toolkit.getDefaultToolkit().getImage(path + "explosion.png");
	}

	///Set the board width and height
	public void setBoardSize(int boardWidth, int boardHeight){
		super.setBoardSize(boardWidth, boardHeight);
	}
	
	///Draw the the explosion
	public void draw(Graphics2D g, ImageObserver ob){
		int x = (int) getPosition().getX();
		int y = (int) getPosition().getY();
		int col = step % NUMBER_OF_COL;
		int row = step / NUMBER_OF_ROW;
		g.drawImage(image, x - width/2, y - height/2, x + width/2, y + height/2, SPRITE_WIDTH * col, SPRITE_HEIGHT * row, SPRITE_WIDTH * (col+1), SPRITE_HEIGHT * (row+1), ob);
	}
	
	///Check if the explosion over
	public boolean isOver(){
		if (step >= NUMBER_OF_FRAME){
			return true;
		}
		return false;
	}
	
	///Move the explosion
	public void move(){
		step++;
	}
}
