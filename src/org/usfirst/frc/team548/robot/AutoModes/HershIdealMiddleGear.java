package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HershIdealMiddleGear extends AutoMode{

	public HershIdealMiddleGear() {
		super("Hersh Middle Gear Only");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
//		driveDistance(1.6, -.9, 6, 1.2, false);
//		openGear(.5, true);
//		driveDistance(.63, .2, .5, 0, true);
//		driveDistance(.6, .5, 1, .1, false);
		
		driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
		GearIngestorExgeset(.7);
		driveDistanceWithArm(.63, -.2, .5, 0, 50);
		driveDistanceWithArm(.6, -.5, 1, .1, Constants.GEARING_PEGHEIGHT);
		//what was added
		turnToAngleInTime(2, 75, false);
		driveDistance(2, .5, 2.5, 0, false);
		turnToAngleInTime(2, 100, false);
		driveDistance(3, .9, 17, 1, false);
		

		
	}
}