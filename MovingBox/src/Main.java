import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static int initializedHight = 800;
	public static int initializedWidth = 1200;
	
	private static final int MIN_WIDTH = 200;
	private static final int MIN_HEIGHT = 200;
	
    public static void main(String[] args)
    {
		///create and the JFrame that contains the GamePanel
        JFrame frame = new JFrame("MovingBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(initializedWidth, initializedHight);
        frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        GamePanel p = new GamePanel();
        frame.add(p);
        frame.setVisible(true);
        
    }
}
