package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HopperThenScore extends AutoMode {

	public HopperThenScore() {
		super("Hopper then Score");
	}

	@Override
	protected void run() {
		driveDistance(1.6, -.9, 8.25, 2.5, false);
		turnToAngleInTime(.2, -10, false);
		turnToAngleInTime(.5, 90, false);
		driveDistance(.5, .7, .7, .3, false);
		turnToAngleInTime(.5, 20, false);
		driveDistance(2.4, .9, 7.6, 1.5, false);
		shootAtSpeed(5, Constants.SHOOT_AUTON_SPEED);
	}

}
