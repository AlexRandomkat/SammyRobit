package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6644.robot.Robot;
import org.usfirst.frc.team6644.robot.subsystems.GRIP_SDS;
import org.usfirst.frc.team6644.robot.subsystems.Vision;

/**
 *
 */
public class UpdateSmartDashboard extends Command {

	public UpdateSmartDashboard() {
		// put required subsystems here
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.drivemotors.isRunning()) {
			// updates SmartDashboard with the program's motor outputs
			double[] prgmMotorOutputs = Robot.drivemotors.getDriveOutputs();
			String leftMotor = String.format("%.3f", prgmMotorOutputs[0]);
			String rightMotor = String.format("%.3f", prgmMotorOutputs[1]);

			SmartDashboard.putString("Left motor: ", leftMotor);
			SmartDashboard.putString("Right motor: ", rightMotor);
		}
		SmartDashboard.putNumber("Total Radians: ", Robot.drivemotors.getRadiansTotal());
		SmartDashboard.putNumber("Current Radians: ", Robot.drivemotors.getRadians());
		SmartDashboard.putNumber("IR Voltage: ", Robot.ir.getVoltage());
		SmartDashboard.putNumber("IR Distance: ", Robot.ir.distanceValue());
		SmartDashboard.putBoolean("Object Close", Robot.ir.isClose());
		SmartDashboard.putNumber("Ultra", Robot.ultra.getRangeInches());
		SmartDashboard.putNumber("Force", Robot.force.getVoltage());
		
		//SmartDashboard.putNumber("x SDS: ", Robot.pipeline.getxVideoValue());
		//SmartDashboard.putNumber("y SDS: ", Robot.pipeline.getyVideoValue());
		//SmartDashboard.putNumber("size SDS: ", Robot.pipeline.getsizeVideoValue());
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