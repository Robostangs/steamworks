package org.usfirst.frc.team548.robot;

public class TeleOperated {

private static TeleOperated instance;
	
	public static TeleOperated getInstance() {
		if(instance == null) instance = new TeleOperated();
		return instance;
	}
	
	private TeleOperated(){
		
	}
	
	public static void run(){
		
	}
}
