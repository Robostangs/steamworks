package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class ShootThenBoilerGear extends AutoMode {

	public ShootThenBoilerGear() {
		super("Shoot then gear");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		//wideTurn(.5, .2, 0);
		shootAtSpeed(5, Constants.SHOOT_AUTON_SPEED);
		wideTurn(.2, 0, -.15);
		driveDistance(2.3, -.9, 9.5, 1, false);
		turnToAngleInTime(.5, -18, true);
		driveDistance(1.5, -.5, 1.7, .5, false);
		openGear(.5, true);
		driveDistance(1, .3, 1.5, 0, true);
	}

}
