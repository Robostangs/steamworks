package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Climber;
import org.usfirst.frc.team548.robot.Constants;

public class MiddleGear extends AutoMode {

	public MiddleGear() {
		super("Middle Gear Only");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
//		driveDistance(1.6, -.9, 6, 1.2, false);
//		openGear(.5, true);
//		driveDistance(.63, .2, .5, 0, true);
//		driveDistance(.6, .5, 1, .1, false);
		
		driveDistanceWithArm(1.6, .9, 6, 1.2, Constants.GEARING_PEGHEIGHT);
		GearIngestorExgeset(2);
		driveDistance(.63, -.2, .5, 0, true);
		driveDistance(.6, -.5, 1, .1, false);
	}

}