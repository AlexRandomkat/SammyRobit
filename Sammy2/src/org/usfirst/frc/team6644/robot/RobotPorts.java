package org.usfirst.frc.team6644.robot;

public enum RobotPorts {
	// Human Input
	JOYSTICK(1),

	// Motor Control
	LEFT_DRIVE_PWM(0), RIGHT_DRIVE_PWM(0);

	// Sensors

	private int portNumber;

	private RobotPorts(int portNumber) {
		this.portNumber = portNumber;
	}

	public int get() {
		return portNumber;
	}
}
