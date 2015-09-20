
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
        gameRender();
        g.drawImage(dbImg, 0, 0, this);
	}
	
	@Override
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
		
	@Override
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
  			int key = e.getKeyCode();
  			switch (key){
  			case KeyEvent.VK_LEFT:
  				game.MoveLeft();
  				break;
  			case KeyEvent.VK_RIGHT:
  				game.MoveRight();
  				break;
  			case KeyEvent.VK_UP:
  				game.MoveUp();
  				break;
  			case KeyEvent.VK_DOWN:
  				game.MoveDown();
  				break;
  			case KeyEvent.VK_SPACE:
  				game.shot();
  				break;
  			case KeyEvent.VK_R:
  				game.resetLevel();
  				break;
  			case KeyEvent.VK_A:
  				game.changePlayerAppearance();
  				break;
  			case KeyEvent.VK_ESCAPE:
  				//TODO: end the game
  				break;
  			}
  			repaint();
  		}
  	}
}
