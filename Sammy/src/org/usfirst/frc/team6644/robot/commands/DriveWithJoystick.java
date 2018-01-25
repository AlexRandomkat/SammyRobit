package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6644.robot.Robot;
import org.usfirst.frc.team6644.robot.RobotPorts;

public class DriveWithJoystick extends Command {
	private Joystick joy = new Joystick(RobotPorts.JOYSTICK.get());
	protected boolean lastButton = false;
	protected boolean lastSafeButton = false;
	protected boolean IRSafety = false;
	protected boolean safe = true;

	public DriveWithJoystick() {
		requires(Robot.drivemotors);
		SmartDashboard.getBoolean("Close stop", Robot.frontIR.isClose());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivemotors.startTeleopMode();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putBoolean("Stuff", IRSafety);
		boolean curBut = joy.getRawButton(2);
		if (lastButton != curBut) {
			lastButton = curBut;
			if (curBut) {
				Robot.drivemotors.toggleMotorDisableState();
			}
		}

		boolean curSafeBut = joy.getRawButton(9);
		if (lastSafeButton != curSafeBut) {
			lastSafeButton = curSafeBut;
			if(curSafeBut) {
				IRSafety = !IRSafety;
			}
		}

		if (IRSafety) {
			if (!Robot.frontIR.isClose() && !Robot.leftIR.isClose() && !Robot.rightIR.isClose()) {
				Robot.drivemotors.driveWithJoystick();
			} else {
				Robot.drivemotors.tankDrive(0, 0);
			}
		} else {
			Robot.drivemotors.driveWithJoystick();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivemotors.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
