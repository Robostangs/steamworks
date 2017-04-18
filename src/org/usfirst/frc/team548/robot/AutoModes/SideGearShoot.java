package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGearShoot extends AutoMode{

private boolean isRed;
	
	public SideGearShoot(boolean isRed) {
		super("Side Gear then shoot");
		this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		
		shootAtSpeed(6.25, Constants.SHOOT_AUTON_SPEED);
		
		
		driveInTime(.5, -.5);
		driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
		//driveDistance(2.3, .9, 7.2, 1.5, false); //drive back toward gear
		if(isRed)turnToAngleInTime(1.7, -57, false); //turn to gear spring (-) is red
		else turnToAngleInTime(1.5, 57, false);
		driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
		GearIngestorExgeset(.7);
		//GearRTFCommand(1.55); //Open gear
		//openGear(1, true);
		//driveDistanceWithArm(1.5, -.3, 1.5, .25, Constants.GEARING_MIN); //back away from gear thing
		// TODO Auto-generated method stub

		
	}

}

