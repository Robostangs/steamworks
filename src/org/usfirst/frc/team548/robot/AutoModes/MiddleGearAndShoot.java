package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.DriveTrain;

public class MiddleGearAndShoot extends AutoMode {

	boolean isRed;
	public MiddleGearAndShoot(boolean red) {
		super("Middle Gear And Shoot. Red? "+red);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		DriveTrain.shiftHigh(true);
		driveDistance(2, .7, 6, 3, false);
		openGear(.5, true);
		driveDistance(2, -.3, .5, 0, true);
		shootAtSpeed(1000, Constants.SHOOT_AUTON_SPEED);
		
		
//		driveDistance(.6, .5, 1, .1, false);
//		if(isRed) turnToAngleInTime(.7, 65, false);
//		else turnToAngleInTime(.7, -65, false);
//		driveDistance(2.1, 1, 10.9, 1, false);
//		if (!isRed) wideTurn(.45, .75, 0);
//		else wideTurn(.45, .75, 0);
//		shootAtSpeed(8, Constants.SHOOT_AUTON_SPEED);
	}

}
