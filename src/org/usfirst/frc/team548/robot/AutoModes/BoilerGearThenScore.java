package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class BoilerGearThenScore extends AutoMode {

	public BoilerGearThenScore() {
		super("Boiler Gear then Score");
	}

	@Override
	protected void run() {
		driveDistance(3, -.9, 7.7, 1.5, false);
		turnToAngleInTime(3, -57, false);
		driveDistance(1.5, -.5, 1.5, .25, false);
		openGear(.1, true);
		driveDistance(1.5, .3, 1.5, .25, true);
		turnToAngleInTime(1, 17, true);
		driveDistance(3, .9, 8.5, 1.5, false);
		shootAtSpeed(4, Constants.SHOOT_AUTON_SPEED);
	}

}
