package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;


public class SammyAccelerometer extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private static BuiltInAccelerometer rioAccelerometer = new BuiltInAccelerometer();
	private static double xTiltAngle = 0;
	private static double yTiltAngle = 0;
	private static double rotationAngle = 0;
	private static double xAcceleration = 0;
	private static double yAcceleration = 0;
	private static double velocityX = 0; // in m/s
	private static double velocityY = 0;
	private static long referenceTime;

	public void calibrateTilt() {
		//finds the x and y tilt of the accelerometer
		
		rioAccelerometer.setRange(Accelerometer.Range.k2G);
		// do this when the robot is not accelerating
		// check to make sure the values are not backwards
		for (int i = 0; i < 400; i++) {
			xTiltAngle += Math.acos(rioAccelerometer.getX());
			yTiltAngle += Math.acos(rioAccelerometer.getY());
		}
		xTiltAngle = xTiltAngle / 400;
		yTiltAngle = yTiltAngle / 400;
		System.out.println("\n\nAccelerometer calibrated, tilt x=" + ((Math.PI / 2) - xTiltAngle) + ", tilt y="
				+ ((Math.PI / 2) - yTiltAngle));
	}
	public void setRotation(double angle) {
		rotationAngle=angle;
	}

	public void accelerometerUpdate() {
		// double check acceleration integration to prevent drifting of velocity value
		// do an average of left and right riemann sums

		// check positive/negative direction
		// correct for error
		xAcceleration = rioAccelerometer.getX() / Math.sin(xTiltAngle);
		yAcceleration = rioAccelerometer.getY() / Math.sin(yTiltAngle);

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
	
	
	

	public double getXG() {
		return rioAccelerometer.getX() / Math.sin(xTiltAngle);
	}

	public double getYG() {
		return rioAccelerometer.getY() / Math.sin(yTiltAngle);
	}

	public double getX() {
		return 9.80665 * getXG();
	}
	public double getZ() {
		return 9.80665 * rioAccelerometer.getZ();
	}

	public double getY() {
		return 9.80665 * getYG();
	}

	public double getAccelerationG() {
		return Math.sqrt(Math.pow(getXG(), 2) + Math.pow(getYG(), 2));
	}

	public double getAcceleration() {
		return 9.80665 * getAccelerationG();
	}
	
	
	
	
	public void display() {
		SmartDashboard.putNumber("raw Y", rioAccelerometer.getY());
		SmartDashboard.putNumber("raw x", rioAccelerometer.getX());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
