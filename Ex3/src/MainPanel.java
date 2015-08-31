import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements ActionListener {
	
	
	private final int timeForInterval = 50;

	private BoardPanel t;
	private Listener keyboard_listener;
	private GameEngine game = new GameEngine(Main.initializedHight/2,Main.initializedHight/2, 0, -1, 0);
	private Timer timer;
	
	public MainPanel(){
		///Initializing the board
		t = new BoardPanel();
		setLayout(new BorderLayout());
		add(t, BorderLayout.CENTER);
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
	private class BoardPanel extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			this.setBackground(Color.BLACK);
			game.draw(g2d, this);
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
			case KeyEvent.VK_UP:
				game.speedUpShip();
				break;
			case KeyEvent.VK_SPACE:
				game.shot();
				break;
			}
			repaint();
		}
		
	}

}
