package gameEngine;

import java.io.BufferedReader;
import java.io.FileReader;

public class ResourceLoader {

	///Read a shader from file
	public static String loadShader (String fileName){
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shadeReader = null;
		
		try {
			shadeReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
			String newLine;
			
			while ((newLine = shadeReader.readLine()) != null){
				shaderSource.append(newLine).append("\n");
			}
			
			shadeReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return shaderSource.toString();
	}
}
