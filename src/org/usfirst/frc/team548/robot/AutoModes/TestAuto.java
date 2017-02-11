package org.usfirst.frc.team548.robot.AutoModes;


public class TestAuto extends AutoMode {

	public TestAuto() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistance(2.5, -.9, 4.8, 1.25);
		openGear(.1, true);
		driveDistance(1, .3, .5, 0);
		driveDistance(1, .5, 1, .1);
		turnToAngleInTime(1.2, 75, false);
		driveDistance(5, 1, 8.1, 1);
		//driveInTime(1.5, -.5);
	}

	

}
