package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

public class Climber {
	private static Climber instance;
	
	public static Climber getInstance() {
		if(instance == null) instance = new Climber();
		return instance;
	}
	
	private static CANTalon climbTalon;
	
	private Climber() {
		climbTalon = new CANTalon(Constants.CLIMB_TALONID_CLIMBTALON);
	}
	
	public static void setPower(double power) {
		climbTalon.set(power);
	}
	
}
