
public class Player extends Sprite{
	
	private int x;
	private int y;
	private int height;
	private int width;
	
	///Default constructor
	public Player (){
		x = 0;
		y = 0;
		height = 10;
		width = 10;
	}
	
	///Constructor
	public Player(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	///Get the x
	public int getX() {
		return x;
	}

	///Set the x
	public void setX(int x) {
		this.x = x;
	}

	///Get the y
	public int getY() {
		return y;
	}

	///Set the y
	public void setY(int y) {
		this.y = y;
	}

	///Get the height
	public int getHeight() {
		return height;
	}

	///Set the height
	public void setHeight(int height) {
		this.height = height;
	}

	///Get the width
	public int getWidth() {
		return width;
	}

	///Set the width
	public void setWidth(int width) {
		this.width = width;
	}
}
