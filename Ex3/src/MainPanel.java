import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements ActionListener , Runnable {
	
	
	private final int timeForInterval = 50;
	private static final int PERIOD = 40;

	private Listener keyboard_listener;
	private GameEngine game = new GameEngine(Main.initializedHight, Main.initializedHight, 0, -1, 0);
	private Timer timer;
	private boolean running;
	private Image bgImage;
    private BufferedImage dbImg = null;
	
	public MainPanel(){
		///Initializing the board
		setLayout(new BorderLayout());
		timer = new Timer(timeForInterval, this);
		timer.start();
		
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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        gameRender();
        game.setBoardSize(getWidth(), getHeight());
        g.drawImage(dbImg, 0, 0, this);
		/*
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		this.setBackground(Color.BLACK);
		game.draw(g2d, this);
		int status = game.update();
		switch (status) {
		case GameEngine.WIN:
			System.out.println("win");
			///TODO win option
			break;
		case GameEngine.LOSE:
			System.out.println("lose");
			//TODO Lose option
			break;
		case GameEngine.NOTHING:
			//keep going
			break;
		default:
			break;
		}*/
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
			case KeyEvent.VK_UP:
				game.speedUpShip();
				break;
			case KeyEvent.VK_SPACE:
				game.shot();
				break;
			case KeyEvent.VK_ESCAPE:
				//TODO: end the game
				break;
			}
			repaint();
		}
		
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
        catch(Exception e)
        {
            System.out.println("Graphics error");
            e.printStackTrace();
        }
    }

	///Render all the game using double buffering
	private void gameRender()
    {
        Graphics dbg;
        dbImg = new BufferedImage(game.shipBoardWidth(), game.shipBoardHeight(), BufferedImage.OPAQUE);
        dbg = dbImg.createGraphics();
        dbg.setColor(Color.BLACK);
        dbg.fillRect(0, 0, game.shipBoardWidth(), game.shipBoardHeight());
        dbg.drawImage(bgImage, 0, 0, this);
        
        // draw game elements
        game.draw((Graphics2D) dbg, this);
    }
	
	@Override
	public void run() {
		long before, diff, sleepTime;
        before = System.currentTimeMillis();
        running = true;
        
        while(running)
        {
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
}
