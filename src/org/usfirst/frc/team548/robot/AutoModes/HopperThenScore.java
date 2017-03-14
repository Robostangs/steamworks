package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;


public class HopperThenScore extends AutoMode {
	
	private boolean isRed;

	public HopperThenScore(boolean isRed) {
		super("Hopper then Score");
		this.isRed = isRed;
	}

	@Override
	protected void run() {
		//driveDistance(2, -.9, 6.2, 1.5, false);
		driveDistance(2.6, -1, 10, 2, false);
		if(isRed) turnToAngleInTime(.8, -90, false);
		else turnToAngleInTime(.8, 90, false);
		driveDistance(1, .5, 2.5, .5, false);
		waitTime(1);
		driveDistance(.5, -.5, .5, 0, false);
		if(isRed) turnToAngleInTime(.8, 90, false);
		else turnToAngleInTime(.8, -90, false);
		driveDistance(2, .9, 8.25, 1.0, false);
		driveDistance(1, .9, 4.25, 1.0, false);
		if(isRed) wideTurn(1, 0.1, 0.6);
		else wideTurn(1, .6, .1);
		shootAtSpeed(7.9, Constants.SHOOT_AUTON_SPEED);
	}

}
