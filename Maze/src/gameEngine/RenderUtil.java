package gameEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
public class RenderUtil {
	
	///Initialization the openGL
	public static void initGraphics(){
		///clear the screen to black//
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		///set that the openGL draw just the front face//
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		
		///Make the openGL to save the z scale when drawing//
		glEnable(GL_DEPTH_TEST);
		
		///Do a auto correction to the colorspace//
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	///Clear all the screen
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

}
