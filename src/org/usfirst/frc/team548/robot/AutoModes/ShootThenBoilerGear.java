package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class ShootThenBoilerGear extends AutoMode {

	public ShootThenBoilerGear() {
		super("Shoot then gear");
		// TODO Auto-generated constructor stub
	}
	/**
	 * DO NOT MERGE READY FOR COMP
	 */
	@Override
	protected void run() {
		//wideTurn(.5, .2, 0);
		shootAtSpeed(5, Constants.SHOOT_AUTON_SPEED);
		driveDistance(.5, -.5, 1, .3, false);
		//wideTurn(.2, 0, -.15);
		turnToAngleInTime(1, 40, false);
		driveDistance(2.7, -.9, 7, 1, false);
		turnToAngleInTime(1, -30, true);
		driveDistance(1.5, -.5, 1.7, .5, false);
		openGear(.5, true);
		driveDistance(1, .3, 1.5, 0, true);
	}

}
