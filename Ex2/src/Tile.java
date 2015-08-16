
public class Tile {
	private int number;
	
	public Tile(){
		number = 0;
	}
	
	public Tile(int num){
		number = num;
	}
	
	public boolean equal(Tile check){
		if (check.getNumber()==number)
			return true;
		return false;
	}
	
	public int getNumber(){
		return number;
	}
}
