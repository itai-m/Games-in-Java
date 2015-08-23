package gameEngine;

import org.lwjgl.input.Keyboard;

public class Game {

	public Game(){
		
	}
	
	public void input(){
		if (Input.GetKeyDown(Keyboard.KEY_UP))
			System.out.println("key down");
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
}
