package org.usfirst.frc.team548.robot.AutoModes;

public class BaseLineTimeAuto extends AutoMode {

	public BaseLineTimeAuto() {
		super("Drive To Baseline with Time");
	}

	@Override
	protected void run() {
		driveInTime(1.5, .75);
	}

}
