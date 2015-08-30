
public class Astroid {
	
	private int size;
	
	///Default constructor
	public Astroid(){
		size = 5;
	}
	
	///Constructor
	public Astroid(int size){
		this.size = size;
	}

	///Split the asteroid
	public Astroid split(){
		return new Astroid(--size);
	}
	
	///Check if destroyed
	public boolean isDestroyed(){
		if (size==0)
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
	
	
}
