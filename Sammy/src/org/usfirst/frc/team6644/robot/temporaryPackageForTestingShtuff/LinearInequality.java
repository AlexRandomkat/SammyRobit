package org.usfirst.frc.team6644.robot.temporaryPackageForTestingShtuff;

/**
 * A class for representing linear inequalities.
 * 
 * @author Alex Randomkat
 *
 */
public class LinearInequality {
	private double constant;
	private double coefficient;
	private double[] domain = new double[2];
	private boolean greaterThan;
	private boolean isLocked;

	/**
	 * Creates a linear inequality.
	 * 
	 * @param constant
	 *            = the b term in f(x)=ax+b
	 * @param coefficient
	 *            = the a term in f(x)=ax+b
	 * @param domain
	 *            = domain of f(x)
	 * @param greaterThan
	 *            = true for y>f(x) or false for y<f(x)
	 * @throws InvalidFunctionDomain
	 */
	public LinearInequality(double coefficient, double constant, double[] domain, boolean greaterThan)
			throws InvalidFunctionDomain {
		this.constant = constant;
		this.coefficient = coefficient;
		this.greaterThan = greaterThan;
		if (domain[0] > domain[1]) {
			throw new InvalidFunctionDomain("Check domain of linear inequality");
		}
		this.domain[0] = domain[0];
		this.domain[1] = domain[1];
	}

	/**
	 * custom error for use in LinearInequality when an invalid domain is put in
	 * when creating an instance of this class
	 * 
	 * @author Alex Randomkat
	 *
	 */
	public static class InvalidFunctionDomain extends Exception {
		private static final long serialVersionUID = 1L;// no idea what this thing is. I only put it here because the
														// IDE told me to. :/
		// TODO ^^^read about this thingy

		/**
		 * See "Exception(String message)"
		 * 
		 * @param message
		 *            = a String
		 */
		InvalidFunctionDomain(String message) {
			super(message);
		}
	}

	/**
	 * Creates a linear inequality spanning above or below the line connecting two
	 * Cartesian coordinates.
	 * 
	 * @param a
	 *            = a Cartesian coordinate
	 * @param b
	 *            = a Cartesian coordinate
	 * @param greaterThan
	 *            = true for y>f(x) or false for y<f(x)
	 */
	public LinearInequality(Cartesian a, Cartesian b, boolean greaterThan) {
		coefficient = (a.y() - b.y()) / (a.x() - b.x());
		constant = a.y() - coefficient * a.x();
		domain[0] = Math.min(a.x(), b.x());
		domain[1] = Math.max(a.x(), b.x());
		this.greaterThan = greaterThan;
	}

	/**
	 * Checks if x is within the domain of f(x)
	 * 
	 * @param x
	 *            = some double
	 * @return true if x is within the domain of f(x), false otherwise.
	 */
	public boolean isInDomain(double x) {
		if (x >= domain[0] && x <= domain[1]) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if c satisfies the LinearInequality without regard to the domain of
	 * f(x)
	 * 
	 * @param c
	 *            = a Cartesian coordinate
	 * @return
	 */
	public boolean isInRange(Cartesian c) {
		if (greaterThan) {
			if (c.y() >= function(c.x())) {
				return true;
			} else {
				return false;
			}
		} else {
			if (function(c.x()) >= c.y()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evaluates f(x) without regard to the domain of f
	 * 
	 * @param x
	 *            = some double
	 * @return the output of f(x)
	 */
	public double function(double x) {
		return coefficient * x + constant;
	}

	/**
	 * evaluates f-1(x), i.e. the inverse of f for some number x, without regard to
	 * the domain of f-1(x)
	 * 
	 * @param x
	 *            = some double
	 * @return the output of f-1(x)
	 */
	public double inverseFunction(double y) {
		return (y - constant) / coefficient;
	}

	/**
	 * Evaluates whether a coordinate satisfies the inequality, taking into account
	 * the domain of f(x)
	 * 
	 * @param c
	 *            = some Cartesian
	 * @return true if the coordinate satisfies the inequality, false otherwise.
	 */
	public boolean withinBounds(Cartesian c) {
		if (isInDomain(c.x())) {
			if (greaterThan) {
				if (c.y() >= function(c.x())) {
					return true;
				} else {
					return false;
				}
			} else {
				if (function(c.x()) >= c.y()) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Setter for isLocked. isLocked signifies whether or not the space covered by
	 * the linear function has been scanned in sufficient resolution.
	 * 
	 * @param b
	 *            = isLocked
	 */
	public void setLocked(boolean b) {
		isLocked = b;
	}

	/**
	 * getter for isLocked
	 * 
	 * @return isLocked
	 */
	public boolean getIsLocked() {
		return isLocked;
	}
}
