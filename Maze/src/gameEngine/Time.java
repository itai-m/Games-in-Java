package gameEngine;

public class Time {

	public static final long SECOND = 1000000000L;  ///Save one second
	private static double delta;  ///Save the delta between times
	
	///Return the current time
	public static long getTime(){
		return System.nanoTime();
	}
	
	///Get the delta
	public static double getDelta(){
		return delta;
	}
	
	///Set the delta
	public static void setDelta(double _delta){
		delta = _delta;
	}
}
