package org.usfirst.frc.team548.robot;

import org.usfirst.frc.team548.robot.AutoModes.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	
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
		Shooter.getInstance();
		TopGear.getInstance();
		TeleOperated.getInstance();
		PDP = new PowerDistributionPanel();
	}

	
	//true is red, false is blue
	@Override
	public void autonomousInit() {
		new SideGear(false).start();
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
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		Shooter.setShooterPower(TeleOperated.driver.getTriggers());
		DriveTrain.breakMode(true);
		if(TeleOperated.driver.getAButton()) DriveTrain.restHyro();
		if(TeleOperated.driver.getBButton()) DriveTrain.calibrateHyro();
		System.out.println(Shooter.getSpeed());
		
	}
	@Override
	public void disabledPeriodic() {
		if(TeleOperated.driver.getAButton()) DriveTrain.calibrateHyro();
	}
}
