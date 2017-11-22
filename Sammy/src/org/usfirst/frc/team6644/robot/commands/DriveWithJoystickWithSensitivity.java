package org.usfirst.frc.team6644.robot.commands;

/**
 *
 */
public class DriveWithJoystickWithSensitivity extends DriveWithJoystick {

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double forwardModifier = 1-Math.abs(joystick.getY());
		left=forwardModifier*joystick.getX()-joystick.getY();
		right=forwardModifier*joystick.getX()+joystick.getY();
		double sensitivity=(-joystick.getRawAxis(3)+1)/2;
		left=left*sensitivity;
		right=right*sensitivity;
		/* get the stuff below working properly instead of just multiplying...
		left=Math.log10(left)/Math.log10(sensitivity);
		right=Math.log10(right)/Math.log10(sensitivity);
		*/
		drivemotors.updateDrive(left,right);
	}
}
