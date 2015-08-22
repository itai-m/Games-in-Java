
public class Block {
	public final static int EMPTY = 0;
	public final static int NORMAL = 1;
	private int type;
	
	///Default constructor
	public Block(){
		type = EMPTY;
	}
	
	///Constructor
	public Block(int _type){
		type = _type;
	}

	///Get type
	public int getType(){
		return type;
	}
	
}
