import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener , Runnable{

	private Board board;
	
	///Constructor
	public GamePanel(){
		board = new Board(10, 10, Main.initializedWidth, Main.initializedHight);
	}
	
	public void paintComponent(Graphics g){
		board.setBoardSize(getWidth(), getHeight());
		board.draw((Graphics2D) g, this);
	}
	
	@Override
	public void run() {
		board.setBoardSize(getWidth(), getHeight());
		
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

}
