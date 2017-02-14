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
		manip = new XBoxController(Constants.XB_POS_MANIP);
	}
	
	public static void run(){
		/**
		 * Driver
		 */
		if(driver.getAButton()) DriveTrain.driveStraight(0.5);
		else DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
		DriveTrain.shiftHigh(driver.getRightBumper());
		
		/**
		 * Manip
		 */
		Climber.setPower((manip.getXButton())? -1 : (manip.getAButton())? -.5 : 0);
		if(!manip.getLeftBumper()) {
			Ingestor.setElevatorPower(manip.getLeftTriggerAxis());
			Ingestor.setRollerBarPower(manip.getLeftTriggerAxis()*.8); 
		} else {
			Ingestor.setElevatorPower(-.8);
			Ingestor.setRollerBarPower(-.8); 
		}
		TopGear.setOpen(manip.getBButton());
		if(manip.getRightTriggerButton()) {
			Shooter.injectAfterSpeed(3000);
		} else {
			if(manip.getRightBumper()) { 
				Shooter.setElevator(-.8);
				Shooter.setShooterPower(-0.5);
			} else {
				Shooter.setShooterPower(0);
				Shooter.setElevator(0);
			}
		}
		/**
		 * Testing MAKE SURE TO REMOVE BEFORE COMP
		 */
		if(driver.getBButton()) DriveTrain.restHyro();
		else if(driver.getYButton()) DriveTrain.calibrateHyro();
		else if(driver.getXButton()) DriveTrain.restEncoders();
		//SmartDashboard stuff
		SmartDashboard.putNumber("Hyro", DriveTrain.getAngle());
		SmartDashboard.putNumber("Pressure", DriveTrain.getPressure());
		SmartDashboard.putNumber("Right pos", DriveTrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left pos", DriveTrain.getLeftEncoderDistance());  
		SmartDashboard.putNumber("Right speed", DriveTrain.getRightSpeed());
		SmartDashboard.putNumber("Left speed", DriveTrain.getLeftSpeed());
		SmartDashboard.putBoolean("High gear", DriveTrain.isHigh());
		SmartDashboard.putNumber("Speed", Shooter.getSpeed());
	}
	
	public static void init() {
		DriveTrain.breakMode(false);
	}
}
