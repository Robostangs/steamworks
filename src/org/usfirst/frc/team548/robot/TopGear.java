package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class TopGear {
	
	private static Solenoid sol;
	private static TopGear instance;
	private TopGear() {
		sol = new Solenoid(Constants.GEAR_SOL_PORT);
	}
	
	public static TopGear getInstance() {
		if(instance == null) instance = new TopGear();
		return instance;
	}
	
	public static void setOpen(boolean b) {
		sol.set(b);
	}
	
	public static boolean isOpen() {
		return sol.get();
	}

}
