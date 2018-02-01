package org.usfirst.frc.team6644.robot.temporaryPackageForTestingShtuff;

/**
 * A class for wrapping cartesian and polar coordinates.
 * 
 * @author Alex Randomkat
 *
 */
public class Coordinate {

	public Coordinate() {

	}

	/**
	 * A method for getting distances between two coordinates if you are unsure of
	 * whether they are polar or cartesian. Please don't use this if it's not
	 * necessary. It's there, but don't do it.
	 * 
	 * @param a
	 *            = some Coordinate
	 * @param b
	 *            = some Coordinate
	 * @return the distance between the two Coordinates.
	 */
	public static double getDistance(Coordinate a, Coordinate b) {
		if (!(a instanceof Cartesian && b instanceof Cartesian)) {
			if (a instanceof Cartesian == b instanceof Cartesian) {
				return Polar.getDistance(a, b);
			} else {
				if (a instanceof Cartesian) {
					return Cartesian.getDistance(a, Polar.getCartesianCoordinate((Polar) b));
				} else {
					return Cartesian.getDistance(Polar.getCartesianCoordinate((Polar) a), b);
				}
			}
		}
		return Cartesian.getDistance(a, b);
	}
}
