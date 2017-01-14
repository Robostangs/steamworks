package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class Ingestor {
	
private static Ingestor instance;
	
	public static Ingestor getInstance() {
		if(instance == null) instance = new Ingestor();
		return instance;
	}
	
	CANTalon boxRight, boxLeft, rollerMotor;
	Solenoid gearSol, leftRolerSol, rightRollerSol;
	
	private Ingestor() {
		boxRight = new CANTalon(Constants.ING_TALONID_BOXRIGHT);
		boxLeft = new CANTalon(Constants.ING_TALONID_BOXLEFT);
		rollerMotor = new CANTalon(Constants.ING_TALONID_ROLLERMOTOR);
		gearSol = new Solenoid(Constants.ING_SOLENOID_GEARSOL);
		leftRolerSol = new Solenoid(Constants.ING_SOLENOID_LEFTROLLERSOL);
		rightRollerSol = new Solenoid(Constants.ING_SOLENOID_RIGHTROLLERSOL);
		
	}
		
}
