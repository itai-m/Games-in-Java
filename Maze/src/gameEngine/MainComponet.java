package gameEngine;


public class MainComponet {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Maze";
	public static final double FRAME_CAP = 5000.0;
	
	private boolean isRunning;
	private Game game;
	
	///Constructor
	public MainComponet(){
		System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
		isRunning = false;
		game = new Game();
	}
	
	///make the game run if is not running yet
	public void start(){
		if (isRunning)
			return;
		run();
	}
	
	///stop the game from running
	public void stop(){
		if (!isRunning)
			return;
		isRunning = false;
	}
	
	///do the running of the game per frame
	private void run(){
		
		final double frameTime = 1.0 / FRAME_CAP;
		long lastTime = Time.getTime();
		double unprocessdTime = 0;
		boolean  render ;
		int frames = 0;
		long frameCounter = 0;
		
		isRunning = true;
		
		while (isRunning){
			render = false;
			
			long startTime = Time.getTime();
			long passTime  = startTime - lastTime;
			lastTime = startTime;
			
			unprocessdTime += passTime / (double)Time.SECOND;
			frameCounter += passTime;
			
			
			while  (unprocessdTime > frameTime){
				render = true;
				//System.out.println(unprocessdTime);
				unprocessdTime -= frameTime;
				if (Window.isCloseRequested())
					stop();
				
				Time.setDelta(frameCounter);
				
				game.input();
				game.update();
				
				///FPS counter
				if (frameCounter >= Time.SECOND){
					System.out.println("FPS:" + frames);
					frameCounter = 0;
					frames = 0;
				}
				
				if(render){
					render();
					frames++;
				}
				else{///wait if there is no need to render
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
		}
		cleanUp();
	}
	
	///render all the Components
	private void render(){
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}
	
	//clean up all the Components
	private void cleanUp(){
		Window.dispose();
	}
	
	public static void main(String[] args)
    {
        Window.createWindow(WIDTH, HEIGHT, TITLE);
        
        MainComponet game = new MainComponet();
        game.start();
        
    }
}
