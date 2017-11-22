package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class DriveWithJoystickWithSensitivity extends Command {
	private static DriveMotors drivemotors = new DriveMotors();
	private static Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());
	private static double left=0;
	private static double right=0;
	private static boolean isRunning=false;

	public DriveWithJoystickWithSensitivity() {
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
		double sensitivity=(-joystick.getRawAxis(3)+1)/2;
		left=left*sensitivity;
		right=right*sensitivity;
		/* get the stuff below working properly instead of just multiplying...
		left=Math.log10(left)/Math.log10(sensitivity);
		right=Math.log10(right)/Math.log10(sensitivity);
		*/
		drivemotors.updateDrive(left,right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		left=0;
		right=0;
		drivemotors.updateDrive(0,0);
		drivemotors.disableSafety();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		left=0;
		right=0;
		drivemotors.updateDrive(0,0);
		drivemotors.disableSafety();
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
}
