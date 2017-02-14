package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class TestAuto extends AutoMode {

	public TestAuto() {
		super("Test");
		// TODO Auto-generated constructor stub
	}
	//.869539 feet to rot
	@Override
	protected void run() {
		//MiddleGearShoot
		driveDistance(2, -.9, 6, 1.2, false);
		openGear(1, true);
		driveDistance(.83, .3, .5, 0, true);
		driveDistance(.6, .5, 1, .1, true);
		turnToAngleInTime(.8, -75, false);
		driveDistance(2.5, 1, 10.7, 1, false);
		wideTurn(.45, .75, 0);
		shootAtSpeed(7, Constants.SHOOT_AUTON_SPEED);
		
		
		//Wide turn and back
//		wideTurn(1, 0, .75);
//		shootAtSpeed(4.0, Constants.SHOOT_AUTON_SPEED);
//		driveDistance(2.0, -.5, 5.0, 1);
		//turnToAngleInTime()
		
	}

	

}
