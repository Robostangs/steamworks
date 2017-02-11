package org.usfirst.frc.team548.robot.AutoCommands;

import javax.rmi.CORBA.Util;

import org.usfirst.frc.team548.robot.DriveTrain;
import org.usfirst.frc.team548.robot.Utils;

public class DriveDistance extends AutoCommandBase {

	private double power, distance, threashold;
	public DriveDistance(double timeOut, double power, double distance, double threshhold) {
		super(timeOut);
		this.power = power;
		this.distance = distance;
		this.threashold = threshhold;
	}

	@Override
	public void init() {
		DriveTrain.restEncoders();
		DriveTrain.breakMode(true);
		DriveTrain.restHyro();
	}

	@Override
	protected void run() {
		if(Math.abs(DriveTrain.averageDistance()) < distance) {
			if(Math.abs(distance)- Math.abs(DriveTrain.averageDistance()) < threashold) DriveTrain.driveStraight(.255 * (Math.abs(power)/power)); 
			else DriveTrain.driveStraight(power);
			
		} else {
			DriveTrain.stop();
			setDone(true);
		}

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Drive Straight Distance";
	}

}
