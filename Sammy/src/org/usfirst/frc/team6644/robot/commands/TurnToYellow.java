package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToYellow extends Command {
	public String turning = new String("0 0 0");
	public boolean Turn = true;
	protected double speed = 0.5;

	public TurnToYellow() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
		// Robot.displayvisionthing.execute();
		if (!Robot.displayvisionthing.hasFoundBlob()) {
			turning = "0 0 0";
			Robot.drivemotors.tankDrive(0, 0);
			Turn = true;
		} else if (Robot.displayvisionthing.hasFoundBlob()) {
			if (((Robot.displayvisionthing.xAxisDistance() < 90) && (Robot.displayvisionthing.xAxisDistance() > 70) && (Robot.frontIR.distanceValue() > 1))) {
				Robot.drivemotors.tankDrive(speed, speed);
				turning = "> | <";
				Turn = false;
			}
			if (Robot.displayvisionthing.xAxisDistance() >= 90  && (Robot.frontIR.distanceValue() > 1)) {
				Robot.drivemotors.tankDrive(speed, -speed);
				turning = "> > >";
				Turn = true;
			}
			if (Robot.displayvisionthing.xAxisDistance() <= 70  && (Robot.frontIR.distanceValue() > 1)) {
				Robot.drivemotors.tankDrive(-speed, speed);
				turning = "< < <";
				Turn = true;
			}
			if (!Robot.displayvisionthing.hasFoundBlob()  && (Robot.frontIR.distanceValue() > 1)) {
				turning = "0 0 0";
				Robot.drivemotors.tankDrive(0, 0);
				Turn = false;
			}

		}

	}// end execute

	public String getTurning() {
		return turning;
	}

	public boolean stillTurn() {
		return Turn;
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
