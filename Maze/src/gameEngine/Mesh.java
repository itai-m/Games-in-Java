package gameEngine;

import static org.lwjgl.opengl.GL15.*;
public class Mesh {

	private int vbo;
	private int size;
	
	///Constructor
	public Mesh(){
		vbo = glGenBuffers();
		size = 0;
	}
	
	public void addVertices(Vertex[] vertices){
		
		size = vertices.length * Vertex.SIZE;
	}
}
