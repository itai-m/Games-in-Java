import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;


public class MainPanel extends JPanel implements ActionListener {

	private BoardPanel t;
	private Listener keyboard_listener;
	
	
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
			this.setBackground(Color.BLACK);
		}
	}
	
	///The listener class for listen to the keyboard
	private class Listener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int status = 0;
			int key = e.getKeyCode();
			switch (key){
			case KeyEvent.VK_LEFT:
				
				break;
			case KeyEvent.VK_RIGHT:
				
				break;
			case KeyEvent.VK_DOWN:
				
				break;
			case KeyEvent.VK_UP:
				
				break;
			}
		}
	}

}
