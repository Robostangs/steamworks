package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class MiddleGearHopper extends AutoMode {
	public MiddleGearHopper() {
		super("Middle Gear and then hopper");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
//		driveDistance(1.6, -.9, 6, 1.2, false);
//		openGear(.5, true);
//		driveDistance(.63, .2, .5, 0, true);
//		driveDistance(.6, .5, 1, .1, false);
		
		driveDistanceWithArm(2, .6, 5.925, 2, Constants.GEARING_PEGHEIGHT);
		GearIngestorExgeset(.7);
		driveDistance(2, -.6, 3, .5, false);
		turnToAngleInTime(.5, -90, false);
		driveDistance(1, .7, 4.20, .5, false);
		turnToAngleInTime(.5, 90, false);
		
		
	}

}
