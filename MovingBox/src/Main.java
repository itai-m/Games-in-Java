import javax.swing.JFrame;

public class Main {
	public static int initializedHight = 800;
	public static int initializedWidth = 1200;
	
    public static void main(String[] args)
    {
		///create and the JFrame that contains the GamePanel
        JFrame frame = new JFrame("MovingBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(initializedWidth, initializedHight);
        GamePanel p = new GamePanel();
        frame.add(p);
        frame.setVisible(true);
        
    }
}
