package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class BoilerGearThenScore extends AutoMode {
	
	/**
	 * ONLY RED I THINK
	 * Also this probably does not work so do not use it NEG IS RED
	 */
	public BoilerGearThenScore() {
		super("Boiler Gear then Score");
	}

	@Override
	protected void run() {
		driveDistance(3, -.9, 7.7, 1.5, false); //drive back toward gear
		turnToAngleInTime(3, -57, false); //turn to gear spring (-) is red
		driveDistance(1.5, -.5, 1.5, .25, false); //drive toward gear spring
		openGear(.1, true); //Open gear
		driveDistance(1.5, .3, 1.5, .25, true); //back away from gear thing
//		turnToAngleInTime(1, 17, true); //Turn to boiler
//		driveDistance(3, .9, 8.5, 1.5, false); //Drive to boiler
//		shootAtSpeed(4, Constants.SHOOT_AUTON_SPEED);//Shoot
	}

}
