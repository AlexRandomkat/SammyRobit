package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team6644.robot.RobotPorts;


public class UltrasonicSensor extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Ultrasonic ultra = new Ultrasonic(RobotPorts.FRONT_ULTRASONIC_PING.get(), RobotPorts.FRONT_ULTRASONIC_ECHO.get());

	public UltrasonicSensor() {
		ultra.setAutomaticMode(true);
	}

	public double getRangeInches() {
		return ultra.getRangeInches();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
