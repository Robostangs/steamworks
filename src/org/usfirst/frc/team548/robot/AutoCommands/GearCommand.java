package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.TopGear;

public class GearCommand extends AutoCommandBase {
	
	private boolean open;
	
	public GearCommand(double timeOut, boolean b) {
		super(timeOut);
		open = b;
	}

	@Override
	public void init() {
		TopGear.setOpen(open);

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Gear Command";
	}

}
