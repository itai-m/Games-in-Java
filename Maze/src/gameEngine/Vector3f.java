package gameEngine;

public class Vector3f {

	private float x;
	private float y;
	private float z;
	
	///Constructor
	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	///Return the length of the vector
	public float lenght(){
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	///Return the number of dot action result
	public float dot(Vector3f v){
		return (x * v.getX() + y * v.getY() + z * v.getZ());
	}
	
	///Return the vector after cross action result with another vector
	public Vector3f cross(Vector3f v){
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();
		return new Vector3f(x_, y_, z_);
	}
	
	///Normalize and return the vector
	public Vector3f normalize(){
		float lenght = lenght();
		x /= lenght;
		y /= lenght;
		z /= lenght;
		return this;
	}
	
	///Return the result of adding this vector with another vector
	public Vector3f add(Vector3f v){
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	///Return the result adding of this vector with number
	public Vector3f add(float num){
		return new Vector3f(x + num, y + num, z + num);
	}

	///Return the result of subtracting this vector with another vector
	public Vector3f sub(Vector3f v){
		return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}
	
	///Return the result of subtracting this vector with number
	public Vector3f sub(float num){
		return new Vector3f(x - num, y - num, z - num);
	}
	
	///Return the result of multiply this vector with another vector
	public Vector3f mul(Vector3f v){
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	
	///Return the result of multiply this vector with number
	public Vector3f mul(float num){
		return new Vector3f(x * num, y * num, z * num);
	}
	
	///Return the result of dividing this vector with number
	public Vector3f div(Vector3f v){
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}
	
	///Return the result of dividing this vector with number
	public Vector3f div(float num){
		return new Vector3f(x / num, y / num, z / num);
	}
	
	///Return the string with the number of the vector
	public String toString(){
		return ("(" + x + "," + y + "," + z + ")");
	}

	///Get the x
	public float getX() {
		return x;
	}

	///Set the x
	public void setX(float x) {
		this.x = x;
	}

	///Get the y
	public float getY() {
		return y;
	}

	///Set the y
	public void setY(float y) {
		this.y = y;
	}

	///Get the z
	public float getZ() {
		return z;
	}

	///Set the z
	public void setZ(float z) {
		this.z = z;
	}
}
