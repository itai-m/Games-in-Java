
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
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Ex1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        MainPanel p = new MainPanel();
        frame.add(p);
        frame.setVisible(true);
        
    }
}
