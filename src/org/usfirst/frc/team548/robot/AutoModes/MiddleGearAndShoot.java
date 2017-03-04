package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class MiddleGearAndShoot extends AutoMode {

	boolean isRed;
	public MiddleGearAndShoot(boolean red) {
		super("Middle Gear And Shoot. Red? "+red);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistance(1.6, -.9, 6, 1.2, false);//Go forward
		openGear(.2, true);//Open gear
		driveDistance(.63, .3, .5, 0, true); //Drive back slowly
		driveDistance(.6, .5, 1, .1, false); //Drive back more
		if(isRed) turnToAngleInTime(.7, 65, false); //turn toward boiler  
		else turnToAngleInTime(.7, -65, false); 
		driveDistance(2.1, 1, 10.9, 1, false); //Drive to boiler
		if (!isRed) wideTurn(.45, .75, 0); //turn toward boiler
		else wideTurn(.45, .75, 0);
		shootAtSpeed(8, Constants.SHOOT_AUTON_SPEED); //Shoot
	}

}