package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team6644.robot.RobotPorts;

/**
 * 
 */
public class DriveMotors extends Subsystem{
	private static Spark leftDrivePWM;
	private static Spark rightDrivePWM;
	private static ADXRS450_Gyro gyro;
	private static RobotDrive drive;
	private double motorSafteyExpireTime = 0.3;// sets the PWM to expire in 0.3 seconds after the last call of .Feed()

	public DriveMotors() {
		leftDrivePWM = new Spark(RobotPorts.LEFT_DRIVE_PWM.get());
		rightDrivePWM = new Spark(RobotPorts.RIGHT_DRIVE_PWM.get());

		drive = new RobotDrive(leftDrivePWM, rightDrivePWM);

		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
	}

	// methods for drive motors
	public void enableSaftey() {
		drive.setSafetyEnabled(true);
		drive.setExpiration(motorSafteyExpireTime);
	}

	public void disableSafety() {
		drive.setSafetyEnabled(false);
		drive.setExpiration(motorSafteyExpireTime);
	}

	public void arcadeDrive(double speed, double angle) {
		drive.arcadeDrive(speed, angle);
	}

	public void arcadeDrive(GenericHID stick) {
		drive.arcadeDrive(stick);
	}

	public void tankDrive(double left, double right) {
		// left and right should be double values at/between -1 and 1.

		// Use enableSaftey for turning on drive motor safety. Not much sense in turning
		// safety on in one motor but not the other.

		// DO NOT HAVE MOTOR INPUTS GREATER IN MAGNITUDE THAN 1
		if (Math.abs(left) > 1 || Math.abs(right) > 1) {
			if (left > 1) {
				if (right > 1) {
					left = 1;
					right = 1;
				} else if (right < -1) {
					left = 1;
					right = -1;
				} else {
					left = 1;
				}
			} else if (left < -1) {
				if (right > 1) {
					left = -1;
					right = 1;
				} else if (right < -1) {
					left = -1;
					right = -1;
				} else {
					left = -1;
				}
			} else {
				if (right > 1) {
					right = 1;
				} else {
					right = -1;
				}
			}
			drive.tankDrive(left, right);
			System.out.println("DANGER: MOTOR OUTPUTS ARE GREATER IN MAGNITUDE THAN 1");
		} else {
			drive.tankDrive(left, right);
		}

		// old code below
		// leftDrivePWM.set(left);
		// rightDrivePWM.set(-right);//accounts for flipped orientation of motors
	}

	public void stop() {
		disableSafety();
		drive.tankDrive(0, 0);
	}

	// methods for gyro
	public void resetGyro() {
		gyro.reset();
		System.out.println("Gyro reset");
	}

	public double getDegrees() {
		return gyro.getAngle() % 360;
	}

	public double getDegreesTotal() {
		return gyro.getAngle();
	}

	public double getRadians() {
		return getDegrees() * Math.PI / 180;
	}

	public double getRadiansTotal() {
		return getDegreesTotal() * Math.PI / 180;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
