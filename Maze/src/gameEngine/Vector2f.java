package gameEngine;

public class Vector2f {
	private float x;
	private float y;
	
	///Constructor
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}

	///Rotate the vector
	public Vector2f rotate(float angle){
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		return new Vector2f((float)(x * cos - y * sin),(float)( x * sin + y * cos));
	}
	
	///Return the length of the vector
	public float lenght(){
		return (float)Math.sqrt( x * x + y * y);
	}
	
	///Return the number of dot action result
	public float dot(Vector2f v){
		return (x * v.getX() + y * v.getY());
	}
	
	///Normalize and return the vector
	public Vector2f normalize(){
		float lenght = lenght();
		x /= lenght;
		y /= lenght;
		return this;
	}
	
	///Return the result of adding this vector with another vector
	public Vector2f add(Vector2f v){
		return new Vector2f(x + v.getX(), y + v.getY());
	}
	
	///Return the result of adding this vector with number
	public Vector2f add(float num){
		return new Vector2f(x + num, y + num);
	}

	///Return the result of subtracting this vector with another vector
	public Vector2f sub(Vector2f v){
		return new Vector2f(x - v.getX(), y - v.getY());
	}
	
	///Return the result of subtracting this vector with number
	public Vector2f sub(float num){
		return new Vector2f(x - num, y - num);
	}
	
	///Return the result of multiply this vector with another vector
	public Vector2f mul(Vector2f v){
		return new Vector2f(x * v.getX(), y * v.getY());
	}
	
	///Return the result of multiply this vector with number
	public Vector2f mul(float num){
		return new Vector2f(x * num, y * num);
	}
	
	///Return the result of dividing this vector with number
	public Vector2f div(Vector2f v){
		return new Vector2f(x / v.getX(), y / v.getY());
	}
	
	///Return the result of dividing this vector with number
	public Vector2f div(float num){
		return new Vector2f(x / num, y / num);
	}
	
	///Return the string with the number of the vector
	public String toString(){
		return ("(" + x + "," + y + ")");
	}
	
	///Get the x
	public float getX() {
		return x;
	}

	///set the x
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
	
	
	
}
