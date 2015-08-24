import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;




public class MainPanel extends JPanel implements ActionListener {

	///Constant variables
	private final int boardSize = 4;
	private final int spaceInBoard = 10;
	private final String Path =  System.getProperty("user.dir") + "\\src\\";;

	///Define all the objects
	private BoardPanel t;
	private Board board = new Board(boardSize);
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
		final int FONT_SIZE = 48;
		final String WINNER_ANNOUNCEMENT = "You Win";
		final String LOSER_ANNOUNCEMENT = "You Lose";
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			if (keyboard_listener.getState() == Board.WIN)
			{
				this.setBackground(Color.GRAY);
				g.setColor(Color.GREEN);
				
				g.setFont(new Font("Courier New", Font.BOLD, FONT_SIZE));
				g.drawString(WINNER_ANNOUNCEMENT, (getWidth()/2)-(FONT_SIZE*(WINNER_ANNOUNCEMENT.length()+1)/((int)Math.round((double)WINNER_ANNOUNCEMENT.length()/2))), getHeight()/2);
				return;
			}
			if (keyboard_listener.getState() == Board.LOSE)
			{
				this.setBackground(Color.GRAY);
				g.setFont(new Font("Courier New", Font.BOLD, FONT_SIZE));
				g.drawString(LOSER_ANNOUNCEMENT, (getWidth()/2)-(FONT_SIZE*(LOSER_ANNOUNCEMENT.length()+1)/((int)Math.round((double)LOSER_ANNOUNCEMENT.length()/2))), getHeight()/2);
				return;
			}
			int boardSize = board.getSize();
			int row = (getWidth()/boardSize) - (spaceInBoard); ///save the size of the tile width
			int col = (getHeight()/boardSize) - (spaceInBoard); ///save the size of the tile height
			int x = 0;
			int y = 0;
			Image img = null;
			///Fit every tile to the right image
			for (int i = 0; i < boardSize ; i++){
				for (int j = 0; j < boardSize ; j++){
					switch (board.getNumber(i, j)) { 
					case 0:
						img = Toolkit.getDefaultToolkit().getImage(Path + "0.png");
						break;
					case 1:
						img = Toolkit.getDefaultToolkit().getImage(Path + "2.png");
						break;
					case 2:
						img = Toolkit.getDefaultToolkit().getImage(Path + "4.png");
						break;
					case 3:
						img = Toolkit.getDefaultToolkit().getImage(Path + "8.png");
						break;
					case 4:
						img = Toolkit.getDefaultToolkit().getImage(Path + "16.png");
						break;
					case 5:
						img = Toolkit.getDefaultToolkit().getImage(Path + "32.png");
						break;
					case 6:
						img = Toolkit.getDefaultToolkit().getImage(Path + "64.png");
						break;
					case 7:
						img = Toolkit.getDefaultToolkit().getImage(Path + "128.png");
						break;
					case 8:
						img = Toolkit.getDefaultToolkit().getImage(Path + "256.png");
						break;
					case 9:
						img = Toolkit.getDefaultToolkit().getImage(Path + "512.png");
						break;
					case 10:
						img = Toolkit.getDefaultToolkit().getImage(Path + "1024.png");
						break;
					case 11:
						img = Toolkit.getDefaultToolkit().getImage(Path + "2048.png");
						break;
					}
					g2d.drawImage(img, x, y, row, col, this);
					x += row + spaceInBoard;

				}
				x = 0;
				y += col + spaceInBoard;
			}

		}
	}
	///The listener class for listen to the keyboard
	private class Listener extends KeyAdapter{

		private int STATE = Board.NOTHING;

		public int getState()
		{
			return STATE;
		}
		private boolean keep_playing()
		{
			return (STATE == Board.NOTHING);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if (keep_playing())
			{
				int status = 0;
				int key = e.getKeyCode();
				switch (key){
				case KeyEvent.VK_LEFT:
					status = board.moveAllTo(Board.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					status = board.moveAllTo(Board.RIGHT);
					break;
				case KeyEvent.VK_DOWN:
					status = board.moveAllTo(Board.DOWN);
					break;
				case KeyEvent.VK_UP:
					status = board.moveAllTo(Board.UP);
					break;
				}
				switch (status){
				case Board.WIN:
					STATE = Board.WIN;
					break;
				case Board.LOSE:
					STATE = Board.LOSE;
					break;
				case Board.NOTHING:
					STATE = Board.NOTHING;
				}
				repaint();
			}
		}

	}
}
