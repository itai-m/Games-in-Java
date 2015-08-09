
public class Ball {
	private double x;
	private double y;
	private double size;
	private double v;
	private double g;
	private boolean up;
	
	public Ball(){
		x = 0;
		y = 0;
		size = 10;
		up = false;
	}
	
	public Ball(double _x, double _y, double _size, double vel, double gra){
		x = _x;
		y = _y;
		size = _size;
		v = vel;
		g = gra;
		up = false;
	}
	
	public void step(){
		if (y < 0){
			up = true;
		}
		if (up){
			v = v + g;
		}
		else{
			v = v - g;
		}
		y = v;
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
