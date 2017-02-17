package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class BoilerGearThenScore extends AutoMode {

	public BoilerGearThenScore() {
		super("Boiler Gear then Score");
	}

	@Override
	protected void run() {
		driveDistance(3, -.9, 7.7, 1.5, false);
		turnToAngleInTime(3, -62, false);
		driveDistance(1, -.5, .86, .25, false);
		openGear(.1, true);
		driveDistance(1, 7., .86, .25, false);
		turnToAngleInTime(1, 10, true);
		driveDistance(3, .9, 7.7, 1.5, false);
		shootAtSpeed(4, Constants.SHOOT_AUTON_SPEED);
	}

}
