package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class Ingestor {
	
private static Ingestor instance;
	
	public static Ingestor getInstance() {
		if(instance == null) instance = new Ingestor();
		return instance;
	}
	
	private static CANTalon rollerMotor;
	private static Solenoid gearSol, leftRolerSol, rightRollerSol;
	
	private Ingestor() {
		rollerMotor = new CANTalon(Constants.ING_TALONID_ROLLERMOTOR);
		//gearSol = new Solenoid(Constants.ING_SOLENOID_GEARSOL);
		//leftRolerSol = new Solenoid(Constants.ING_SOLENOID_LEFTROLLERSOL);
		//rightRollerSol = new Solenoid(Constants.ING_SOLENOID_RIGHTROLLERSOL);	
	}
	
	public static void setRollerBarPower(double power) {
		rollerMotor.set(power);
	}
	
}
