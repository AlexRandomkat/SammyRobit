package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.Robot;

public class DriveWithJoystick extends Command {

	public DriveWithJoystick() {
		requires(Robot.drivemotors);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivemotors.startTeleopMode();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivemotors.driveWithJoystick();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivemotors.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
