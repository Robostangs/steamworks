package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGearLeftSideBlue extends AutoMode {
private boolean isRed;
	
	public SideGearLeftSideBlue() {
		super("Side gear lef side blue");
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
		turnToAngleInTime(2, 57, false);
		driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
		GearIngestorExgeset(.7);
		driveDistanceWithArm(2, -.3, 2, .25, Constants.GEARING_PEGHEIGHT); //back away from gear thing
		//what was added
		turnToAngleInTime(1, -55, false);
		driveDistance(2, .8, 7, 1.0, false);
		turnToAngleInTime(.5, 40, false);
		driveDistance(100, .9, 21.00, 2.00, false);
	
}
}