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
		talonLeft.set(3000);
	}
	
	public static void stop() {
		setShooterPower(0);
	}
	
	public static double getSpeed() {
		return talonLeft.getSpeed();
	}
	
	public static void injectAfterSpeed(double speed) {
		setShooterSpeed(speed);
		if(getSpeed() > speed-100) setElevator(.6);
	}
}
