
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
	
	public void reset(){
		number = 0;
	}
	
	public void upgrade(){
		number++;
	}
	
	public boolean isEmpty(){
		if (number == 0)
			return true;
		return false;
	}
	
	private void setNumber(int newNumber){
		number = newNumber;
	}
	
	public void moveTo(Tile moveThere){
		moveThere.setNumber(number);
		this.reset();
	}
	
	public void setTo1(){
		number = 1;
	}
	
	public void setTo2(){
		number = 2;
	}
}
