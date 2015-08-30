
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
	public static int initializedHight = 600;
	public static int initializedWidth = 600;
	
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Ex3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(initializedWidth, initializedHight);
        MainPanel p = new MainPanel();
        frame.add(p);
        frame.setVisible(true);
        
    }
}
