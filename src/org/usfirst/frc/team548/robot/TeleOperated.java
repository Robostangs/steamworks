package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Compressor;
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
		//When hersh ins't here
		if (!driver.getLeftTriggerButton()) {

			if (driver.getRightTriggerButton()) {
				GearIngestor.setArmPos(Constants.GEARING_MIN);
			} else {
				GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT);
			}
			
			if (GearIngestor.getArmPos() < 300) {
				GearIngestor.rollerIngestCurrentLimiting();
				if (GearIngestor.isGearInIngestor()) {
					//manip.setLeftRumble(1);
					driver.setLeftRumble(1);
				}
			} else if (driver.getLeftBumper()) {
				GearIngestor.setRollerBarPower(-.7);
				if (GearIngestor.isGearInIngestor()) {
					//manip.setLeftRumble(1);
					driver.setLeftRumble(1);
				}
			} else {

				//manip.setLeftRumble(0);
				driver.setLeftRumble(0);
				GearIngestor.setRollerBarPower(0);
			}
		} else {
			GearIngestor.setArmPos(0);//Go down
			GearIngestor.setRollerBarPower(.7d);//Spit out gear
		}
		
		
		DriveTrain.shiftHigh(driver.getRightBumper());
		
		/**
		 * Manip
		 */
//		if(Math.abs(manip.getRightStickYAxis()) > .2) Climber.setPower(-Math.abs(manip.getRightStickYAxis()));
//		else Climber.setPower(0);
//		
//		if (!manip.getBButton()) {
//			//Roller
//			if (manip.getRightBumper()) {
//				manip.setLeftRumble(0);
//				driver.setLeftRumble(0);
//				GearIngestor.setRollerBarPower(.7d);
//			} else if (GearIngestor.getArmPos() < 300) {
//				GearIngestor.rollerIngestCurrentLimiting();
//				if (GearIngestor.isGearInIngestor()) {
//					manip.setLeftRumble(1);
//					driver.setLeftRumble(1);
//				}
//			} else if (manip.getPOV() == 90) {
//				GearIngestor.rollerIngestCurrentLimiting();
//				if (GearIngestor.isGearInIngestor()) {
//					manip.setLeftRumble(1);
//					driver.setLeftRumble(1);
//				}
//			} else {
//
//				manip.setLeftRumble(0);
//				driver.setLeftRumble(0);
//				GearIngestor.setRollerBarPower(0);
//			}
//
//			// ARM
//			if (Math.abs(manip.getLeftStickYAxis()) > .2) {
//				GearIngestor.setArmPower(-manip.getLeftStickYAxis());
//			} else if (manip.getYButton()) {
//				GearIngestor.setArmPos(Constants.GEARING_MAX-50);
//			} else if (manip.getRightTriggerButton()) {
//				
//				 GearIngestor.setArmPos(Constants.GEARING_MIN);
//			} else if(manip.getStartButton()){
//				if(wiggle < 4){
//					GearIngestor.setArmPower(-.5);
//				}
//				else if(wiggle >= 4){
//					GearIngestor.setArmPower(.5);
//				}	
//				wiggle ++;
//				if (wiggle > 8) wiggle = 0;
//			} else {
//				GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT);
//				/// GearIngestor.stopArm();
//			}
//		} else {
//			GearIngestor.setArmPos(0);//Go down
//			GearIngestor.setRollerBarPower(.7d);//Spit out gear
//		}
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
////		/**
//		 * JAREDS CRAP BELOW
//		 */
//		
//		DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
//		if(!driver.getStartButton()) {
//			TopGear.setOpen(driver.getBButton());
//		}
//		
//		if(driver.getRightTriggerButton()) {
//			Shooter.injectAfterSpeed(2900);
//		} else {
//			if(driver.getRightBumper()) { 
//				Shooter.setElevator(-.8);
//				//Shooter.setShooterPower(-0.5);	
//			} else {
//				Shooter.setElevator(0);
//			}
//			Shooter.setShooterPower(0);
//		}
//		
//		if(driver.getYButton()) {
//			new Compressor(0).start();
//		} else {
//			new Compressor(0).stop();
//		}
//		
	}
	
	public static void init() {
		DriveTrain.breakMode(false);
	}
}
