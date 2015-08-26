package gameEngine;

public class Vertex {

	public static final int SIZE = 3;
	
	public Vector3f pos;  ///save the position of the vertex
	
	///Constructor
	public Vertex(Vector3f pos){
		this.pos = pos;
	}

	///Get the position
	public Vector3f getPos() {
		return pos;
	}

	///Set the position
	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	
}
