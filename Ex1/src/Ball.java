
public class Ball {
	private double x;
	private double y;
	private double size;
	private double v;
	private double g;
	private boolean up;
	private double cor;
	
	///Default constructor
	public Ball(){
		x = 0;
		y = 0;
		size = 10;
		up = false;
		v = 0;
		g = 0;
		cor = 0;
	}
	
	///Constructor
	public Ball(double _x, double _y, double _size, double vel, double gra, double _cor){
		x = _x;
		y = _y;
		size = _size;
		v = vel;
		g = gra;
		up = false;
		cor = _cor;
	}
	
	///do one step of the ball
	public void step(){
		if (up){
			v = v - g/2;
			y = y + v;
		}
		else{
			v = v + g/2;
			y = y - v;
		}
		if (y - size <= 0){
			up = true;
			y = size;
			v = v*cor;
		}
		if (v < 0){
			up = false;
		}
		
	}
	
	//change the environment 
	public void changeEnv(double _cor, double gra){
		cor = _cor;
		g = gra;
	}
	
	///get x
	public double getX(){
		return x;
	}
	
	///get y
	public double getY(){
		return y;
	}
	
	///set y and the ball to fall down
	public void restart(double hight){ 
		up = false;
		y = hight;
		v = 0;
	}
	
	public double getSize(){
		return size;
	}
}
