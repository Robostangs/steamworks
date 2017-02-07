package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {

private static TeleOperated instance;
	
	public static TeleOperated getInstance() {
		if(instance == null) instance = new TeleOperated();
		return instance;
	}
	
	public static XBoxController driver;
	public static XBoxController manip;
	
	private TeleOperated(){
		driver = new XBoxController(Constants.XB_POS_DRIVER);
		//manip = new XBoxController(Constants.XB_POS_MANIP);
	}
	
	public static void run(){
		//DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
		DriveTrain.arcadeDrice(driver.getTriggers(), Utils.negPowTwo(driver.getLeftStickXAxis()));
		DriveTrain.shiftHigh(driver.getRightBumper());
		SmartDashboard.putNumber("Hyro", DriveTrain.getAngle());
		SmartDashboard.putNumber("Pressure", DriveTrain.getPressure());
		SmartDashboard.putNumber("Right pos", DriveTrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left pos", DriveTrain.getLeftEncoderDistance());
		SmartDashboard.putNumber("Right speed", DriveTrain.getRightSpeed());
		SmartDashboard.putNumber("Left speed", DriveTrain.getLeftSpeed());
		SmartDashboard.putBoolean("High gear", DriveTrain.isHigh());
		Climber.setPower((driver.getPOV() == 0)? 1 : (driver.getPOV() == 180)? -1 : 0);
		Ingestor.setRollerBarPower((driver.getYButton())? .75 : (driver.getAButton())? -.75 : 0);
		Shooter.setShooterPower(driver.getRightStickYAxis());
	}
}
