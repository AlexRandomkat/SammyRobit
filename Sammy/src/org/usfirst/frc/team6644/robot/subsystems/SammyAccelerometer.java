package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;


public class SammyAccelerometer extends Subsystem{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	//set accelerometer range, don't do anything that causes acceleration >2g in autonomous.
	private static BuiltInAccelerometer rioAccelerometer = new BuiltInAccelerometer(Accelerometer.Range.k2G);//sets range to +- 2g
	
	private static double xTiltAngle = 0;
	private static double yTiltAngle = 0;
	private static double rotationAngle = 0;
	private static double xAcceleration = 0;
	private static double yAcceleration = 0;
	private static double velocityX = 0; // in m/s
	private static double velocityY = 0;
	private double halfPI = Math.PI/2;
	private static long referenceTime;

	public void calibrateTilt() {
		//finds the x and y tilt of the accelerometer
		// do this when the robot is not accelerating
		
		// TODO: check to make sure the getX() and getY() values are not backwards
		
		for (int i = 0; i < 400; i++) {
			//takes 400 measurements of the tilt
			xTiltAngle += Math.acos(rioAccelerometer.getX());//getX() is not divided by anything as getX() returns measurement in g-forces.
			yTiltAngle += Math.acos(rioAccelerometer.getY());
		}
		
		//take the average of measurements
		xTiltAngle = xTiltAngle / 400;
		yTiltAngle = yTiltAngle / 400;
		
		System.out.println("\n\nAccelerometer calibrated, tilt x=" + ((Math.PI / 2) - xTiltAngle) + ", tilt y="
				+ ((Math.PI / 2) - yTiltAngle));
	}
	public void setRotation(double angle) {
		rotationAngle=angle;
	}

	public void accelerometerUpdate() {
		//this should be called periodically to keep values updated
		
		// double check acceleration integration to prevent drifting of velocity value
		// TODO: find an average of left and right riemann sums when calculating velocity from acceleration.
		
		/*
		 * Derivation of equations found at:
		 * https://cdn.discordapp.com/attachments/381229987191062539/388434267392311296/photo.JPG
		 * https://cdn.discordapp.com/attachments/381229987191062539/388597058375909376/photo_1.JPG
		 */
		
		// check positive/negative direction
		// correct for tilt error
		//all acceleration measurements in g-forces; all direction measurents in radians
		xAcceleration = (rioAccelerometer.getX()/Math.cos(halfPI-xTiltAngle))+Math.tan(halfPI-xTiltAngle);//g not included because all measurements are in g-forces, and we can assume g is about 1.
		yAcceleration = (rioAccelerometer.getY()/Math.cos(halfPI-yTiltAngle))+Math.tan(halfPI-yTiltAngle);
		//correct for rotation around z-axis of accelerometer relative to robot
		xAcceleration = xAcceleration/Math.cos(rotationAngle);
		yAcceleration = yAcceleration/Math.cos(rotationAngle);
		
		//integrate acceleration to find velocity
		//check this, it probably leads to drift of velocity value
		velocityX += (System.nanoTime() - referenceTime) * Math.pow(10, -9) * xAcceleration;
		velocityY += (System.nanoTime() - referenceTime) * Math.pow(10, -9) * yAcceleration;
		referenceTime = System.nanoTime();
	}

	//methods for returning velocity below
	public double getVelocity() {
		//yay Pythagoras
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
	
	//methods for returning raw acceleration values below (meaning they are uncorrected and relative to the accelerometer, not the robot).
	//methods that have "9.80665 * something" in them return acceleration measurements in m/s instead of g-forces/
	public double getXG() {
		return rioAccelerometer.getX();
	}
	public double getYG() {
		return rioAccelerometer.getY();
	}
	public double getZG() {
		return rioAccelerometer.getZ();
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
		//use pythagorean theorem for 2-D vector addition
		return Math.sqrt(Math.pow(getXG(), 2) + Math.pow(getYG(), 2));
	}
	public double getAcceleration() {
		return 9.80665 * getAccelerationG();
	}
	
	//methods used for returning corrected acceleration values
	//Make sure to call accelerometerUpdate() beforehand to avoid drawing inaccurate values
	public double getAccelerationX() {
		return xAcceleration;
	}
	public double getAccelerationY() {
		return yAcceleration;
	}
	
	//other methods and stuff goes below here
	public void display() {
		//displays raw accelerometer values on the SmartDashboard
		SmartDashboard.putNumber("raw Y", rioAccelerometer.getY());
		SmartDashboard.putNumber("raw X", rioAccelerometer.getX());
		SmartDashboard.putNumber("raw Z", rioAccelerometer.getZ());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
