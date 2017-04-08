package org.usfirst.frc.team548.robot;

import org.usfirst.frc.team548.robot.AutoModes.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	AutoMode autonCommand;
	SendableChooser austinChooser;
	
	public static PowerDistributionPanel PDP;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		Climber.getInstance();
		//Ingestor.getInstance();
		//Shooter.getInstance();
		//TopGear.getInstance();
		GearIngestor.getInstance();
		TeleOperated.getInstance();
		PDP = new PowerDistributionPanel();
		
		austinChooser = new SendableChooser<AutoMode>();
		//austinChooser.addDefault("Shoot Only", new OnlyShoot());
		austinChooser.addDefault("Middle Gear", new MiddleGear());
	//	austinChooser.addObject("Shoot Only", new OnlyShoot());
		austinChooser.addObject("Red Side Gear no driving", new SideGear(true));
		austinChooser.addObject("Blue Side Gear  no driving", new SideGear(false));
	//  austinChooser.addObject("Red Side Gear with driving", new HershIdealSideGear(true));
	//	austinChooser.addObject("Blue side gear with driving", new HershIdealSideGear(false));
		austinChooser.addObject("Middle gear with driving", new HershIdealMiddleGear());
		austinChooser. addObject("Red right", new SideGearRightSideRed());
		austinChooser.addObject("Blue right", new SideGearRightSideBlue());
		austinChooser.addObject("Red left", new SideGearLeftSideRed());
		austinChooser.addObject("Blue left", new SideGearLeftSideBlue());
		
		SmartDashboard.putData("AUTO MODE", austinChooser);
		GearIngestor.setArmOffSet();
		USBLED.getInstance();
	}

	
	//true is red, false is blue
	@Override
	public void autonomousInit() {
		((AutoMode)austinChooser.getSelected()).start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	public void teleopInit() {
		TeleOperated.init();
	}
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		TeleOperated.run();
//		SmartDashboard.putNumber("Speed", 
//				Shooter.getSpeed());
//		if(Math.abs(TeleOperated.driver.getTriggers()) > .1) {
//			Shooter.setShooterPower(TeleOperated.driver.getTriggers());
//		} else if(TeleOperated.driver.getAButton()) {
//			Shooter.setShooterSpeed(3000);
//		} else {
//			Shooter.stop();
//		}
//		
//		if(TeleOperated.driver.getRightBumper()) {
//			Shooter.setElevator(0.8);
//		} else {
//			Shooter.setElevator(0);
//		}
		SmartDashboard.putNumber("Arm pos", GearIngestor.getArmPos());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
//		Shooter.setShooterPower(TeleOperated.driver.getTriggers());
//		DriveTrain.breakMode(true);
//		if(TeleOperated.driver.getAButton()) DriveTrain.restHyro();
//		if(TeleOperated.driver.getBButton()) DriveTrain.calibrateHyro();
		System.out.println(GearIngestor.getArmPos() + " "+GearIngestor.getAbsPos());
		
//		
//		if(Math.abs(TeleOperated.driver.getLeftStickYAxis()) > .2 ) {
//			GearIngestor.setArmPower(TeleOperated.driver.getLeftStickYAxis());
		if (TeleOperated.driver.getAButton()) {
			GearIngestor.setArmPos(Constants.GEARING_MIN);
			//.443 gear
			//.703 max
		}  else if(TeleOperated.driver.getBButton()) {
			GearIngestor.setArmPos(Constants.GEARING_MAX);
		} else {
			if(Math.abs(TeleOperated.driver.getRightStickYAxis()) > .2) {
				GearIngestor.setArmPower(TeleOperated.driver.getRightStickYAxis());
			} else {
				GearIngestor.stopArm();
			}
			
		}
//		
//		if(TeleOperated.driver.getRightTriggerButton()) GearIngestor.rollerIngestCurrentLimiting();
//		else if(TeleOperated.driver.getLeftTriggerButton()) GearIngestor.setRollerBarPower(.5);
//		else GearIngestor.stopRoller();
		
		
	}
	@Override
	public void disabledPeriodic() {
		if(TeleOperated.driver.getAButton()) DriveTrain.calibrateHyro();
		TeleOperated.manip.setRightRumble(0);
		TeleOperated.manip.setLeftRumble(0);
		TeleOperated.driver.setRightRumble(0);
		TeleOperated.driver.setLeftRumble(0);
		//System.out.println(GearIngestor.getAbsPos());
		//SmartDashboard.putData("AUTO MODE", austinChooser);
		
	}
}
