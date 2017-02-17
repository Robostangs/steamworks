package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class MiddleGearAndShoot extends AutoMode {

	boolean isRed;
	public MiddleGearAndShoot(boolean red) {
		super("Middle Gear And Shoot. Red? "+red);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistance(2, -.9, 6, 1.2, false);
		openGear(1, true);
		driveDistance(.83, .3, .5, 0, true);
		driveDistance(.6, .5, 1, .1, false);
		if(isRed) turnToAngleInTime(.8, -75, false);
		else turnToAngleInTime(.8, 75, false);
		driveDistance(2.5, 1, 10.7, 1, false);
		if (isRed) wideTurn(.45, .75, 0);
		else wideTurn(.45, .75, 0);
		shootAtSpeed(7, Constants.SHOOT_AUTON_SPEED);
	}

}
