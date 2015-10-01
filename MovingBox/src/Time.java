import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Time {

	private long time;
	
	///Constructors
	public Time(){
		time = System.currentTimeMillis();
	}
	
	public Time(long time){
		this.time = time;
	}
	
	public Time(Time time){
		this.time = time.getTime();
	}
	
	///Stop the time
	public void stop(){
		time = System.currentTimeMillis() - time;
	}
	
	///Return the now time in string
	public String printNowTime(){
		return timeToString(System.currentTimeMillis() - time);
	}
	
	///Return the time in String
	public String toString(){
		return timeToString(time);
	}
	
	///Return the time in string
	private String timeToString(long time){
		long timeNow = time / 1000;
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
		String msg = this.printNowTime();
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString(msg, boardWidth - msg.length() * 15, 30);
    }

	///Get the time in long 
	public long getTime() {
		return time;
	}

	///Set the time
	public void setTime(long time) {
		this.time = time;
	}
	
	///Check if it bigger between stop times
	public boolean isBigger(Time other){
		return (time > other.getTime());
	}
}
