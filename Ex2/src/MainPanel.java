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

	private final int boardSize = 4;
	private final int spaceInBoard = 10;
	private final String Path = "C:\\Users\\User\\workspace\\Ex2\\src\\";
	
	private BoardPanel t;
	private Board  board= new Board(boardSize);
	
	public MainPanel(){
		t = new BoardPanel();
		setLayout(new BorderLayout());
	    add(t, BorderLayout.CENTER);
	    
	    addKeyListener(new Listener());
	    setFocusable(true);
	}
	
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	private class BoardPanel extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        int boardSize = board.getSize();
	        int row = (getWidth()/boardSize) - (spaceInBoard);
	        int col = (getHeight()/boardSize) - (spaceInBoard);
	        int x = 0;
	        int y = 0;
	        Image img = null;
	        
	        for (int i = 0; i < boardSize ; i++){
	        	for (int j = 0; j < boardSize ; j++){
	        		switch (board.getNumber(i, j)) { 
	        		case 0:
		        			img = Toolkit.getDefaultToolkit().getImage(Path + "number0.jpg");
		    		        break;
	        		case 1:
		        			img = Toolkit.getDefaultToolkit().getImage(Path + "number1.jpg");
		    		        break;
	        		case 2:
		        			img = Toolkit.getDefaultToolkit().getImage(Path + "number2.jpg");
		    		        break;
	        		case 3:
		        			img = Toolkit.getDefaultToolkit().getImage(Path + "number3.jpg");
		    		        break;
	        		case 4:
		        			img = Toolkit.getDefaultToolkit().getImage(Path + "number4.jpg");
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
	
	private class Listener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int key = e.getKeyCode();
			switch (key){
			case (KeyEvent.VK_LEFT):
				board.moveAllLeft();
				repaint();
				break;
			}
		}
		
	}
}
