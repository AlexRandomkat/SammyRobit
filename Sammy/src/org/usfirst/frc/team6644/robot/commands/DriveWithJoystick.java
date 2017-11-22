package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class DriveWithJoystick extends Command {
	protected static DriveMotors drivemotors = new DriveMotors();
	protected static Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());
	protected static double left=0;
	protected static double right=0;
	protected static boolean isRunning=false;

	public DriveWithJoystick() {
		requires(drivemotors);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drivemotors.enableSaftey();
		isRunning=true;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double forwardModifier = 1-Math.abs(joystick.getY());
		left=forwardModifier*joystick.getX()-joystick.getY();
		right=forwardModifier*joystick.getX()+joystick.getY();
		drivemotors.updateDrive(left,right);
	}
	public boolean isRunning() {
		return isRunning;
	}
	public double[] getDriveOutputs() {
		//returns an array [left,right]
		double[] driveOutputs=new double[2];
		driveOutputs[0]=left;
		driveOutputs[1]=right;
		return driveOutputs;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivemotors.updateDrive(0, 0);
		drivemotors.disableSafety();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
