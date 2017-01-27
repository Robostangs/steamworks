package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
		DriveTrain.shiftHigh(driver.getRightBumper());
		SmartDashboard.putNumber("Hyro", DriveTrain.getAngle());
		SmartDashboard.putNumber("Pressure", DriveTrain.getPressure());
		SmartDashboard.putNumber("Right pos", DriveTrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left pos", DriveTrain.getLeftEncoderDistance());
		SmartDashboard.putNumber("Right speed", DriveTrain.getRightSpeed());
		SmartDashboard.putNumber("Left speed", DriveTrain.getLeftSpeed());
		Climber.setPower(driver.getTriggers());
	}
}
