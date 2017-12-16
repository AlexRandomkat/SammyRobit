package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * incomplete
 */
public class AutonomousTurn extends PIDCommand {
	private double speed = 0;
	private double degree = 0;
	private double sign;

	// test stuff
	private int count = 0;

	public AutonomousTurn(double speed, double degree) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super(0.3, 0, 0);
		requires(Robot.drivemotors);
		this.speed = speed;
		this.degree = degree;
		if (degree >= 0) {
			sign = 1;
		} else {
			sign = -1;
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivemotors.disableSafety();
		Robot.drivemotors.resetGyro();
		getPIDController().setSetpoint(degree);
		getPIDController().setPercentTolerance(10);// sets to 10% tolerance
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
		count++;
		count %= 50;
		if (count == 0) {
			System.out.println("Output: " + sign*output + "\tError: " + getPIDController().getAvgError());
		}
		Robot.drivemotors.arcadeDrive(sign*speed*output,1);
	}
}
