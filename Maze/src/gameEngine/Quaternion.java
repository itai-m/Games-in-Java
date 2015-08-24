package gameEngine;

public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;
	
	public Quaternion (float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public float lenght(){
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Quaternion normalize(){
		float lenght = lenght();
		x /= lenght;
		y /= lenght;
		z /= lenght;
		w /= lenght;
		return this;
	}

	public Quaternion conjugate(){
		return new Quaternion(-x, -y, -z, -w);
	}
	
	public Quaternion mul(Quaternion q){
		float w_ = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();
		float x_ = x * q.getW() + w * q.getX() + y * q.getZ() - z * q.getY();
		float y_ = y * q.getW() + w * q.getY() + z * q.getX() - x * q.getZ();
		float z_ = z * q.getW() + w * q.getZ() + x * q.getY() - y * q.getX();
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion mul(Vector3f v){
		float w_ = -x * v.getX() - y * v.getY() - z * v.getZ();
		float x_ =  w * v.getX() - y * v.getZ() - z * v.getY();
		float y_ =  w * v.getY() + z * v.getX() - x * v.getZ();
		float z_ =  w * v.getZ() + x * v.getY() - y * v.getX();
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
}
