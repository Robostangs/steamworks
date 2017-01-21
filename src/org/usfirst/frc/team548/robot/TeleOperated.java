package org.usfirst.frc.team548.robot;

public class TeleOperated {

private static TeleOperated instance;
	
	public static TeleOperated getInstance() {
		if(instance == null) instance = new TeleOperated();
		return instance;
	}
	
	public static XBoxController driver;
	
	private TeleOperated(){
		driver = new XBoxController(Constants.XB_POS_DRIVER);
	}
	
	public static void run(){
		
	}
}
