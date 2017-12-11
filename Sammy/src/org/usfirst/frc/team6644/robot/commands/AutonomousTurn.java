package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 * incomplete
 */
public class AutonomousTurn extends Command {
	private DriveMotors drivemotors = new DriveMotors();
	private double left = 0;
	private double right = 0;
	private double time = 0;

	public AutonomousTurn(double left, double right, double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivemotors);
		this.left = left;
		this.right = right;
		this.time = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(time);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivemotors.updateDrive(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		drivemotors.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
