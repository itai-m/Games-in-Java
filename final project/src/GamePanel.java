import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;



public class GamePanel extends JPanel implements ActionListener , Runnable{

	private GameEngine game;
	private Listener keyboard_listener;
	
	///Constructor
	public GamePanel(){
		game = new GameEngine( Main.initializedWidth, Main.initializedHight);
		
		///Initializing the listener.
		keyboard_listener = new Listener();
		addKeyListener(keyboard_listener);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		game.setBoardSize(getWidth(), getHeight());
		game.draw((Graphics2D) g, this);
	}
	
	@Override
	public void run() {
		game.setBoardSize(getWidth(), getHeight());
		
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
  			case KeyEvent.VK_ESCAPE:
  				//TODO: end the game
  				break;
  			}
  			repaint();
  		}
  		
  	}
}
