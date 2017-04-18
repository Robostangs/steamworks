package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HershIdealSideGear extends AutoMode {

private boolean isRed;
	
	public HershIdealSideGear(boolean isRed) {
		super("Hersh Ideal Side Gear");
		this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
		//driveDistance(2.3, .9, 7.2, 1.5, false); //drive back toward gear
		if(isRed)turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
		else turnToAngleInTime(2, 57, false);
		driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
		GearIngestorExgeset(.7);
		//GearRTFCommand(1.55); //Open gear
		//openGear(1, true);
		driveDistanceWithArm(2, -.3, 2, .25, Constants.GEARING_PEGHEIGHT); //back away from gear thing
		//what was added
		if(isRed)turnToAngleInTime(1, 55, false);
		else turnToAngleInTime(1, -55, false);
		
		//if(isRed)driveDistance(2, .8, 7, 1.0, false);
		//else 
		driveDistance(10, .9, 21.00, 2, false);
		
		
		//if(isRed)turnToAngleInTime(.5, -45, false);
		//if(isRed)driveDistance(100, .9, 21.00, 2.00, false);
		
		
		// TODO Auto-generated method stub

	}
}
