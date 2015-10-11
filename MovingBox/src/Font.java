import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Font {
	
	private final static String WIN_PATH =  System.getProperty("user.dir") + "\\image\\";
	private final static String LINUX_PATH =  System.getProperty("user.dir") + "/image/";
	private final static int FONT_WIDTH = 32;
	private final static int FONT_HEIGHT = 48;
	private final static int HASKII_CAP_DIFF = 32;
	private final static int HASKII_NUM_0 = 48;
	
	private static Image img;
	
	///Constructor
	public static void init(){
		String path = "";
		if (System.getProperty("os.name").equals("Linux")){
			path = LINUX_PATH;
		}
		else{
			path = WIN_PATH;
		}
		img  = Toolkit.getDefaultToolkit().getImage(path + "font.png");
	}
	
	///Draw a letter with picture
	public static void drawLetter(char letter, int x, int y, int width, int height, Graphics g){
		if (('A' <= letter) && ('Z' >= letter)){
			letter += HASKII_CAP_DIFF;
		}
		switch (letter){
		case 'a':
			drawPartOfPic(0,0,x,y,width,height,g);
			break;
		case 'b':
			drawPartOfPic(0,1,x,y,width,height,g);
			break;
		case 'c':
			drawPartOfPic(0,2,x,y,width,height,g);
			break;
		case 'd':
			drawPartOfPic(0,3,x,y,width,height,g);
			break;
		case 'e':
			drawPartOfPic(0,4,x,y,width,height,g);
			break;
		case 'f':
			drawPartOfPic(0,5,x,y,width,height,g);
			break;
		case 'g':
			drawPartOfPic(0,6,x,y,width,height,g);
			break;
		case 'h':
			drawPartOfPic(0,7,x,y,width,height,g);
			break;
		case 'i':
			drawPartOfPic(0,8,x,y,width,height,g);
			break;
		case 'j':
			drawPartOfPic(0,9,x,y,width,height,g);
			break;
		case 'k':
			drawPartOfPic(0,0,x,y,width,height,g);
			break;
		case 'l':
			drawPartOfPic(0,0,x,y,width,height,g);
			break;
		case 'm':
			drawPartOfPic(0,0,x,y,width,height,g);
			break;
		}
	}
	
	private static void drawPartOfPic(int col, int row, int x, int y, int width, int height, Graphics g){
		g.drawImage(img, x, y, x + width, y + height, FONT_WIDTH * row, FONT_HEIGHT * col, FONT_WIDTH * (row + 1), FONT_HEIGHT * (col + 1), null);
	}
}
