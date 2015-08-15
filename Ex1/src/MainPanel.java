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
	
	///constant Variables
	private final int ballSize = 40;
	private final String Path = "C:\\Users\\Itai\\workspace\\Games-in-Java\\Ex1\\src\\";
	private final double defaultGravConst = 9.8;
	private final double earthGravConst = 9.8;
	private final double moonGravConst = 1.6;
	private final double marsGravConst = 3.8;
	private final double otherGravConst = 5;
	private final double defaultCOR = 0.8;
	private final double defaltBallHight = 10;
	private final double defaltBallWight = 325;
	private final int timeForInterval = 50;
	
	///define all the objects
	private JComboBox<String> galaxiescombo;
	private JButton cmdStart, cmdReset;
	private JLabel lblEnvironment, lblGrev, lblEla, lblIni;
	private JSpinner spinGrav, spinEla, spinIni;
	private String trans = "";
	private BallPanel t;
	private Timer timer;
	private Ball ball;
	
	///define and restart the spinner Models 
	private SpinnerModel  graSpinnerModel = new SpinnerNumberModel(defaultGravConst, 0, 50, 0.2);
	private SpinnerModel  elaSpinnerModel = new SpinnerNumberModel(defaultCOR, 0, 1, 0.1);
	private SpinnerModel  iniSpinnerModel = new SpinnerNumberModel(defaltBallHight, 1, 100, 10);
	
	
	
	public MainPanel(){
		
		///initializing the all the object 
		lblEnvironment = new JLabel("Environment:");
		String[] galaxies = {"Default", "Earth", "Moon", "Mars", "Other"};
		galaxiescombo = new JComboBox<>(galaxies);
		lblGrev = new JLabel("Gravitational constant:");
		spinGrav = new JSpinner(graSpinnerModel);
		lblEla = new JLabel("Elastic constant:");
		spinEla = new JSpinner(elaSpinnerModel);
		lblIni = new JLabel("Initialized hight:");
		spinIni = new JSpinner(iniSpinnerModel);
		cmdReset = new JButton("Reset");
		cmdStart = new JButton("Start");
		timer = new Timer(timeForInterval,this);
		timer.start();
		ball = new Ball(defaltBallWight, defaltBallHight*6, ballSize, 0, defaultGravConst, defaultCOR);
		
		///Listener to the galaxies comboBox and change in accordance with ball and the spinners 
		galaxiescombo.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                trans = (String)galaxiescombo.getSelectedItem();
                if (trans.equals("Earth")){
                	spinGrav.setValue(earthGravConst);
                	ball.changeEnv((double)spinEla.getValue(), earthGravConst);
    	        }
    	        else if (trans.equals("Moon")){
    	        	spinGrav.setValue(moonGravConst);
    	        	ball.changeEnv((double)spinEla.getValue(), moonGravConst);
    	        }
    	        else if (trans.equals("Mars")){
    	        	spinGrav.setValue(marsGravConst);
    	        	ball.changeEnv((double)spinEla.getValue(), marsGravConst);
    	        }
    	        else if (trans.equals("Other")){
    	        	spinGrav.setValue(otherGravConst);
    	        	ball.changeEnv((double)spinEla.getValue(), otherGravConst);
    	        }
    	        else{
    	        	spinGrav.setValue(defaultGravConst);
    	        	ball.changeEnv((double)spinEla.getValue(), defaultGravConst);
    	        }
                t.repaint();
            }
        });
		
		///Listener to the reset button
		cmdReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ball.restart((((double)spinIni.getValue())/100)*(getHeight()-50));
            	timer.stop();
                t.repaint();
            }
        });
		
		///Listener to the start button
		cmdStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ball.restart((((double)spinIni.getValue())/100)*(getHeight()-50));
            	ball.changeEnv((double)spinEla.getValue(), (double)spinGrav.getValue());
            	timer.start();
                t.repaint();
            }
        });
		
		///set all the objects in the panel
        setLayout(new BorderLayout());
        JPanel pNorth = new JPanel();
        pNorth.add(lblEnvironment);
        pNorth.add(galaxiescombo);
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
	        
	        ///change the Background in accordance to the galaxy
	        if (trans.equals("Earth")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "earth.jpg");
		        g2d.drawImage(img,0,0,getWidth(),getHeight(),this);
	        }
	        else if (trans.equals("Moon")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "moon.jpg");
		        g2d.drawImage(img,0,0,getWidth(),getHeight(),this);
	        }
	        else if (trans.equals("Mars")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "mars.jpg");
		        g2d.drawImage(img,0,0,getWidth(),getHeight(),this);
	        }
	        else if (trans.equals("Other")){
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "other.jpg");
		        g2d.drawImage(img,0,0,getWidth(),getHeight(),this);
	        }
	        else{
		        Image img = Toolkit.getDefaultToolkit().getImage(Path + "default.jpg");
		        g2d.drawImage(img,0,0,getWidth(),getHeight(),this);
	        }
	        
	        ///paint the ball
	        g2d.setColor(new Color(255, 255, 255, 200));
	        g2d.fillOval( (int) ((getWidth()/2)-(ball.getSize()/2)), (int)(getHeight()-ball.getY()), (int)ball.getSize(), (int)ball.getSize());
		}
	}

	//repaint and to one step of the ball in every click of the timer
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		ball.step();
	}
}

