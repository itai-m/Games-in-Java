
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class MessageBoard {


	
	private String msg;
	private static Image img;
	
	///Default constructor
	public MessageBoard(){
		msg = "";
		init();
	}
	
	///Constructor
	public MessageBoard(String msg){
		this.msg = msg;
		init();
	}
	
	///Initialization the picture
	private static void init(){
		img  = Toolkit.getDefaultToolkit().getImage(TemplateHandler.getPath() + "winbackground.jpg");
	}

	///Draw the message board
	public void draw(Graphics2D g, ImageObserver ob, int width, int height){
		 g.drawImage(img, 0, 0, width, height, ob);
	     Font.drawString(msg, width/10, height/3, (int)((double)width * (double)(0.9)), (int)((double)height * (double)( 0.2)), g);
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
