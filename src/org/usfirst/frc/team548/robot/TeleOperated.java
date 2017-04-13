package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {

	private static TeleOperated instance;
	private static Timer timer;
	private static int wiggle = 0;
	private static boolean yPressed = false, dPressed = false;

	public static TeleOperated getInstance() {
		if (instance == null)
			instance = new TeleOperated();
		return instance;
	}

	public static XBoxController driver;
	public static XBoxController manip;
	private static boolean povPressed = false, timerStart = false, gearArmOffsetPress = false;
	

	//private static double shooterAdjustment = 0;
	private static double gearOffset = 0;
	private TeleOperated() {
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
		} else if (driver.getXButton()){
			DriveTrain.drive(.15, .15);
		} else if (driver.getYButton()){
			//DriveTrain.drive(.1, .1);
			if(!dPressed){
				DriveTrain.restEncoders();
				dPressed = true;
			}
			DriveTrain.moveDistance(.14 , .12, 0);
			
		} else {
			dPressed = false;
			DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
			//DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
		}
		if(DriverStation.getInstance().isBrownedOut()) driver.setRightRumble(1);
		else driver.setRightRumble(0);
		
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
			} else if (GearIngestor.getArmPos() < 300) {
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
				GearIngestor.setArmPos(Constants.GEARING_MAX-50);
			} else if (manip.getRightTriggerButton()) {
				
				 GearIngestor.setArmPos(Constants.GEARING_MIN);
			} else if(manip.getStartButton()){
				if(wiggle < 3){
					GearIngestor.setArmPower(-.5);
				}
				else if(wiggle >= 3){
					GearIngestor.setArmPower(.5);
				}	
				wiggle ++;
				if (wiggle > 6) wiggle = 0;
			} else {
				GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT+gearOffset);
				/// GearIngestor.stopArm();
			}
		} else {
			GearIngestor.setArmPower(-.7);//Go down
			GearIngestor.setRollerBarPower(.7d);//Spit out gear
		}
		
		if(Climber.isOpen()) manip.setRightRumble(1);
		else manip.setRightRumble(0);
	
		
		if(manip.getPOV() == 180) {
			Climber.setClimbOpen(true);
		} else if(manip.getPOV() == 0 ) {
			Climber.setClimbOpen(false);
		}
		
		if(manip.getBackButton()) gearOffset = 0;
		
		
	
		if(!gearArmOffsetPress && manip.getLeftTriggerButton()) {
			gearOffset-=20;
			gearArmOffsetPress = true;
		} else if(!gearArmOffsetPress && manip.getLeftBumper()) {
			gearOffset+=20;
			gearArmOffsetPress = true;
		} else {
			gearArmOffsetPress = false;
		}
		
		/**
		 * LEDS
		 */
		USBLED.isHasGear(GearIngestor.isGearInIngestor());
		USBLED.isRftOut(Climber.isOpen());
		USBLED.isWantGear(GearIngestor.getArmPos() < 300);
		USBLED.isWombo(manip.getAButton());
		
		
		//Ingestor.setRollerBarDown(!manip.getBackButton());
		/**
		 * Testing MAKE SURE TO REMOVE BEFORE COMP
		 */
//		if(driver.getBButton()) DriveTrain.restHyro();
//		else if(driver.getYButton()) DriveTrain.calibrateHyro();
//		else if(driver.getXButton()) DriveTrain.restEncoders();
		SmartDashboard.putData("HYRO", DriveTrain.hyro);
		
		if(driver.getAButton()) DriveTrain.restHyro();
		
		
		//SmartDashboard stuff
		SmartDashboard.putNumber("Hyro", DriveTrain.getAngle());
//		SmartDashboard.putNumber("Pressure", DriveTrain.getPressure());
//		SmartDashboard.putNumber("Right pos", DriveTrain.getRightEncoderDistance());
//		SmartDashboard.putNumber("Left pos", DriveTrain.getLeftEncoderDistance());  
//		SmartDashboard.putNumber("Right speed", DriveTrain.getRightSpeed());
//		SmartDashboard.putNumber("Left speed", DriveTrain.getLeftSpeed());
//		SmartDashboard.putBoolean("High gear", DriveTrain.isHigh());
//		SmartDashboard.putNumber("Speed", Shooter.getSpeed());
//		SmartDashboard.putBoolean("Ready for takeoff", Climber.isOpen());
	}

	public static void init() {
		DriveTrain.breakMode(false);
	}

	// MANIP
	// Start: Gear during teleop
	// Right Trigger: shoot and elevator for fuel
	// Right Bumper: reverse shooter elevator
	// B Button: open & close gears
	// Y BUtton: enable/disable RFT
	// Right/Left on dpad: expand/compress shooter wall
	// up/down on dpad: up/down with the power of the shooter
	// x button: set elevator power
	// y button: set elevator bar power
	// Driver
	// A Button: wiggle
	// alex's weird controls

}