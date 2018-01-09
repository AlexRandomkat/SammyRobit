package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;
import org.usfirst.frc.team6644.robot.commands.abstractClasses.PIDCommandOptimizable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * incomplete
 */
public class AutonomousTurn extends PIDCommandOptimizable {
	private double degree = 0;
	
	public AutonomousTurn(double degree) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super(0.02, 0.0000102*Math.pow(360/degree,2), 0);
		requires(Robot.drivemotors);
		this.degree = degree;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivemotors.disableSafety();
		Robot.drivemotors.resetGyro();
		setSetpoint(degree);
		getPIDController().setPercentTolerance(0.5);// sets to 0.5% tolerance
		getPIDController().setToleranceBuffer(10);// takes average of 10 measurements when determining if error is within tolerance
		
		//PIDController automatically started when command is initialized, according to class docs
		
		//test stuff below:
		System.out.println(getPIDController().getSetpoint());
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		//PIDController is automatically stopped when the command is ended/interrupted
		isRunning=false;
		Robot.drivemotors.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drivemotors.getDegreesTotal();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Error", getPIDController().getAvgError());
		SmartDashboard.putNumber("Output", output);
		
		//limit outputs to between -1 and 1, inclusive.
		if(Math.abs(output)<=1) {
			Robot.drivemotors.arcadeDrive(0,-output);
		}else if(output<0) {
			Robot.drivemotors.arcadeDrive(0,1);
		}else {
			Robot.drivemotors.arcadeDrive(0,-1);
		}
	}
	
	@Override
	protected void requiresStatement() {
		requires(Robot.drivemotors);
	}
}
