package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IRArray extends Subsystem {
	DigitalOutput latch;
	DigitalOutput clock;
	DigitalOutput data;
	int count = 0;

	public IRArray() {
		latch = new DigitalOutput(2);
		clock = new DigitalOutput(3);
		data = new DigitalOutput(5);
		data.set(true);
		clock.set(true);
	}

	public void setLow() {
		// count++;
		updateShiftRegister();
	}

	public void setHigh() {
		// out.set(true);
	}

	public void shiftOut(int val) {
		for (int i = 0; i < 8; i++) {
			data.set(((val & (1 << i)) == 1));
			clock.set(false);
			clock.set(true);
		}
	}

	public void updateShiftRegister() {
		// latch.set(true);
		// shiftOut(count);
		// latch.set(false);
		// clock.set(false);
		// Timer.delay(.5);
		// data.set(true);
		// clock.set(false);

		// data.set(true);
		// clock.set(true);
		// Timer.delay(.5);
		// data.set(false);
	}

	public void periodic() {
		
		count++;
		count %= 8;
		switch (count) {
		case 0: {
			data.set(true);
			clock.set(true);
			break;
		}
		default: {
			data.set(false);
			clock.set(false);
		}
		}
		//clock.set(false);
		//data.set(true);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
