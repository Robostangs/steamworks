package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Climber;

public class MiddleGear extends AutoMode {

	public MiddleGear() {
		super("Middle Gear Only");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistance(1.6, -.9, 6, 1.2, false);
		GearRFT(1.55);
		Climber.setClimbOpen(false);
		driveDistance(.63, .3, .5, 0, true);
		driveDistance(.6, .5, 1, .1, false);
	}

}
