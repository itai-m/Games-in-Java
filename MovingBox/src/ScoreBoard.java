import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreBoard {

	private String[] names;
	private Time[] times;
	
	
	///Default constructor
	public ScoreBoard(){
		names = new String[10];
		times = new Time[10];
	}
	
	///Constructor
	public ScoreBoard(int size) {
		names = new String[size];
		times = new Time[size];
		for (int i = 0; i < times.length ; i++){
			times[i] = new Time(0);
		}
	}
	
	///Add a new name to the to the score board, return true if he add to the list otherwise false
	public boolean add(String name, Time time){
		if ((time.isBigger(times[times.length - 1]) && (!(times[times.length - 1].getTime() == 0)))){
			return false;
		}
		else{
			for (int i = 0 ; i < times.length ; i++ ){
				if ((!time.isBigger(times[i])) || (times[i].getTime() == 0)){
					insertOne(name, time, i);
					return true;
				}
			}
			return true;
		}
	}
	
	///Insert to the score board
	private void insertOne(String name, Time time, int index){
		if (index < times.length - 1){
			insertOne(names[index], times[index], index + 1);
			names[index] = name;
			times[index] = new Time(time);
		}
		
	}
	
	///Set all the ScoreBoard
	public void set(String[] names, Time[] times){
		this.names = names;
		this.times = times;
	}
	
	///Return the string with the score board
	public String toString(){
		String toRetrun = "";
		toRetrun += "-------------------\n";
		toRetrun += "|  Name  |  Time  |\n";
		toRetrun += "-------------------\n";
		for (int i = 0; i < names.length ; i++){
			if (times[i].getTime() == 0){
				toRetrun +=  "|Empty   |        |\n" ; 
			}
			else{
				if (names[i].length() > 8){
					toRetrun += "|" + names[i].substring(0, 8) + "|" + times[i] + "|\n";
				}
				else{
					toRetrun += "|" + padRight(names[i],8) + "|" + times[i] + "|\n";
				}
			}
		}
		toRetrun += "-------------------\n";
		return toRetrun;
	}
	
	///Draw the score board
	public void draw(Graphics g, int boardWidth, int boardHeight){
		String msg = this.toString();
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString(msg, boardWidth/2 - msg.length() , 100);
	}
	
	///Pad a string with n spaces to the right
	private static String padRight(String s, int n) {
	    return String.format("%1$-" + n + "s", s);
	}
}
