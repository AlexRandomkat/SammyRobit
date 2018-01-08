package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	protected double speed = 0;
	protected double goal = 0;

	public Turn(double speed, double goal) {
		requires(Robot.drivemotors);
		this.speed = speed;
		this.goal = goal;
	}

	public void initialize() {
		Robot.drivemotors.disableSafety();
		Robot.drivemotors.resetGyro();
	}

	public void execute() {
		Robot.drivemotors.tankDrive(speed, -speed);
	}

	public void end() {
		Robot.drivemotors.stop();
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivemotors.getDegrees() > goal;
	}

}
