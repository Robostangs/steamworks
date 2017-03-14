package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter {
	
private static Shooter instance;
	
	public static Shooter getInstance() {
		if(instance == null) instance = new Shooter();
		return instance;
	}
	
	private static CANTalon talonLeft, talonRight, elvevator;
	
	private Shooter() {
		talonLeft = new CANTalon(Constants.SHOOT_TALONID_TALONLEFT); //Encoder
		talonRight = new CANTalon(Constants.SHOOT_TALONID_TALONRIGHT);
		talonLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		talonLeft.setPID(Constants.SHOOT_PID_P, Constants.SHOOT_PID_I, Constants.SHOOT_PID_D, Constants.SHOOT_PID_F, Constants.SHOOT_PID_IZONE, 0, 0);
		
		talonRight.changeControlMode(TalonControlMode.Follower);
		//talonRight.reverseSensor(true);
		talonRight.set(talonLeft.getDeviceID());
		elvevator = new CANTalon(Constants.SHOOT_TALONID_TALONELEVATOR);
		//talonRight.enableBrakeMode(false);
		//talonLeft.enableBrakeMode(false);
	}
	
	public static void setShooterPower(double p) {
		talonLeft.changeControlMode(TalonControlMode.PercentVbus);
		talonLeft.set(p);
		//talonRight.set(p);
	}
	
	public static void setElevator(double p) {
		elvevator.set(p);
	}
	
	public static void setShooterSpeed(double speed) {
		talonLeft.changeControlMode(TalonControlMode.Speed);
		talonLeft.set(speed);
	}
	
	public static void stop() {
		setShooterPower(0);
	}
	
	public static double getSpeed() {
		return talonLeft.getSpeed();
	}
	
	public static void injectAfterSpeed(double speed) {
		setShooterSpeed(speed);
		if(getSpeed() > speed-45) setElevator(.4);
	}
	
	public static void addF(double a){
		talonLeft.setF(Constants.SHOOT_PID_F+a);
	}
}
