package org.usfirst.frc.team6644.robot.commands.abstractClasses;

import edu.wpi.first.wpilibj.command.PIDCommand;

abstract public class PIDCommandOptimizable extends PIDCommand {
	public boolean isRunning = false;
	public boolean interrupted = false;

	public PIDCommandOptimizable(double p, double i, double d) {
		super(p, i, d);
	}

	public PIDCommandOptimizable(double p, double i, double d, double period) {
		super(p, i, d, period);
	}

	public PIDCommandOptimizable(String name, double p, double i, double d) {
		super(name, p, i, d);
	}

	public PIDCommandOptimizable(String name, double p, double i, double d, double period) {
		super(name, p, i, d, period);
	}

	@Override
	abstract protected double returnPIDInput();

	@Override
	abstract protected void usePIDOutput(double output);

	/*
	 * call requires(Subsystem s) here again.
	 */
	abstract protected void requiresStatement();

	public void optimizePIDinitialize() {
		getPIDController().setPID(0, 0, 0);
		requiresStatement();
	}

	public void optimizePIDstart() {
		isRunning = true;
		initialize();
		isRunning = false;
	}

	public void changePIDCoefficients(double kP, double kI, double kD, double kF) {
		this.getPIDController().setPID(kP, kI, kD, kF);
	}

	public double optimizePIDinterrupted() {
		/*
		 * This is called whenever a PIDCommand is interrupted by OptimizePID due to the
		 * PIDCommand exceeding the alloted time to run.
		 */
		isRunning = false;
		double error = getPIDController().getError();
		double setpoint = getPIDController().getSetpoint();
		getPIDController().disable();
		interrupted();
		return error / setpoint;
	}
}
