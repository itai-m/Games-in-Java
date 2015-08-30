import java.util.Random;

public class Astroid extends Sprite{
	
	private int size;
	
	///Default constructor
	public Astroid(){
		super();
		size = 3;
	}
	
	///Constructor
	public Astroid(int size){
		super();
		this.size = size;
	}

	///Split the asteroid
	public Astroid split(){
		this.rotate((float) getRand(0,90));
		return new Astroid(--size);
	}
	
	///Check if destroyed
	public boolean isDestroyed(){
		if (size == 0)
			return true;
		return false;
	}
	
	///Get the size
	public int getSize() {
		return size;
	}

	///Set the size
	public void setSize(int size) {
		this.size = size;
	}
	
	///get a random number
	private double getRand(double min, int max){
		Random rand = new Random();
		return rand.nextInt(max) + min;
	}
}
