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
		
		shader.addUniform("uniformFloat");
		/////////////////////
	}
	
	///do the input of the game
	public void input(){
		Input.Update();
	}
	
	float temp = 0.0f;
	
	///update the game
	public void update(){
		
		/////testing area////
		temp += Time.getDelta();
		shader.setUniform("uniformFloat", (float)Math.sin(temp));
		/////////////////////
	}
	
	///render all the game
	public void render(){
		shader.bind();
		mesh.draw();
	}
}
