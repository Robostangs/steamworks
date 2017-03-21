package org.usfirst.frc.team548.robot.AutoModes;

public class SideGear extends AutoMode {
private boolean isRed;
	
	public SideGear(boolean isRed) {
		super("Side Gear");
		this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistance(2.3, -.9, 7.2, 1.5, false); //drive back toward gear
		if(isRed)turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
		else turnToAngleInTime(2, 57, false);
		driveDistance(1.5, -.5, 1.5, .25, false); //drive toward gear spring
		//GearRTFCommand(1.55); //Open gear
		openGear(1, true);
		driveDistance(1.5, .3, 1.5, .25, true); //back away from gear thing
		// TODO Auto-generated method stub

	}

}
