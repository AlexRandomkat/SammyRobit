package org.usfirst.frc.team6644.robot.subsystems;

import org.usfirst.frc.team6644.robot.RobotPorts;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

public class IRSensor extends Subsystem {
	private AnalogInput sensor;
	protected static final double closeRange = 1.5;
	public IRSensor(int port){
		sensor = new AnalogInput(port);
	}

	public double getVoltage() {
		return sensor.getVoltage();
	}

	public double distanceValue() {
		return 1 / getVoltage();
	}

	public boolean isClose() {
		if (distanceValue() < closeRange) {
			return true;
		}
		return false;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
