
public class Tile {
	
	private final int fullNumber = 11;
	
	private int number; ///save the power of the real number
	
	///Default constructor
	public Tile(){
		number = 0;
	}
	
	///Constructor
	public Tile(int num){
		number = num;
	}
	
	///Check if it is equal
	public boolean equal(Tile check){
		if (check.getNumber()==number)
			return true;
		return false;
	}
	
	///Return the number
	public int getNumber(){
		return number;
	}
	
	///Reset the number
	public void reset(){
		number = 0;
	}
	
	///Add one to the number
	public void upgrade(){
		number++;
	}
	
	///Check if the tile is empty
	public boolean isEmpty(){
		if (number == 0)
			return true;
		return false;
	}
	
	///Set a number in to the tile
	private void setNumber(int newNumber){
		number = newNumber;
	}
	
	///Move one tile to the other
	public void moveTo(Tile moveThere){
		moveThere.setNumber(number);
		this.reset();
	}
	
	///Set the tile to 1
	public void setTo1(){
		number = 1;
	}
	
	///Set the tile to 2
	public void setTo2(){
		number = 2;
	}
	
	///Check if the tile is full (win)
	public boolean checkWin(){
		if (number == fullNumber)
			return true;
		return false;
	}
}
