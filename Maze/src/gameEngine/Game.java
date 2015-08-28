package gameEngine;


public class Game {

	private Mesh mesh;
	private Shader shader;
	
	///Constructor
	public Game(){
		mesh = new Mesh();
		
		/////testing area////
		shader = new Shader();
		Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
									  new Vertex(new Vector3f(0, 1, 0)),
									  new Vertex(new Vector3f(1, -1, 0))};
		mesh.addVertices(data);
		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();
		/////////////////////
	}
	
	///do the input of the game
	public void input(){
		Input.Update();
	}
	
	///update the game
	public void update(){
		
	}
	
	///render all the game
	public void render(){
		shader.bind();
		mesh.draw();
	}
}
