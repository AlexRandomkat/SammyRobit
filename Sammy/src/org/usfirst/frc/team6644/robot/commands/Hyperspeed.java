package org.usfirst.frc.team6644.robot.commands;
/**
 *
 */
public class Hyperspeed extends DriveWithJoystick {
	public Hyperspeed() {
		super();
	}
	protected void calculateMotorOutputs() {
		super.calculateMotorOutputs();
		
		// HYPERSPEEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		if (left >= 0) {
			if (right >= 0) {
				left=1;
				right=1;
			} else {
				left=1;
				right=-1;
			}
		} else {
			if (right >= 0) {
				left=-1;
				right=1;
			} else {
				left=-1;
				right=-1;
			}
		}
	}
}
