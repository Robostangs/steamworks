package org.usfirst.frc.team548.robot;

import org.usfirst.frc.team548.robot.AutoModes.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		Climber.getInstance();
		Ingestor.getInstance();
		Shooter.getInstance();
		TopGear.getInstance();
		TeleOperated.getInstance();
	}

	
	@Override
	public void autonomousInit() {
		new BaseLineTimeAuto().start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		TeleOperated.run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		System.out.println(DriveTrain.getAngle());
		DriveTrain.breakMode(true);
		if(TeleOperated.driver.getAButton()) DriveTrain.restHyro();
		if(TeleOperated.driver.getBButton()) DriveTrain.calibrateHyro();
	}
}

