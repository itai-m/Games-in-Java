
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



public class GamePanel extends JPanel implements ActionListener , Runnable{

	private static final int PERIOD = 40;
	
	private GameEngine game;
	private Listener keyboardListener;
	private boolean running;
	
	private Image bgImage;
    private BufferedImage dbImg = null;
	
	///Constructor
	public GamePanel(){
		game = new GameEngine( Main.initializedWidth, Main.initializedHight);
		running = true;
		
		///Initializing the listener.
		keyboardListener = new Listener();
		addKeyListener(keyboardListener);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		game.setBoardSize(getWidth(), getHeight());
        //gameRender();
        g.drawImage(dbImg, 0, 0, this);
	}
	
	public void run() {
		long before, diff, sleepTime;
        before = System.currentTimeMillis();
        running = true;
        
        while(running){
        	game.update();
            gameRender();
            paintScreen();   // active rendering
    		
            diff = System.currentTimeMillis() - before;
            sleepTime = PERIOD - diff;
            if(sleepTime <= 0)
                sleepTime = 5;

            try {
                Thread.sleep(sleepTime);
            }
            catch(InterruptedException e){}

            before = System.currentTimeMillis();
        }
		
	}
	
	///Render all the game using double buffering
	private void gameRender()
    {
        Graphics dbg;
        dbImg = new BufferedImage(game.getBoardWidth(), game.getBoardHeight(), BufferedImage.OPAQUE);
        dbg = dbImg.createGraphics();
        dbg.drawImage(bgImage, 0, 0, this);
        
        // draw game elements
        game.draw((Graphics2D) dbg, this);
    }

	///Paint a image to the screen
	private void paintScreen(){
        Graphics g;
        try {
            g = getGraphics();
            if(g != null && dbImg != null)
            {
                g.drawImage(dbImg, 0, 0, null);
            }
        }
        catch(Exception e){
            System.out.println("Graphics error");
            e.printStackTrace();
        }
    }
		
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// only start the animation once the JPanel has been added to the JFrame
    public void addNotify()
    { 
        super.addNotify();   // creates the peer
        startGame();    // start the thread
    }
    
    ///Start the game
    public void startGame()
    {
        (new Thread(this)).start();
    }

    
    ///The listener class for listen to the keyboard
  	private class Listener extends KeyAdapter{
  		@Override
  		public void keyPressed(KeyEvent e) {
  			super.keyPressed(e);
  			if (e.getKeyCode() == KeyEvent.VK_LEFT){
  				game.MoveLeft();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
  				game.MoveRight();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_UP){
  				game.MoveUp();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_DOWN){
  				game.MoveDown();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_SPACE){
  				game.shot();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_R){
  				game.resetLevel();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_A){
  				game.changePlayerAppearance();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_S){
  				game.changeMute();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_Q){
  				game.changeSBVisible();
  			}
  			if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
  				//TODO: end the game
  			}
  			repaint();
  		}
  	}
}
