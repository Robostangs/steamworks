package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class OnlyShoot extends AutoMode {

	public OnlyShoot() {
		super("Only Shoot");
	}

	@Override
	protected void run() {
		//shootAtSpeed(12, Constants.SHOOT_AUTON_SPEED);
		//Blue.... Make pos to do red
		wideTurn(.2, -.5, -.1);
		driveInTime(1, -.9);
	}

}
