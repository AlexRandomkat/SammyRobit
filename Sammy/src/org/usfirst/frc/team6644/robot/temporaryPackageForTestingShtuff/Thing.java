package org.usfirst.frc.team6644.robot.temporaryPackageForTestingShtuff;

import java.util.ArrayList;

/**
 * A class for representing things that the robot should try not to run into.
 * 
 * @author Alex Randomkat
 *
 */

/*
 * TODO: Create continuity checker by seeing if all Cartesian coordinates in a
 * Thing are endpoints of a line,
 */
/*
 * TODO: Create a Thing merger to combine Things that share a side
 */

public class Thing {
	private static double linkingDistance;
	private static double mappedDistance;
	private ArrayList<Cartesian> points;
	private ArrayList<LinearInequality> thingBoundaries = new ArrayList<LinearInequality>();
	boolean bound = false; // this is for keeping track of whether or not the object has been closed, i.e.
							// fully scanned by the robot.
	boolean isLocked = false; // This is for keeping track of whether or not the object has been scanned in
								// sufficient resolution to consider ignoring it.

	/**
	 * A constructor for a Thing.
	 * 
	 * @param points
	 *            = An ArrayList of Cartesian coordinates that is sorted such that
	 *            each subsequent coordinate is linked to the prior, with the
	 *            exception of the first.
	 */
	public Thing(ArrayList<Cartesian> points) {
		this.points = points;
		LinearInequality temp;
		for (int i = 1; i < points.size(); i++) {
			temp = new LinearInequality(points.get(i - 1), points.get(i), true);

			// makes sure interior of object is pointing in the correct direction
			if (temp.isInRange(WorldMap.getInstance().getPosition())) {
				thingBoundaries.add(new LinearInequality(points.get(i - 1), points.get(i), false));
			} else {
				thingBoundaries.add(temp);
			}
		}

		// test if first and last element of points are linked
		if (points.size() > 2 && testForLinking(points.get(0), points.get(points.size() - 1))) {
			// if so, link them
			temp = new LinearInequality(points.get(0), points.get(points.size() - 1), true);
			if (temp.isInRange(WorldMap.getInstance().getPosition())) {
				thingBoundaries.add(new LinearInequality(points.get(0), points.get(points.size() - 1), false));
			} else {
				thingBoundaries.add(temp);
			}

			// record that the Thing is closed. :)
			bound = true;
		}

		/*
		 * Anatomy of thingBoundaries: Each element i of thingBoundaries represents the
		 * line between points.get(i-1) and points.get(i). If
		 * thingBoundaries.get(points.size()-1) exists, then it is the line between
		 * points.get(0) and points.get(points.size()-1).
		 */
	}

	/**
	 * Sets the linking distance. If two points are within the linking distance of
	 * one another, they will be considered part of the same Thing. It is a good
	 * idea to set this a bit larger than the width of the robot.
	 * 
	 * @param distance
	 *            = linkingDistance
	 */
	public static void setLinkingDistance(double distance) {
		linkingDistance = distance;
	}

	/**
	 * 
	 * @return The linking distance.
	 */
	public static double getLinkingDistance() {
		return linkingDistance;
	}

	/**
	 * Tests to see if two unspecified-type coordinates should be linked into the
	 * same Thing.
	 * 
	 * @param a
	 *            = a coordinate
	 * @param b
	 *            = a coordinate
	 * @return true if they are within linking distance of each other, false
	 *         otherwise.
	 */
	public static boolean testForLinking(Coordinate a, Coordinate b) {
		double distance = Coordinate.getDistance(a, b);
		if (distance <= linkingDistance) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * getter for points
	 * 
	 * @return ArrayList<Cartesian> points
	 */
	public ArrayList<Cartesian> getPoints() {
		return points;
	}

	/**
	 * method for merging two Things with adjacent sides
	 * 
	 * @param a
	 *            = a Thing
	 * @param b
	 *            = a Thing
	 * @param aSide
	 *            = the side number
	 * @return
	 */
	/*
	public static Thing mergeThings(Thing a, Thing b, int aSide, int bSide) {

	}*/
}
