package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class DriveTrain {
private static DriveTrain instance;
	
	public static DriveTrain getInstance() {
		if(instance == null) instance = new DriveTrain();
		return instance;
	}
	
	private static CANTalon rightFront, rightBack, rightMini, leftFront, leftBack, leftMini;
	private static Solenoid rightShifter, leftShifter;
	
	
	private DriveTrain() {
		rightFront = new CANTalon(Constants.DT_TALONID_RIGHTFRONT);
		rightBack = new CANTalon(Constants.DT_TALONID_RIGHTBACK);
		rightMini= new CANTalon(Constants.DT_TALONID_RIGHTMINI);
		leftFront = new CANTalon(Constants.DT_TALONID_LEFTFRONT);
		leftBack = new CANTalon(Constants.DT_TALONID_LEFTBACK);
		leftMini = new CANTalon(Constants.DT_TALONID_LEFTMINI);
		rightShifter = new Solenoid(Constants.DT_SOLENOID_RIGHTSHIFTER);
		leftShifter = new Solenoid(Constants.DT_SOLENOID_LEFTSHIFTER);
	}
	
	public static void drive(double leftPower, double rightPower){
		rightFront.set(rightPower);
		rightBack.set(rightPower);
		rightMini.set(rightPower);
		leftFront.set(-leftPower);
		leftBack.set(-leftPower);
		leftMini.set(-leftPower);
	}
	
	public static void stop() {
		drive(0,0);
	}
	
	public static void arcadeDrice(double fwd, double tur) {
		if(Math.abs(tur) < .01) tur = 0;
		if(Math.abs(fwd) < .2) fwd = 0;
		drive(Util.ensureRange(fwd+tur, -1d, 1d), Util.ensureRange(fwd-tur, -1d, 1d));
	}
	
	public static void shift(boolean b) {
		rightShifter.set(b);
		leftShifter.set(b);
	}
	
	public static void shiftToHigh() {
		shift(Constants.DT_SHIFT_HIGH);
	}
	
	public static void shiftLow() {
		shift(!Constants.DT_SHIFT_HIGH);
	}
	
	public static boolean isHigh() {
		return rightShifter.get() == Constants.DT_SHIFT_HIGH;
	}
	
}
