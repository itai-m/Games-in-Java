
public class Ball {
	private double x;
	private double y;
	private double size;
	private double v;
	private double g;
	private boolean up;
	private int time;
	
	public Ball(){
		x = 0;
		y = 0;
		size = 10;
		up = false;
		time = 0;
	}
	
	public Ball(double _x, double _y, double _size, double vel, double gra){
		x = _x;
		y = _y;
		size = _size;
		v = vel;
		g = gra;
		up = false;
		time = 0;
	}
	
	public void step(){
		time++;
		double speed = 0;
		if (y - size < 0){
			up = true;
		}
		if (up){
			y = y + (v + (g)/2);
		}
		else{
			y = y - (v + (g)/2);
		}
		
	}
	
	public void changeInv(double vel, double gra){
		v = vel;
		g = gra;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getSize(){
		return size;
	}
}
