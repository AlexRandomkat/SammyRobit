package org.usfirst.frc.team6644.robot.temporaryPackageForTestingShtuff;

import java.util.ArrayList;

/**
 * A class for representing the world around the robot.
 * 
 * Goal of robot: Explore world until either memory runs out or all Things are
 * sufficiently mapped. If all things are sufficiently mapped out, just wander
 * around to impress onlookers.
 * 
 * @author Alex Randomkat
 *
 */

/*
 * TODO: Use sorted Cartesian coordinates to find each's nearest coordinate
 * within linkingDistance, once loops around or breaks, give all current
 * coordinates in loop to a Thing. x^2+y^2<=linkingDistance^2. Yay Pythagoras.
 */
public final class WorldMap {
	private static WorldMap instance;
	private double bufferDistance;
	private ArrayList<Thing> avoidThese;
	private ArrayList<Cartesian> points; // for temporarily storing outputs from lidar
	private ArrayList<Cartesian> notThings; // for storing lidar outputs that cannot be classified as any one object
											// yet. Poor orphans.
	int implementPings = 0;
	private Cartesian position;

	private WorldMap() {
		avoidThese = new ArrayList<Thing>();
		points = new ArrayList<Cartesian>();
		notThings = new ArrayList<Cartesian>();
		position = new Cartesian(0, 0);
	}

	public static WorldMap getInstance() {
		if (instance == null) {
			instance = new WorldMap();
		}
		return instance;
	}

	/**
	 * Sorts an ArrayList of Cartesians from lowest x-coordinate to highest
	 * x-coordinate value. Uses bubblesort, as coordinates should already be mostly
	 * sorted. May change to mergesort later if sorting time becomes too large.
	 * 
	 * @param in
	 *            = ArrayList<Cartesian> to be sorted
	 * @return An ArrayList<Cartesian> that is sorted according to the description.
	 */
	public ArrayList<Cartesian> sortCartesiansByX(ArrayList<Cartesian> in) {
		boolean notSorted = true;
		Cartesian temp;

		// bubblesort thingy
		while (notSorted) {
			notSorted = false;
			for (int i = 1; i < in.size(); i++) {
				if (in.get(i - 1).x() > in.get(i).x()) {
					temp = in.get(i - 1);
					in.remove(i - 1);
					in.add(i, temp);
					notSorted = true;
				}
			}
		}
		return in;
	}

	/**
	 * A setter method for points. Also prepares points to be made into Things.
	 * 
	 * @param in
	 *            = an ArrayList of Cartesians
	 */

	/**
	 * makes Things using Cartesian coordinates in points
	 */
	public void makeThingsFromRecent(ArrayList<Cartesian> in) {
		in = sortCartesiansByX(in);

		boolean withinRange;
		boolean changeWithinRange;
		ArrayList<ArrayList<Cartesian>> thingPoints = new ArrayList<ArrayList<Cartesian>>();
		ArrayList<Cartesian> temp = new ArrayList<Cartesian>();
		int counter;
		int start = 0;
		double smallestDistance = Thing.getLinkingDistance();
		for (int i = 0; i < in.size(); i++) {
			counter = -i;
			withinRange = true;
			changeWithinRange = false;

			// find possible points to link with and place in temp
			while (withinRange) {
				if (i + counter < in.size()) {
					if (Math.abs(in.get(i + counter).x() - in.get(i).x()) <= Thing.getLinkingDistance()
							&& counter != 0) {
						if (!changeWithinRange) {
							start = i + counter;
						}
						temp.add(in.get(i + counter));
						changeWithinRange = true;
					} else {
						if (changeWithinRange) {
							withinRange = false;
						}
					}
					counter++;
				} else {
					withinRange = false;
				}
			}

			// find closest point in temp. If there is nothing in temp, that means the point
			// is an orphan...
			if (temp.size() != 0) {
				withinRange = false;
				// find closest point within a distance of linkedDistance;
				for (int ind = 0; ind < temp.size(); ind++) {
					if (Cartesian.getDistance(in.get(i), temp.get(ind)) < smallestDistance
							&& temp.get(ind).linkedBy != i) {
						smallestDistance = Cartesian.getDistance(in.get(i), temp.get(ind));
						counter = ind;
						withinRange = true;
					}
				}

				// if withinRange is still false, then the point is an orphan or end of a Thing.
				// Otherwise, do shtuff.
				if (withinRange) {
					in.get(i).linkTo = i + start + counter;
					in.get(i + start + counter).linkedBy = i;
				}
			}
		}
	}

	public Cartesian getPosition() {
		return position;
	}

	public void recalibratePosition() {
		/*
		 * put code in here for recalibrating position on map using scanned objects
		 * 
		 */
	}
}
