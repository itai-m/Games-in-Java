import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements ActionListener {
	
	private final float SHIPSIZE = 50;
	private final String Path =  System.getProperty("user.dir") + "\\src\\";;

	private BoardPanel t;
	private Listener keyboard_listener;
	private GameEngine game = new GameEngine(Main.initializedHight/2,Main.initializedHight/2, 0, -1, 0);
	
	public MainPanel(){
		///Initializing the board
		t = new BoardPanel();
		setLayout(new BorderLayout());
		add(t, BorderLayout.CENTER);

		///Initializing the listener.
		keyboard_listener = new Listener();
		addKeyListener(keyboard_listener);
		setFocusable(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	///The panel that the board draw on
	private class BoardPanel extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			BufferedImage image = null;
			this.setBackground(Color.BLACK);
			Vector2f loc = game.getShipLocation();
			//System.out.println(loc);
			try {
				image = ImageIO.read( new File( Path + "off.png" ) );
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
			image = rotate(image,Math.toRadians(game.getShipAngle() + 90) );
			g2d.drawImage(image, (int)(loc.getX()-SHIPSIZE/2), (int)(loc.getY()-SHIPSIZE/2), (int)(SHIPSIZE), (int)(SHIPSIZE), this);
			game.update();
		}
	}
	
	///The listener class for listen to the keyboard
	private class Listener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int key = e.getKeyCode();
			switch (key){
			case KeyEvent.VK_LEFT:
				game.moveShipLeft();
				break;
			case KeyEvent.VK_RIGHT:
				game.moveShipRight();
				break;
			case KeyEvent.VK_DOWN:
				
				break;
			case KeyEvent.VK_UP:
				game.speedUpShip();
				break;
			}
			repaint();
		}
		
	}

	public BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = getGraphicsConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}
}
