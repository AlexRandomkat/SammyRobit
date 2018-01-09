package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;

public class DriveWithControllerWithSensitivity extends DriveWithController {

	public DriveWithControllerWithSensitivity() {
		super();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivemotors.driveWithControllerWithSensitivity();
	}
}
