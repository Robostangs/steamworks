package org.usfirst.frc.team548.robot;

import org.usfirst.frc.team548.robot.AutoModes.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
		// Ingestor.getInstance();
		// Shooter.getInstance();
		// TopGear.getInstance();
		TeleOperated.getInstance();
		GearIngestor.getInstance();
		// SoundServer.getInstance();
		// SoundServer.startServer();
		dis = new AnalogInput(1);
		PDP = new PowerDistributionPanel();
		GearIngestor.setArmOffSet();
	}

	public static PowerDistributionPanel PDP;
	AnalogInput dis;

	@Override
	public void autonomousInit() {
		new MiddleGear().start();
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
		// SmartDashboard.putNumber("Speed",
		// Shooter.getSpeed());
		// if(Math.abs(TeleOperated.driver.getTriggers()) > .1) {
		// Shooter.setShooterPower(TeleOperated.driver.getTriggers());
		// } else if(TeleOperated.driver.getAButton()) {
		// Shooter.setShooterSpeed(3000);
		// } else {
		// Shooter.stop();
		// }
		//
		// if(TeleOperated.driver.getRightBumper()) {
		// Shooter.setElevator(0.8);
		// } else {
		// Shooter.setElevator(0);
		// }
		// SoundServer.setBoolData1(Climber.isOpen());
		//SmartDashboard.putNumber("Distance", dis.getAverageValue() * .002314d + 1.976);
		SmartDashboard.putData("HYRO", DriveTrain.hyro);
	}

	boolean testStarted = false;
	int currentTest = 0;
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		// Shooter.setShooterPower(TeleOperated.driver.getTriggers());
		// DriveTrain.breakMode(true);
		// if(TeleOperated.driver.getAButton()) DriveTrain.restHyro();
		// if(TeleOperated.driver.getBButton()) DriveTrain.calibrateHyro();
		// System.out.println(Shooter.getSpeed());

		// GearIngestor.setDoorOpen(TeleOperated.driver.getAButton());

		System.out.println(GearIngestor.getArmPos() + " " + GearIngestor.getAbsPos());

		//
		// if(Math.abs(TeleOperated.driver.getLeftStickYAxis()) > .2 ) {
		// GearIngestor.setArmPower(TeleOperated.driver.getLeftStickYAxis());
		if (!testStarted) {
			if (TeleOperated.driver.getAButton()) {
				GearIngestor.setArmPos(Constants.GEARING_MIN);
				// .443 gear
				// .703 max
			} else if (TeleOperated.driver.getBButton()) {
				GearIngestor.setArmPos(Constants.GEARING_MAX);
			} else {
				GearIngestor.stopArm();
			}
		} else {
			
		}
	}
	
	

	public void disabledPeriodic() {
		// SoundServer.setBoolData1(false);
		SmartDashboard.putNumber("Distance", dis.getAverageValue() * .002314d + 1.976);
		if (TeleOperated.driver.getAButton())
			DriveTrain.restHyro();
		if (TeleOperated.driver.getBButton())
			DriveTrain.calibrateHyro();
		
		testStarted = false;
		currentTest = 0;
	}
}
