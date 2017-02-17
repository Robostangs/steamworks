package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class ShootThenBoilerGear extends AutoMode {

	public ShootThenBoilerGear() {
		super("Shoot then gear");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		wideTurn(.5, .2, 0);
		shootAtSpeed(5, Constants.SHOOT_AUTON_SPEED);
		wideTurn(.2, .15, .15);
		driveDistance(4, -.9, 6.9, 1, false);
		turnToAngleInTime(.5, -10, true);
		driveDistance(.5, .5, .86, .2, false);
		openGear(.1, true);
		driveDistance(1, .5, .86, 0, false);
	}

}
