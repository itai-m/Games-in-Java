
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

	private final float SHIPSIZE = 50;
	private final String Path =  System.getProperty("user.dir") + "\\src\\";
	
	///Default constructor
	public Ship(){
		super();
	}
	
	///Constructors
	public Ship(Vector2f position, Vector2f direction, float speed){
		super(position, direction, speed);
	}
	
	public Ship(float posX, float posY, float dirX, float dirY, float speed){
		super(posX, posY, dirX, dirY, speed);
	}
	
	///Speed up the ship
	public void speedUp(){
		speed += acceleration*2;
	}
	
	///Draw the ship
	public void draw(Graphics2D g, ImageObserver ob){
		BufferedImage image = null;
		Vector2f loc = getPosition();
		try {
			image = ImageIO.read( new File( Path + "off.png" ) );
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		image = rotate(image,Math.toRadians(GetAngle() + 180) , ob );
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

}
