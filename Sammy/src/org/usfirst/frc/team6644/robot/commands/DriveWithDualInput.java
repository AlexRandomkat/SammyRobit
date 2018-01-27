package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;

/**
 *
 */
public class DriveWithDualInput extends DriveWithJoystick {

	public DriveWithDualInput() {
		super();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivemotors.driveWithDualInputs();
	}
}
