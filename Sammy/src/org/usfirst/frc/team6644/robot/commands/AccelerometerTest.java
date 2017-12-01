package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.subsystems.RoboRioAccelerometer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AccelerometerTest extends Command {
	RoboRioAccelerometer accel = new RoboRioAccelerometer();
	private int count = 0;

	public AccelerometerTest() {
		// Use requires() here to declare subsystem dependencies
		requires(accel);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		count++;
		count %= 50;
		if (count == 0) {
			System.out.println("X:" + accel.getAccelerationX() + " Y:" + accel.getAccelerationY());
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