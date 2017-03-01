package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber {
	private static Climber instance;
	
	public static Climber getInstance() {
		if(instance == null) instance = new Climber();
		return instance;
	}
	
	private static CANTalon climbTalon;
	private static Solenoid sol;
	
	private Climber() {
		climbTalon = new CANTalon(Constants.CLIMB_TALONID_CLIMBTALON);
		sol = new Solenoid(Constants.CLIMB_SOL_PORT);
	}
	
	public static void setPower(double power) {
		climbTalon.set(power);
	}
	
	public static void setClimbOpen(boolean b) {
		sol.set(b);
	}
	
	public static boolean isOpen() {
		return sol.get();
	}
	
}
