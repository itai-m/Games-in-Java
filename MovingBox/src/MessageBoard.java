import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class MessageBoard {

	private String msg;
	
	///Default constructor
	public MessageBoard(){
		msg = "";
	}
	
	///Constructor
	public MessageBoard(String msg){
		this.msg = msg;
	}

	///Draw the message board
	public void draw(Graphics2D g, ImageObserver ob, int width, int height){
		 g.setColor(Color.DARK_GRAY); 
	     g.fillRect(0, 0, width, height);
	     Font.drawString(msg, width/10, height/3, width * (9 / 10), height * ( 2 / 3), g);
	}
	
	///Get the message
	public String getMsg() {
		return msg;
	}

	///Set the message
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
