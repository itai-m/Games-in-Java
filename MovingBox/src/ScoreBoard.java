import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreBoard {

	private final int MAX_NAME_SIZE = 8;
	private final int TIME_STRING_SIZE = 8;
	private final int LINE_SIZE = MAX_NAME_SIZE + TIME_STRING_SIZE + 4;
	private final int FONT_SIZE = 30;
	
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
	
	///Draw the score board (old function)
	public void drawOld(Graphics g, int boardWidth, int boardHeight){
		String msg = this.toString();
		int drawHeight = boardHeight / 2 - (times.length / 2 + 1) * FONT_SIZE;
		
        g.setColor(Color.CYAN); 
        g.fillRoundRect(boardWidth/2 - (LINE_SIZE * 9), drawHeight - FONT_SIZE, LINE_SIZE * 18, (times.length + 4) * FONT_SIZE, 30, 30);
        
        g.setFont(new Font("Consolas", Font.PLAIN, FONT_SIZE));
        g.setColor(Color.BLACK);
        for (int i = 0; i < times.length + 4; i++){
        	String tempMsg = msg.substring(i * LINE_SIZE, (i + 1) * LINE_SIZE  - LINE_SIZE / 2 - 1);
        	String tempMsg2 = msg.substring((i + 1) * LINE_SIZE  - LINE_SIZE / 2, (i + 1) * LINE_SIZE);
        	g.drawString(tempMsg, boardWidth/2 - tempMsg.length() * 8 , drawHeight + i * FONT_SIZE);
        	g.drawString(tempMsg2, boardWidth/2 , drawHeight + i * FONT_SIZE);
        }
	}
	
	///Draw the score board
	public void draw(Graphics g, int boardWidth, int boardHeight){
		String msg = this.toString();
		int xStart = boardWidth / 4;
		int yStart = boardHeight / 4;
		int width = boardWidth / 2;
		int height = boardHeight / 20;
		//drawString("  Name | Time  ", boardWidth / 4, boardHeight / 4, boardWidth / 2, boardHeight / 2, g);
		for (int i = 0 ; i < times.length ; i++){
			String tempMsg = msg.substring(i * LINE_SIZE, (i + 1) * LINE_SIZE );
			drawString(tempMsg, xStart, yStart + height * i, width, height, g);
		}
	}
	
	///Pad a string with n spaces to the right
	private static String padRight(String s, int n) {
	    return String.format("%1$-" + n + "s", s);
	}
	
	///Draw a string with pictures
	private void drawString(String str, int x, int y, int width, int height, Graphics g){
		int strSize = str.length();
		if (strSize == 0){
			return;
		}
		int letterSize = width / strSize;
		for (int i = 0; i < strSize ; i ++){
			drawLetter(str.charAt(i), x + i * letterSize, y, letterSize, height, g);
		}
	}
	
	///Draw a letter with picture
	private void drawLetter(char letter, int x, int y, int width, int height, Graphics g){
		///For Test///
		g.setColor(new Color((int)letter,(int)letter,(int)letter));
		g.fillRect(x, y, width, height);
		//////////////
		//TODO: need to find font
	}
}
