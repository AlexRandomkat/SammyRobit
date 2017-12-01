package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

/**
 *
 */
public class RoboRioAccelerometer extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private static BuiltInAccelerometer rioAccelerometer = new BuiltInAccelerometer();
	private static double velocityX = 0; // in m/s
	private static double velocityY = 0;
	private static long referenceTime;

	public void accelerometerUpdate() {
		// double check acceleration integration to prevent drifting of velocity value
		// check positive/negative direction
		double xAcceleration = 9.80665 * rioAccelerometer.getX();
		double yAcceleration = 9.80665 * rioAccelerometer.getY();// multiplied by 9.80665 to convert g-forces to m/s^2
		velocityX += (System.nanoTime() - referenceTime) * Math.pow(10, -9) * xAcceleration;
		velocityY += (System.nanoTime() - referenceTime) * Math.pow(10, -9) * yAcceleration;
		referenceTime = System.nanoTime();
	}

	public double getVelocity() {
		return Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
	}

	public double getVelocityDirection() {
		// returns direction in radians from x-axis
		if (velocityX <= 0) {
			return Math.atan2(velocityY, velocityX);
		} else {
			return Math.PI + Math.atan2(velocityY, velocityX);
		}
	}

	public double getAccelerationX() {
		//return 9.80665 * Math.sqrt(Math.pow(rioAccelerometer.getX(), 2) + Math.pow(rioAccelerometer.getY(), 2));
		return rioAccelerometer.getX();
	}
	public double getAccelerationY() {
		//return 9.80665 * Math.sqrt(Math.pow(rioAccelerometer.getX(), 2) + Math.pow(rioAccelerometer.getY(), 2));
		return rioAccelerometer.getY();
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
