package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousTest extends Command {
	private DriveMotors drivemotors = new DriveMotors();
	private double leftMotor;
	private double rightMotor;
	private double timeInSeconds;
    public AutonomousTest(double leftMotor, double rightMotor, double timeInSeconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivemotors);
    	this.leftMotor=leftMotor;
    	this.rightMotor=rightMotor;
    	this.timeInSeconds=timeInSeconds;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	drivemotors.enableSaftey();
    	setTimeout(timeInSeconds);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivemotors.updateDrive(leftMotor,rightMotor);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//ends 
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivemotors.updateDrive(0,0);
    	drivemotors.disableSafety();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
