package gameEngine;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashMap;


public class Shader {

	private int program;
	private HashMap<String, Integer> uniforms;
	
	///Constructor
	public Shader(){
		program =  glCreateProgram();
		
		if (program == 0){
			System.err.println("Shader creation failed: in the Constructor");
			System.exit(-1);
		}
		
		uniforms = new HashMap<String, Integer>();
	}
	
	///Bind the shader
	public void bind(){
		glUseProgram(program);
	}
	
	///Add vertex to the shader
	public void addVertexShader(String text){
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	///Add a geometry to the shader
	public void addGeometryShader(String text){
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	///Add a fragment to the shader
	public void addFragmentShader(String text){
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	///Add a program to the shader
	private void addProgram(String text, int type){
		
		int shader = glCreateShader(type);
		
		if (shader == 0){
			System.err.println("Shader creation failed: in the adding a shader");
			System.exit(-1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		
		if (glGetShader(shader, GL_COMPILE_STATUS) == 0){
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(-1);
		}
		
		glAttachShader(program, shader);
	}
	
	///Compile and check the completion
	public void compileShader(){
		glLinkProgram(program);
		if (glGetProgram(program, GL_LINK_STATUS) == 0){
			System.err.println(glGetProgramInfoLog(program, 1024));
			System.exit(-1);
		}
		
		glValidateProgram(program);
		
		if (glGetProgram(program, GL_VALIDATE_STATUS) == 0){
			System.err.println(glGetProgramInfoLog(program, 1024));
			System.exit(-1);
		}
		
	}
	
	///Add uniform to the shader
	public void addUniform(String uniform){
		int uniformLocation  = glGetUniformLocation(program, uniform);
		
		if (uniformLocation == -1){
			System.err.println("Could not find uniform:" + uniform);
			new Exception().printStackTrace();
			System.exit(-1);
		}
		
		uniforms.put(uniform, uniformLocation);
	}
	
	///Set the uniform form an integer
	public void setUniform(String uniformName, int value){
		glUniform1i(uniforms.get(uniformName), value);
	}
	
	///Set the uniform form an integer
	public void setUniform(String uniformName, float value){
		glUniform1f(uniforms.get(uniformName), value);
	}
	
	///Set the uniform form an integer
	public void setUniform(String uniformName, Vector3f value){
		glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());;
	}
	
	///Set the uniform form an integer
	public void setUniform(String uniformName, Matrix4f value){
		glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
	}
}
