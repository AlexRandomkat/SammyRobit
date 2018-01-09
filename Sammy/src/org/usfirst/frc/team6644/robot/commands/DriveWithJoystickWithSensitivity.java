package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;

public class DriveWithJoystickWithSensitivity extends DriveWithJoystick {
	public DriveWithJoystickWithSensitivity() {
		super();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivemotors.driveWithJoystickWithSensitivity();
	}
}
