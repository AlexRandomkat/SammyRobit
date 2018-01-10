package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ForceSensor extends Subsystem {
	protected AnalogInput sensor = new AnalogInput(1);
	public double getVoltage() {
		return sensor.getVoltage();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
