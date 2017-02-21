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
		driveDistance(2, -.9, 6.2, 1.5, false);
		if(isRed) turnToAngleInTime(.8, -90, false);
		else turnToAngleInTime(.8, 90, false);
		driveDistance(1, .5, 1.8, .5, false);
		waitTime(1);
		driveDistance(.5, -.5, .5, 0, false);
		if(isRed) turnToAngleInTime(.8, 90, false);
		else turnToAngleInTime(.8, -90, false);
		driveDistance(1.3, .9, 4.5, 1.0, false);
		if(isRed) wideTurn(1, 0, 0.4);
		else wideTurn(1, .4, .0);
		shootAtSpeed(6.6, Constants.SHOOT_AUTON_SPEED);
	}

}
