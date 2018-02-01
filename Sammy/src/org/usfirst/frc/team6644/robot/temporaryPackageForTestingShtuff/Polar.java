package org.usfirst.frc.team6644.robot.temporaryPackageForTestingShtuff;

/**
 * A class for representing Polar coordinates
 * 
 * @author Alex Randomkat
 *
 */
public class Polar extends Coordinate {
	private double r;
	private double theta;// should be in radians.

	/**
	 * 
	 * @param distance
	 *            = The distance of the polar coordinate from the origin.
	 * @param directionInRadians
	 *            = The direction of the polar coordinate from the origin in
	 *            radians.
	 */
	public Polar(double distance, double directionInRadians) {
		this.r = distance;
		this.theta = directionInRadians;
	}

	/**
	 * @return The distance of the coordinate from the origin
	 */
	public double r() {
		return r;
	}

	/**
	 * @return The direction of the coordinate from the origin in radians
	 */
	public double theta() {
		return theta;
	}

	/**
	 * Gets the distance between two polar coordinates. Currently just transforms
	 * them into rectangular coordinates and calculates the distance from there,
	 * because I'm lazy.
	 * 
	 * @param a
	 *            = a Polar coordinate
	 * @param b
	 *            = a Polar coordinate
	 * @return The distance between a and b
	 */
	public static double getDistance(Polar a, Polar b) {
		// because I'm too lazy to do any trig. Maybe I'll change this later.
		return Cartesian.getDistance(getCartesianCoordinate(a), getCartesianCoordinate(b));
	}

	/**
	 * Converts a polar coordinate into a Cartesian coordinate
	 * 
	 * @param a
	 *            = a Polar coordinate
	 * @return A Cartesian coordinate
	 */
	public static Cartesian getCartesianCoordinate(Polar a) {
		return new Cartesian(a.r() * Math.cos(a.theta()), a.r() * Math.sin(a.theta()));
	}

	/**
	 * Converts this polar coordinate into a Cartesian coordinate
	 * 
	 * @return A Cartesian coordinate
	 */
	public Cartesian getCartesianCoordinate() {
		return new Cartesian(r * Math.cos(theta), r * Math.sin(theta));
	}
}
