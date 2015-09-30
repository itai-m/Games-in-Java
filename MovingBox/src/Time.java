import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Time {

	private long time;
	
	///Constructors
	public Time(){
		time = System.currentTimeMillis();
	}
	
	///return the time in String
	public String toString(){
		long timeNow = (System.currentTimeMillis() - time) / 1000;
		int h = (int) (timeNow / (3600));
		int m = (int) ((timeNow - (h * 3600)) / 60);
		int s = (int) (timeNow - (h * 3600) - m * 60);

		return String.format("%02d:%02d:%02d", h,m,s);
	}
	
	///Reset the time
	public void reset(){
		time = System.currentTimeMillis();
	}
	
	///Draw the time
	public void drawTime(Graphics g, int boardWidth){
		String msg = this.toString();
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString(msg, boardWidth - msg.length() * 15, 30);
    }
}
