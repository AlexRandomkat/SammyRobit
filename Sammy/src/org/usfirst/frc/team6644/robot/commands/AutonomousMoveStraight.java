package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *incomplete
 */
public class AutonomousMoveStraight extends Command {
	private static DriveMotors drivemotors = new DriveMotors();
	private static double motorSpeed;
    public AutonomousMoveStraight(double distance,double time) {
        // Use requires() here to declare subsystem dependencies
        requires(drivemotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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