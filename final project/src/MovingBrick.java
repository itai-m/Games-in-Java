
public class MovingBrick extends Sprite{
	
	private int kind;
	
	///Default constructor
	public MovingBrick(){
		super();
		kind = 0;
	}
	
	///Constructors
	public MovingBrick(float x, float y, int kind, int boardWidth, int boardHeight){
		super(x, y, 1, 0, 1, boardWidth, boardHeight);
		this.kind = kind;
	}
}

