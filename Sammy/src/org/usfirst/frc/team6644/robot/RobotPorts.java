package org.usfirst.frc.team6644.robot;

public enum RobotPorts {
	
	//Laptop USB Ports
	JOYSTICK(0),CONTROLLER(1),

	// PWM Ports
	LEFT_DRIVE_PWM(0), RIGHT_DRIVE_PWM(1), CLIMB_DRIVE_PWM(2),

	// Analog Ports
	FRONT_IR(0), LEFT_IR(2), RIGHT_IR(3),
	
	//Digital Ports
	FRONT_ULTRASONIC_PING(0), FRONT_ULTRASONIC_ECHO(1);
	
	private int portNumber;

	private RobotPorts(int portNumber) {
		this.portNumber = portNumber;
	}

	public int get() {
		return portNumber;
	}
}
