package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.commands.abstractClasses.PIDCommandOptimizable;

import edu.wpi.first.wpilibj.Timer;

/*
 * Made because hand-tuning PID loops is really annoying.
 */
public class OptimizePID extends Command {
	private PIDCommandOptimizable toBeOptimized;
	private Timer timer;
	private double targetTime; // target time for PID loop to finish executing
	private double timeLimit; // timeTolerance*targetTime is the alloted time for the PID loop to finish
								// executing before it is ended.
	private double kP;
	private double kI;
	private double kD;
	private double kF=0;
	private double runtime; // stores runtime of a PID loop
	private double errorRatio; // stores leftover error of PID loop divided by the setpoint
	private double setback;// accounts for overshoot after PID loop ends

	public OptimizePID(PIDCommandOptimizable toBeOptomized, double targetTimeInSeconds, double timeTolerance) {
		this.toBeOptimized = toBeOptomized;

		// I may end up deleting these two variables if I don't use them outside of the
		// constructor.
		this.targetTime = targetTimeInSeconds;
		this.timeLimit = timeTolerance * targetTimeInSeconds;
	}
	
	public OptimizePID(PIDCommandOptimizable toBeOptomized, double Kf, double targetTimeInSeconds, double timeTolerance) {
		this.toBeOptimized = toBeOptomized;

		// I may end up deleting these two variables if I don't use them outside of the
		// constructor.
		this.kF=kF;
		this.targetTime = targetTimeInSeconds;
		this.timeLimit = timeTolerance * targetTimeInSeconds;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		toBeOptimized.optimizePIDinitialize();
		kP = 0;
		kI = 0;
		kD = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		 * PLEASE MAKE SURE THAT toBeOptomized.usePIDOutput is limited for safety!!!!!!
		 */
		toBeOptimized.changePIDCoefficients(kP,kI,kD,kF);
		timer.start();
		toBeOptimized.optimizePIDstart();
		while (toBeOptimized.isRunning()) {
			if (timer.get() > timeLimit) {
				runtime=timer.get();
				toBeOptimized.interrupted=true;
				errorRatio = toBeOptimized.optimizePIDinterrupted();
			}
		}
		if(toBeOptimized.interrupted==false) {
			runtime=timer.get();
		}
		// TODO account for overshoot after PID loop ends
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
