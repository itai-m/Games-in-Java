
public class Ball {
	private double x;
	private double y;
	private double size;
	private double v;
	private double g;
	private boolean up;
	private double cor;
	
	public Ball(){
		x = 0;
		y = 0;
		size = 10;
		up = false;
		v = 0;
		g = 0;
		cor = 0;
	}
	
	public Ball(double _x, double _y, double _size, double vel, double gra, double _cor){
		x = _x;
		y = _y;
		size = _size;
		v = vel;
		g = gra;
		up = false;
		cor = _cor;
	}
	
	public void step(){
		if (up){
			v = v - g/2;
			y = y + v;
		}
		else{
			v = v + g/2;
			y = y - v;
		}
		if (y - size < 0){
			up = true;
			y = size;
		}
		if (v < 0){
			up = false;
		}
		
	}
	
	public void changeInv(double _cor, double gra){
		cor = _cor;
		g = gra;
	}
	
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setY(int _y){ 
		up = false;
		y = _y;
	}
	
	public double getSize(){
		return size;
	}
}
