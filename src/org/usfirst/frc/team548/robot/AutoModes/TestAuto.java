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
		//Fail hopper
//		driveDistance(1.6, -.9, 8.25, 2.5, false);
//		turnToAngleInTime(.2, -10, false);
//		turnToAngleInTime(.5, 90, false);
//		driveDistance(.5, .7, .7, .3, false);
//		turnToAngleInTime(.5, 20, false);
//		driveDistance(2.4, .9, 7.6, 1.5, false);
		//Wide turn and back
//		wideTurn(1, 0, .75);
//		shootAtSpeed(4.0, Constants.SHOOT_AUTON_SPEED);
//		driveDistance(2.0, -.5, 5.0, 1);
		//turnToAngleInTime()
		
		//driveDistance(3, -.9, 7.7, 1.5, false);
		
		
		
		//turnToAngleInTime(3, 365, false);
		//driveInTime(2, .2);
		
		//	driveDistanceWithArm(2.3, .9, 3, 1.5, Constants.GEARING_PEGHEIGHT);
			//driveDistance(2.3, .9, 7.2, 1.5, false); //drive back toward gear
			//if(isRed)turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
			 turnToAngleInTime(2, 57, false);
			

		}
	}

	


