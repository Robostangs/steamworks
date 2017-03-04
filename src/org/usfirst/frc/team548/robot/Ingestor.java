package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class Ingestor {
	
private static Ingestor instance;
	
	public static Ingestor getInstance() {
		if(instance == null) instance = new Ingestor();
		return instance;
	}
	
	private static CANTalon rollerMotor, elevatorMotor;
	private static DoubleSolenoid rollerSol;
	private static Solenoid ingestorWall;
	
	private Ingestor() {
		rollerMotor = new CANTalon(Constants.ING_TALONID_ROLLERMOTOR);
		elevatorMotor = new CANTalon(Constants.ING_TALONID_ELEVATOR);
		rollerSol = new DoubleSolenoid(Constants.ING_SOLENOID_LEFTROLLERSOL, Constants.ING_SOLENOID_RIGHTROLLERSOL);	
		ingestorWall = new Solenoid(Constants.ING_SOLENOID_WALL);
	}
	
	public static void setRollerBarPower(double power) {
		rollerMotor.set(power);
	}
	
	public static void setElevatorPower(double power) {
		elevatorMotor.set(-power);
	}
	
	public static void setRollerBarDown(boolean b) {
		rollerSol.set((b)? Value.kForward : Value.kReverse);
	}
	
	
	
	public static void setIngestorWall(boolean b) {
		ingestorWall.set(b);
	}
	
	public static boolean getIngestorWall() {
		return ingestorWall.get();
	}
}