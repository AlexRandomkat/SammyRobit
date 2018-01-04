package org.usfirst.frc.team6644.robot;

public enum RobotPorts {
	// Human Input
	JOYSTICK(0),CONTROLLER(1),

	// Motor Control
	LEFT_DRIVE_PWM(0), RIGHT_DRIVE_PWM(1), CLIMB_DRIVE_PWM(2),

	// Sensors
	FRONT_IR(0);
	private int portNumber;

	private RobotPorts(int portNumber) {
		this.portNumber = portNumber;
	}

	public int get() {
		return portNumber;
	}
}
