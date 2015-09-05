import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Astroid extends Sprite{
	
	public static final int DRAW_SIZE = 30;
	
	private int size;
	
	///Default constructor
	public Astroid(){
		super();
		size = 3;
	}
	
	///Constructor
	public Astroid(Vector2f position, Vector2f direction, float speed ,int size, int boardWidth, int boardHeight){
		super(position, direction, speed, boardWidth, boardHeight);
		this.size = size;
	}
	
	public Astroid(float posX, float posY, float dirX, float dirY, float speed ,int size, int boardWidth, int boardHeight){
		super(posX, posY, dirX, dirY, speed, boardWidth,  boardHeight);
		this.size = size;
	}
 
	///Split the asteroid
	public Astroid split(){
		if (size <= 0)
			return null;
		Astroid temp = new Astroid(getPosition().getX(), getPosition().getY(), getDirection().getX(), getDirection().getY(), getSpeed() + 1, --size, getBoardWidth(), getBoardHeight());
		temp.rotate((float)getRand(180, 360));
		this.rotate((float) getRand(0,180));
		this.speedUp();
		return temp;
	}
	
	///Check if destroyed
	public boolean isDestroyed(){
		if (size == 0)
			return true;
		return false;
	}
	
	///Get the size
	public int getSize() {
		return size;
	}

	///Set the size
	public void setSize(int size) {
		this.size = size;
	}
	
	///get a random number
	private double getRand(double min, int max){
		Random rand = new Random();
		return rand.nextInt(max) + min;
	}
	
	///Draw the asteroid
	public void draw(Graphics2D g, ImageObserver ob){
		Image img = Toolkit.getDefaultToolkit().getImage(GameEngine.Path + "astroid.png");
		int tempSize = DRAW_SIZE*size;
		g.drawImage(img, (int)getPosition().getX() - (tempSize / 2), (int)getPosition().getY() - (tempSize / 2), tempSize, tempSize, ob);
	}
	
	///Set the asteroid to a random location 
	public void randDirection(){
		rotate((float) getRand(0, 360));
	}
}
