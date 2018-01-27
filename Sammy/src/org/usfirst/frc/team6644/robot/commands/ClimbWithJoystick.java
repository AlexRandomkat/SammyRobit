package org.usfirst.frc.team6644.robot.commands;

import org.usfirst.frc.team6644.robot.RobotPorts;
import org.usfirst.frc.team6644.robot.subsystems.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbWithJoystick extends Command {
	protected Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());
	// protected boolean isRunning = false;
	protected Climb climbo = new Climb();

	public ClimbWithJoystick() {
		// Use requires() here to declare subsystem dependencies
		requires(climbo);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		climbo.enableSaftey();
		// isRunning = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void isButtonPressed() {

	}

	protected void execute() {
		// all the code in here
		if (joystick.getRawButton(3)) {
			climbo.set(1);
		} else if (joystick.getRawButton(5)) {
			climbo.set(-1);
		} else {
			climbo.set(0);
		} // end else if
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
