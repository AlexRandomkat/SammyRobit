package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IRStop extends Command {
	public IRStop() {
		requires(Robot.drivemotors);
	}

	public void execute() {
		Robot.drivemotors.tankDrive(.5, .5);
	}

	public void end() {
		Robot.drivemotors.stop();
	}

	@Override
	protected boolean isFinished() {
		return Robot.frontIR.isClose();
	}
}
