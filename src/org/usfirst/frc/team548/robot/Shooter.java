package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

public class Shooter {
	
private static Shooter instance;
	
	public static Shooter getInstance() {
		if(instance == null) instance = new Shooter();
		return instance;
	}
	
	CANTalon talonLeft, talonRight;
	
	
	private Shooter() {
		talonLeft = new CANTalon(Constants.SHOOT_TALONID_TALONLEFT);
		talonRight = new CANTalon(Constants.SHOOT_TALONID_TALONRIGHT);
	}
}
