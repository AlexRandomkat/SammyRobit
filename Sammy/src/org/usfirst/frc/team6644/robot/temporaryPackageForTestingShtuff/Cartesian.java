package org.usfirst.frc.team6644.robot.temporaryPackageForTestingShtuff;

import java.util.ArrayList;

/**
 * A class for representing Cartesian coordinates
 * 
 * @author Alex Randomkat
 *
 */
public class Cartesian extends Coordinate {
	private double x;
	private double y;
	private boolean lineLinked = false;
	public int linkedBy = -1;
	public int linkTo = -1;
	public static final Cartesian origin = new Cartesian(0, 0);

	/**
	 * @param x
	 *            = the x-coordinate of the Cartesian (x,y)
	 * @param y
	 *            = the y-coordinate of the Cartesian (x,y)
	 */
	public Cartesian(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return The x-coordinate of the Cartesian (x,y).
	 */
	public double x() {
		return x;
	}

	/**
	 * @return The y-coordinate of the Cartesian (x,y).
	 */
	public double y() {
		return y;
	}

	/**
	 * Setter for lineLinked. lineLinked is to keep track of whether a Cartesian
	 * coordinate is part of an endpoint of a LinearInequality.
	 * 
	 * @param b
	 *            = lineLinked
	 */
	public void setLineLinked(boolean b) {
		lineLinked = b;
	}

	/**
	 * Getter for lineLinked.
	 * 
	 * @return lineLinked
	 */
	public boolean getLineLinked() {
		return lineLinked;
	}

	/**
	 * uses the Pythagorean theorem to find distance between two Cartesian
	 * coordinates in a 2-dimensional coordinate system
	 * 
	 * @param a
	 *            = a Cartesian coordinate / a point
	 * @param b
	 *            = a Cartesian coordinate / a point
	 * @return the distance between the two points
	 */
	public static double getDistance(Cartesian a, Cartesian b) {
		return Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2));
	}

	/**
	 * returns direction to Cartesian b from a in radians.
	 * 
	 * @param a
	 *            = a Cartesian coordinate / a point
	 * @param b
	 *            = a Cartesian coordinate / a point
	 * @return the direction from point a to point b in radians
	 */
	public static double getDirectionInRadians(Cartesian a, Cartesian b) {
		return Math.atan2(b.y() - a.y(), b.x() - a.x());
	}

	/**
	 * Converts a Cartesian coordinate to a Polar coordinate
	 * 
	 * @param a
	 *            = a Cartesian coordinate
	 * @return a Polar coordinate
	 */
	public static Polar getPolarCoordinate(Cartesian a) {
		return new Polar(getDistance(origin, a), getDirectionInRadians(origin, a));
	}

	/**
	 * Converts this Cartesian coordinate to a Polar coordinate
	 * 
	 * @return a Polar coordinate
	 */
	public Polar getPolarCoordinate() {
		return new Polar(getDistance(origin, this), getDirectionInRadians(origin, this));
	}

	/**
	 * Overrides Object.equals(Object object), as if two Cartesians are represent
	 * the same coordinate, then they are the same thing.
	 */
	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof Cartesian) {
			return x == ((Cartesian) object).x() && y == ((Cartesian) object).y();
		} else {
			return false;
		}
	}

	/**
	 * Override Object.hashCode() to fit with definition due to changes in equals.
	 */
	@Override
	public int hashCode() {
		return (int) Math.round(x * 8192 + y * 8192);
	}
}
