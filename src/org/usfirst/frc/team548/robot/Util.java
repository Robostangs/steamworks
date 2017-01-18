package org.usfirst.frc.team548.robot;

public class Util {
	public static double ensureRange(double v, double min, double max) {
		return Math.min(Math.max(v, min), max);
	}
	
	public double negPowTwo(double v) {
		return (v == 0) ? Math.pow(v, 2)*(Math.abs(v)/v) : 0; 
	}
}
