package org.usfirst.frc.team548.robot.AutoModes;


public class TestAuto extends AutoMode {

	public TestAuto() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		turnToAngleInTime(2.5, 90, true);
		driveInTime(2, .5);
		turnToAngleInTime(2.5, 0, false);
		driveInTime(2, .5);
	}

	

}
