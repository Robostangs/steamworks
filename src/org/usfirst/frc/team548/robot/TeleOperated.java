package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {

private static TeleOperated instance;
private static int wiggle = 0;
	
	
	public static TeleOperated getInstance() {
		if(instance == null) instance = new TeleOperated();
		return instance;
	}
	
	public static XBoxController driver;
	public static XBoxController manip;
	private static boolean povPressed = false, timerStart = false, gearArmOffsetPress = false;
	
	private static double shooterAdjustment = 0;
	
	private TeleOperated(){
		driver = new XBoxController(Constants.XB_POS_DRIVER);
		manip = new XBoxController(Constants.XB_POS_MANIP);
	}
	private static int gearOffset = 0;
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
			//DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
			DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Math.pow(driver.getLeftStickXAxis(), 3));
		}
		//When hersh ins't here
//		if (!driver.getLeftTriggerButton()) {
//
//			if (driver.getRightTriggerButton()) {
//				GearIngestor.setArmPos(Constants.GEARING_MIN);
//			} else {
//				GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT);
//			}
//			
//			if (GearIngestor.getArmPos() < 300) {
//				GearIngestor.rollerIngestCurrentLimiting();
//				if (GearIngestor.isGearInIngestor()) {
//					//manip.setLeftRumble(1);
//					driver.setLeftRumble(1);
//				}
//			} else if (driver.getLeftBumper()) {
//				GearIngestor.setRollerBarPower(-.7);
//				if (GearIngestor.isGearInIngestor()) {
//					//manip.setLeftRumble(1);
//					driver.setLeftRumble(1);
//				}
//			} else {
//
//				//manip.setLeftRumble(0);
//				driver.setLeftRumble(0);
//				GearIngestor.setRollerBarPower(0);
//			}
//		} else {
//			GearIngestor.setArmPos(0);//Go down
//			GearIngestor.setRollerBarPower(.7d);//Spit out gear
//		}
		
		
		DriveTrain.shiftHigh(driver.getRightBumper());
		
		/**
		 * Manip
		 */
		if(Math.abs(manip.getRightStickYAxis()) > .2) Climber.setPower(-Math.abs(manip.getRightStickYAxis()));
		else Climber.setPower(0);

		
		
		//Gear Ingestor
		if (!manip.getBButton()) {
			//Roller
			if (manip.getRightBumper()) {
				manip.setLeftRumble(0);
				driver.setLeftRumble(0);
				GearIngestor.setRollerBarPower(.7d);
			} else if (GearIngestor.getArmPos() < -1900) {
				GearIngestor.rollerIngestCurrentLimiting();
				if (GearIngestor.isGearInIngestor()) {
					manip.setLeftRumble(1);
					driver.setLeftRumble(1);
				} else {
					manip.setLeftRumble(0);
					driver.setLeftRumble(0);
				}
			} else if (manip.getPOV() == 90) {
				GearIngestor.rollerIngestCurrentLimiting();
				if (GearIngestor.isGearInIngestor()) {
					manip.setLeftRumble(1);
					driver.setLeftRumble(1);
				}
			} else {

				manip.setLeftRumble(0);
				driver.setLeftRumble(0);
				GearIngestor.setRollerBarPower(0);
			}

			// ARM
			if (Math.abs(manip.getLeftStickYAxis()) > .2) {
				GearIngestor.setArmPower(-manip.getLeftStickYAxis()*.25);
			} else if (manip.getYButton()) {
				GearIngestor.setArmPos(Constants.GEARING_MAX);
			} else if (manip.getRightTriggerButton()) {
				
				 GearIngestor.setArmPos(Constants.GEARING_MIN);
			} else if (DriveTrain.isHigh()) {
				
				 GearIngestor.setArmPos(Constants.GEARING_MAX);
			}  else if(manip.getStartButton()){
				if(wiggle < 3){
					GearIngestor.setArmPower(-.5);
				}
				else if(wiggle >= 3){
					GearIngestor.setArmPower(.5);
				}	
				wiggle ++;
				if (wiggle > 6) wiggle = 0;
			} else {
				//GearIngestor.setArmPower(.1);
				GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT+gearOffset);
				// GearIngestor.stopArm();
			}
		} else {
			GearIngestor.setArmPower(-.7);//Go down
			GearIngestor.setRollerBarPower(.7d);//Spit out gear
		}
		
		if(Climber.isOpen()) manip.setRightRumble(1);
		else manip.setRightRumble(0);
	
		
		if(manip.getRightJoystickButton()) {
			Climber.setClimbOpen(true);
		} else if(manip.getPOV() == 270) {
			Climber.setClimbOpen(false);
		}
		
		if(manip.getBackButton()) gearOffset = 0;
		
	
		if(!gearArmOffsetPress && manip.getPOV() == 180) {
			gearOffset-=20;
			gearArmOffsetPress = true;
		} else if(!gearArmOffsetPress && manip.getPOV() == 0) {
			gearOffset+=20;
			gearArmOffsetPress = true;
		} else {
			gearArmOffsetPress = false;
		}
		
		SmartDashboard.putNumber("Arm pos", GearIngestor.getArmPos());
		/*
		 * Testing MAKE SURE TO REMOVE BEFORE COMP
		 */
//		if(driver.getBButton()) DriveTrain.restHyro();
//		else if(driver.getYButton()) DriveTrain.calibrateHyro();
//		else if(driver.getXButton()) DriveTrain.restEncoders();
		
		
		
		
		//SmartDashboard stuff
//		SmartDashboard.putNumber("Hyro", DriveTrain.getAngle());
//		SmartDashboard.putNumber("Pressure", DriveTrain.getPressure());
//		SmartDashboard.putNumber("Right pos", DriveTrain.getRightEncoderDistance());
//		SmartDashboard.putNumber("Left pos", DriveTrain.getLeftEncoderDistance());  
		SmartDashboard.putNumber("Right speed", DriveTrain.getRightSpeed());
		SmartDashboard.putNumber("Left speed", DriveTrain.getLeftSpeed());
//		SmartDashboard.putBoolean("High gear", DriveTrain.isHigh());
//		SmartDashboard.putNumber("Speed", Shooter.getSpeed());
	}
	
	public static void init() {
		DriveTrain.breakMode(false);
	}
}
