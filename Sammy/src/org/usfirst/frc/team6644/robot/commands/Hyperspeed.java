package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

/**
 *
 */
public class Hyperspeed extends Command {
	private static DriveMotors drivemotors = new DriveMotors();
	private static Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());

	public Hyperspeed() {
		requires(drivemotors);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drivemotors.enableSaftey();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double forwardModifier = 1 - Math.abs(joystick.getX());
		double left = forwardModifier * joystick.getX() + -joystick.getY();
		double right = forwardModifier * joystick.getX() - -joystick.getY();
		// HYPERSPEEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		if (left >= 0) {
			if (right >= 0) {
				drivemotors.updateDrive(1, 1);
			} else {
				drivemotors.updateDrive(1, -1);
			}
		} else {
			if (right >= 0) {
				drivemotors.updateDrive(-1, 1);
			} else {
				drivemotors.updateDrive(-1, -1);
			}
		}
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
