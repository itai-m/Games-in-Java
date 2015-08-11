import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;


public class MainPanel extends JPanel implements ActionListener{
	private final int ballSize = 40;
	private final String Path = "C:\\Users\\Itai\\workspace\\Games-in-Java\\Ex1\\src\\";
	private final double defaultGravConst = 9.8;
	private final double earthGravConst = 9.8;
	private final double moonGravConst = 1.6;
	private final double marsGravConst = 3.8;
	private final double otherGravConst = 5;
	private final double defaultCOR = 0.8;
	
	private JComboBox<String> wallcombo;
	private JButton cmdStart, cmdReset;
	private JLabel lblEnvironment, lblGrev, lblEla, lblIni;
	private JSpinner spinGrav, spinEla, spinIni;
	private String trans = "";
	private BallPanel t;
	private SpinnerModel  graSpinnerModel = new SpinnerNumberModel(9.8, 0, 50, 0.2);
	private SpinnerModel  elaSpinnerModel = new SpinnerNumberModel(0.8, 0, 1, 0.1);
	private SpinnerModel  iniSpinnerModel = new SpinnerNumberModel(50, 1, 500, 1);
	private Timer timer;
	private Ball ball;
	
	
	
	public MainPanel(){
		lblEnvironment = new JLabel("Environment:");
		String[] s = {"Default", "Earth", "Moon", "Mars", "Other"};
		wallcombo = new JComboBox<>(s);
		lblGrev = new JLabel("Gravitational constant:");
		spinGrav = new JSpinner(graSpinnerModel);
		lblEla = new JLabel("Elastic constant:");
		spinEla = new JSpinner(elaSpinnerModel);
		lblIni = new JLabel("Initialized hight:");
		spinIni = new JSpinner(iniSpinnerModel);
		cmdReset = new JButton("Reset");
		cmdStart = new JButton("Start");
		timer = new Timer(20,this);
		timer.start();
		ball = new Ball(200, 500, ballSize, 0, defaultGravConst, defaultCOR);
		System.out.println("Height: " +getHeight());
		wallcombo.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                trans = (String)wallcombo.getSelectedItem();
                if (trans.equals("Earth")){
                	spinGrav.setValue(earthGravConst);
                	ball.changeInv((double)spinEla.getValue(), (double)spinGrav.getValue());
    	        }
    	        else if (trans.equals("Moon")){
    	        	spinGrav.setValue(moonGravConst);
    	        	ball.changeInv((double)spinEla.getValue(), (double)spinGrav.getValue());
    	        }
    	        else if (trans.equals("Mars")){
    	        	spinGrav.setValue(marsGravConst);
    	        	ball.changeInv((double)spinEla.getValue(), (double)spinGrav.getValue());
    	        }
    	        else if (trans.equals("Other")){
    	        	spinGrav.setValue(otherGravConst);
    	        	ball.changeInv((double)spinEla.getValue(), (double)spinGrav.getValue());
    	        }
    	        else{
    		        
    	        }
                t.repaint();
            }
        });
		
		cmdReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ball.setY((int)spinIni.getValue());
            	timer.stop();
                t.repaint();
            }
        });
		
		cmdStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ball.changeInv((double)spinEla.getValue(), (double)spinGrav.getValue());
            	timer.start();
                t.repaint();
            }
        });
		
		
        setLayout(new BorderLayout());
        JPanel pNorth = new JPanel();
        pNorth.add(lblEnvironment);
        pNorth.add(wallcombo);
        pNorth.add(lblGrev);
        pNorth.add(spinGrav);
        pNorth.add(lblEla);
        pNorth.add(spinEla);
        pNorth.add(lblIni);
        pNorth.add(spinIni);
        pNorth.add(cmdReset);
        pNorth.add(cmdStart);
        add(pNorth, BorderLayout.NORTH);
        t = new BallPanel();
        add(t, BorderLayout.CENTER);
	}
	
	
	private class BallPanel extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        if (trans.equals("Earth")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "earth.jpg");
		        g2d.drawImage(img, 0, 0, this);
	        }
	        else if (trans.equals("Moon")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "moon.jpg");
		        g2d.drawImage(img, 0, 0, this);
	        }
	        else if (trans.equals("Mars")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "mars.jpg");
		        g2d.drawImage(img, 0, 0, this);
	        }
	        else if (trans.equals("Other")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "other.jpg");
		        g2d.drawImage(img, 0, 0, this);
	        }
	        else{
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "default.jpg");
		        g2d.drawImage(img, 0, 0, this);
	        }
	        g2d.setColor(new Color(255, 255, 255, 200));
	        g2d.fillOval( (int)ball.getX(), (int)(getHeight()-ball.getY()), (int)ball.getSize(), (int)ball.getSize());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		ball.step();
	}
}

