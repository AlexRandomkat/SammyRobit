package org.usfirst.frc.team6644.robot.commands;
/**
 *
 */
public class DriveWithJoystickWithSensitivity extends DriveWithJoystick {
	public DriveWithJoystickWithSensitivity() {
		super();
	}
	private double sensitivity;
	// Called repeatedly when this Command is scheduled to run
	protected void calculateMotorOutputs() {
		super.calculateMotorOutputs();
		
		sensitivity=(-joystick.getRawAxis(3)+1)/2;
		left=left*sensitivity;
		right=right*sensitivity;
	}
}
