
public class ScoreBoard {

	private String[] names;
	private Time[] times;
	
	
	///Default constructor
	public ScoreBoard(){
		names = new String[10];
		times = new Time[10];
	}
	
	///Constructor
	public ScoreBoard(int size) {
		names = new String[size];
		times = new Time[size];
		for (int i = 0; i < times.length ; i++){
			times[i] = new Time(0);
		}
	}
	
	///Add a new name to the to the score board, return true if he add to the list otherwise false
	public boolean add(String name, Time time){
		if (!time.isBigger(times[times.length - 1])){
			return false;
		}
		else{
			for (int i = 0 ; i < times.length ; i++ ){
				if (time.isBigger(times[i])){
					insertOne(name, time, i);
					return true;
				}
			}
			return true;
		}
	}
	
	///Insert to the score board
	private void insertOne(String name, Time time, int index){
		if (index < times.length - 1){
			insertOne(names[index + 1], times[index + 1], index + 1);
			names[index] = name;
			times[index] = new Time(time);
		}
		
	}
	
	///Set all the ScoreBoard
	public void set(String[] names, Time[] times){
		this.names = names;
		this.times = times;
	}
	
	///Return the string with the score board
	public String toString(){
		String toRetrun = "";
		toRetrun += "-------------------\n";
		toRetrun += "|  Name  |  Time  |\n";
		toRetrun += "-------------------\n";
		for (int i = 0; i < names.length ; i++){
			if (times[i].getTime() == 0){
				toRetrun +=  "|Empty   |        |\n" ; 
			}
			else{
				if (names[i].length() > 8){
					toRetrun += "|" + names[i].substring(0, 8) + "|" + times[i] + "|\n";
				}
				else{
					toRetrun += "|" + names[i] + "|" + times[i] + "|\n";
				}
			}
		}
		toRetrun += "-------------------\n";
		return toRetrun;
	}
}
