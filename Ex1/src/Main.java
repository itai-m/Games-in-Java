
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Main {
	private final static int initializedHight = 650;
	private final static int initializedWidth = 800;
	
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Ex1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(initializedWidth, initializedHight);
        MainPanel p = new MainPanel();
        frame.add(p);
        frame.setVisible(true);
        
    }
}
