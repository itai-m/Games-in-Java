package gameEngine;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

	private int program;
	
	///Constructor
	public Shader(){
		program =  glCreateProgram();
		
		if (program == 0){
			System.err.println("Shader creation failed");
			System.exit(-1);
		}
	}
	
	///Add vertex to the shader
	public void addVertexShader(String text){
		
	}
	
	///Add a geometry to the shader
	public void addGeometryShader(String text){
		
	}
	
	///Add a fragment to the shader
	public void addFragmentShader(String text){
		
	}
	
	///Add a program to the shader
	private void addProgram(String text, int type){
		
	}
	
	
}
