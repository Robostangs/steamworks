package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {

private static TeleOperated instance;
private static Timer timer;
private static int wiggle = 0;
private static boolean yPressed = false;
	
	
	public static TeleOperated getInstance() {
		if(instance == null) instance = new TeleOperated();
		return instance;
	}
	
	public static XBoxController driver;
	public static XBoxController manip;
	private static boolean povPressed = false, timerStart = false;
	
	private static double shooterAdjustment = 0;
	
	private TeleOperated(){
		driver = new XBoxController(Constants.XB_POS_DRIVER);
		manip = new XBoxController(Constants.XB_POS_MANIP);
		timer = new Timer();
	}
	
	public static void run(){
		/**
		 * Driver
		 */
		if(driver.getAButton()){
			if(wiggle < 4){
				DriveTrain.drive(-Constants.DT_WIGGLE_POWER, -Constants.DT_WIGGLE_POWER);
			}
			else if(wiggle >= 4){
				DriveTrain.drive(Constants.DT_WIGGLE_POWER, Constants.DT_WIGGLE_POWER);
			}	
			wiggle ++;
			if (wiggle > 8) wiggle = 0;
		} else {
			DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
		}
		if(manip.getStartButton()) {
			if(!timerStart) {
				timer.start();
				timerStart = true;
			}
			
				TopGear.setOpen(true);
			
			if(timer.get() > .3 && timer.get() < 1) {
				Climber.setClimbOpen(true);
			} else if(timer.get() > 1) {
				Climber.setClimbOpen(false);
			}
			
			//DriveTrain.drive(.3, .3);
		} 
		else{
			
			timerStart = false;
			timer.reset();
		}
		
		
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
		if(!manip.getStartButton()) {
			TopGear.setOpen(manip.getBButton());
		}
		
		if(manip.getRightTriggerButton()) {
			Shooter.injectAfterSpeed(2900);
		} else {
			if(manip.getRightBumper()) { 
				Shooter.setElevator(-.8);
				//Shooter.setShooterPower(-0.5);	
			} else {
				Shooter.setElevator(0);
			}
			Shooter.setShooterPower(0);
		}
		
		if(manip.getPOV() == 0 && !povPressed) {
			shooterAdjustment += .0005;
			Shooter.addF(shooterAdjustment);
			povPressed = true;
		} else if (manip.getPOV() == 180 && !povPressed) {
			shooterAdjustment -= .0005;
			Shooter.addF(shooterAdjustment);
			povPressed = true;
		} else if(manip.getPOV() == -1) {
			povPressed = false;
		}
		
		if(manip.getYButton() && !yPressed) {
			Climber.setClimbOpen(!Climber.isOpen());
			yPressed = true;
		} else if(yPressed && !manip.getYButton()) {
			yPressed = false;
		}
		
	
		
		Ingestor.setRollerBarDown(manip.getBackButton());
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
