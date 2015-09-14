
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Component;
import javax.imageio.ImageIO;


public class Ship extends Sprite{

	public static final float SHIPSIZE = 50;
	private final int LIFES_SHIPS_SIZE = (int) (SHIPSIZE / 2);
	private final String Path =  System.getProperty("user.dir") + "\\src\\";
	
	private int lifes;
	
	///Default constructor
	public Ship(){
		super();
		lifes = 3;
	}
	
	///Constructors
	public Ship(Vector2f position, Vector2f direction, float speed, int lifes, int boardWidth, int boardHeight){
		super(position, direction, speed, boardWidth, boardHeight);
		this.lifes = lifes;
	}
	
	public Ship(int boardWidth, int boardHeight, float dirX, float dirY, float speed, int lifes){
		super(boardWidth / 2, boardHeight / 2, dirX, dirY, speed, boardWidth,  boardHeight);
		this.lifes = lifes;
	}
	
	///Speed up the ship
	public void speedUp(){
		speed += acceleration*2;
	}
	
	///Lose one life
	public void lifeDown(){
		lifes--;
	}
	
	///Check if the life's ship is over
	public boolean isDead(){
		if (lifes <= 0)
			return true;
		return false;
	}
	
	///Draw the ship
	public void draw(Graphics2D g, ImageObserver ob){
		BufferedImage image = null;
		BufferedImage lifesImage = null;
		Vector2f loc = getPosition();
		try {
			image = ImageIO.read( new File( Path + "off.png" ) );
			lifesImage = ImageIO.read( new File( Path + "off.png" ) );
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < lifes ; i++){
			g.drawImage(lifesImage, LIFES_SHIPS_SIZE*(i * 2 + 1), LIFES_SHIPS_SIZE, LIFES_SHIPS_SIZE, LIFES_SHIPS_SIZE, ob);
		}
		image = rotate(image,Math.toRadians(GetAngle() + 180) , ob );
		//image = rotateImage(image,(int) GetAngle() + 180 );
		g.drawImage(image, (int)(loc.getX()-SHIPSIZE/2), (int)(loc.getY()-SHIPSIZE/2), (int)(SHIPSIZE), (int)(SHIPSIZE),ob);
		
	}
	
	///Rotate an Image
	private BufferedImage rotate(BufferedImage image, double angle, ImageObserver ob) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = ((Component) ob).getGraphicsConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}
	
	
	//one more way to rotate image
	private BufferedImage rotateImage(BufferedImage img, int angle)
    {
        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = res.createGraphics();
        g2d.rotate(Math.toRadians(angle), img.getWidth()/2, img.getHeight()/2);
        
        g2d.drawImage(img, 0, 0, null);
     
        return res;
    }
}
