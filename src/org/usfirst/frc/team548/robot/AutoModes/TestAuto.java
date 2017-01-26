package org.usfirst.frc.team548.robot.AutoModes;


public class TestAuto extends AutoMode {

	public TestAuto() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		turnToAngleInTime(5, 45, true);
		waitTime(5);
		turnToAngleInTime(5, 0, false);
	}

	

}
