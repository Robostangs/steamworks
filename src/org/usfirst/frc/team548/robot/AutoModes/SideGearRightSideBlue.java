package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGearRightSideBlue extends AutoMode {

private boolean isRed;
	
	public SideGearRightSideBlue() {
		super("Side geear right side blue");
	//	this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistanceWithArm(2.3, .9, 7.2, 1.5, Constants.GEARING_PEGHEIGHT);
		turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
		driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
		GearIngestorExgeset(.7);
		driveDistanceWithArm(2, -.3, 2, .25, Constants.GEARING_PEGHEIGHT); //back away from gear thing
		//what was added
		turnToAngleInTime(1, 55, false);		
		driveDistance(10, .9, 21.00, 2, false);
}
}
