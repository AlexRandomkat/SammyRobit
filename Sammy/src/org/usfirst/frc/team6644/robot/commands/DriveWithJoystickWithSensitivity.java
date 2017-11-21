package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class DriveWithJoystickWithSensitivity extends Command {
	private static DriveMotors drivemotors = new DriveMotors();
	private static Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());

	public DriveWithJoystickWithSensitivity() {
		requires(drivemotors);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drivemotors.enableSaftey();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Assumes "the typical convention for joysticks and gamepads is for Y to be
		// negative as they joystick is pushed away from the user ... and for X to be
		// positive as the joystick is pushed to the right."
		// ^^^Check this assumption in the Driver Station before deploying code.
		double forwardModifier = 1-Math.abs(joystick.getY());
		double left=forwardModifier*joystick.getX()-joystick.getY();
		double right=forwardModifier*joystick.getX()+joystick.getY();
		double sensitivity=(-joystick.getRawAxis(3)+1)/2;
		left=left*sensitivity;
		right=right*sensitivity;
		/* get the stuff below working properly instead of just multiplying...
		left=Math.log10(left)/Math.log10(sensitivity);
		right=Math.log10(right)/Math.log10(sensitivity);
		*/
		drivemotors.updateDrive(left,right);//placeholder
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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
