package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateSmartDashboard extends Command {

	public UpdateSmartDashboard() {
		// put required subsystems here
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		DriveWithJoystickWithSensitivity driveCommand = new DriveWithJoystickWithSensitivity();
		if (driveCommand.isRunning()) {
			// updates SmartDashboard with the program's motor outputs
			double[] prgmMotorOutputs = driveCommand.getDriveOutputs();
			SmartDashboard.putNumber("Left motor:", prgmMotorOutputs[0]);
			SmartDashboard.putNumber("Right motor:", prgmMotorOutputs[1]);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
