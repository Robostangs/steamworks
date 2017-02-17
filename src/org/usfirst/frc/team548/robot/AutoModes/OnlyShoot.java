package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class OnlyShoot extends AutoMode {

	public OnlyShoot() {
		super("Only Shoot");
	}

	@Override
	protected void run() {
		shootAtSpeed(7, Constants.SHOOT_AUTON_SPEED);
	}

}
