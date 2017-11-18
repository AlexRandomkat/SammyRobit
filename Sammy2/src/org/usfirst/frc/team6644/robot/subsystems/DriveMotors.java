package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team6644.robot.RobotPorts;

/**
 *
 */
public class DriveMotors extends Subsystem {
	static Spark leftDrivePWM = new Spark(RobotPorts.LEFT_DRIVE_PWM.get());
	static Spark rightDrivePWM = new Spark(RobotPorts.RIGHT_DRIVE_PWM.get());
	double motorSafteyExpireTime = 0.2;// sets the PWM to expire in 0.2 seconds after the last call of .Feed()

	public void enableSaftey() {
		leftDrivePWM.setSafetyEnabled(true);
		leftDrivePWM.setExpiration(motorSafteyExpireTime);
		rightDrivePWM.setSafetyEnabled(true);
		rightDrivePWM.setExpiration(motorSafteyExpireTime);// see comment above
	}

	public void disableSafety() {
		leftDrivePWM.setSafetyEnabled(false);
		rightDrivePWM.setSafetyEnabled(false);
	}

	public void updateDrive(double left, double right) {
		// left and right should be double values at/between -1 and 1.

		// Use enableSaftey for turning on drive motor safety. Not much sense in turning
		// safety on in one motor but not the other.
		if (leftDrivePWM.isSafetyEnabled()) {
			leftDrivePWM.Feed();
			rightDrivePWM.Feed();
		}
		leftDrivePWM.set(left);
		rightDrivePWM.set(right);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
