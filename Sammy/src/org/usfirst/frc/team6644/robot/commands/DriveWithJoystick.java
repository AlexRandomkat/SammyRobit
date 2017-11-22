package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class DriveWithJoystick extends Command {
	private static DriveMotors drivemotors = new DriveMotors();
	private static Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());

	public DriveWithJoystick() {
		requires(drivemotors);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drivemotors.enableSaftey();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double forwardModifier = 1 - Math.abs(joystick.getY());
		drivemotors.updateDrive(forwardModifier * joystick.getX() + -joystick.getY(),
				forwardModifier * joystick.getX() - -joystick.getY()); // I'm pretty sure it's impossible for any of the
																		// left or right PWM inputs to be out of the
																		// range of [-1,1], double check that.
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivemotors.updateDrive(0, 0);
		drivemotors.disableSafety();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
}
