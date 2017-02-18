package org.usfirst.frc.team548.robot;

public class Constants {
	//Xbox
	public static final int XB_POS_DRIVER = 0;
	public static final int XB_POS_MANIP = 1;
	//DriveTrain
	public static final int DT_TALONID_RIGHTFRONT = 7;
	public static final int DT_TALONID_RIGHTBACK = 8;
	public static final int DT_TALONID_RIGHTMINI = 9;
	public static final int DT_TALONID_LEFTFRONT = 4;
	public static final int DT_TALONID_LEFTBACK = 5;
	public static final int DT_TALONID_LEFTMINI = 6;
	public static final int DT_SOLENOID_SHIFTER = 0;
	public static final boolean DT_SHIFT_HIGH = true;
	public static final double DT_DRIVE_STRAIGHT = 1.20;
	
	//Climber
	public static final int CLIMB_TALONID_CLIMBTALON = 12;
	//Ingestor
	public static final int ING_TALONID_ELEVATOR = 2;
	public static final int ING_TALONID_ROLLERMOTOR = 11;
	public static final int ING_SOLENOID_LEFTROLLERSOL = 0;
	public static final int ING_SOLENOID_RIGHTROLLERSOL = 0;
	//Shooter
	public static final int SHOOT_TALONID_TALONRIGHT= 10;
	public static final int SHOOT_TALONID_TALONLEFT= 3;
	public static final int SHOOT_TALONID_TALONELEVATOR = 1;
	public static final int SHOOT_AUTON_SPEED = 3300;
	
	public static final double SHOOT_PID_P = .01;
	public static final double SHOOT_PID_I = .00001;
	public static final double SHOOT_PID_D = 0;
	public static final double SHOOT_PID_F = .0232;
	public static final int SHOOT_PID_IZONE = 50;
	//Gear Stuff
	public static final int GEAR_SOL_PORT = 1;
}
