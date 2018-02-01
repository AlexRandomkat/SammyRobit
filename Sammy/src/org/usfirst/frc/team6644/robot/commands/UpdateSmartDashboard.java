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

	private int count = 0;
	public UpdateSmartDashboard() {
		// put required subsystems here
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		count++;
		count %= 300;
		if(count == 250) {
			//Robot.irArray.setLow();
			//System.out.println("High");
		}else if(count == 499) {
			//Robot.ard.setLow();
			//Robot.irArray.setHigh();
		}
		//Robot.ard.setHigh();
		
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
		SmartDashboard.putBoolean("Object Close Front", Robot.frontIR.isClose());
		SmartDashboard.putBoolean("Object Close Left", Robot.leftIR.isClose());
		SmartDashboard.putBoolean("Object Close Right", Robot.rightIR.isClose());
		//SmartDashboard.putNumber("Ultra", Robot.ultra.getRangeInches());
		SmartDashboard.putNumber("Force", Robot.force.getVoltage());
		SmartDashboard.putString("Bananas?:", GRIP_SDS.value); // "dont know yet");
		SmartDashboard.putNumber("X banana location", GRIP_SDS.xVideoValue);
		SmartDashboard.putNumber("Y banana location", GRIP_SDS.yVideoValue);
		SmartDashboard.putNumber(" banana size", GRIP_SDS.sizeVideoValue);
		
		
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