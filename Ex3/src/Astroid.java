import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Astroid extends Sprite{
	
	private final int DRAW_SIZE = 30;
	
	private int size;
	
	///Default constructor
	public Astroid(){
		super();
		size = 3;
	}
	
	///Constructor
	public Astroid(Vector2f position, Vector2f direction, float speed ,int size){
		super(position, direction, speed);
		this.size = size;
	}
	
	public Astroid(float posX, float posY, float dirX, float dirY, float speed ,int size){
		super(posX, posY, dirX, dirY, speed);
		this.size = size;
	}

	///Split the asteroid
	public Astroid split(){
		Astroid temp = new Astroid(getPosition().getX(), getPosition().getY(), getDirection().getX(), getDirection().getY(), getSpeed() + 1, --size);
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
		Image img = Toolkit.getDefaultToolkit().getImage(GameEngine.Path + "astroid.jpg");
		g.drawImage(img, (int)getPosition().getX(), (int)getPosition().getY(), DRAW_SIZE*size, DRAW_SIZE*size, ob);
	}
	
	///Set the asteroid to a random location 
	public void randLoction(){
		
	}
}
