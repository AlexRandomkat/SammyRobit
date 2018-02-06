package org.usfirst.frc.team6644.robot.subsystems;

import org.usfirst.frc.team6644.robot.RobotPorts;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climb extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private static Spark ClimbDrive = new Spark(RobotPorts.CLIMB_DRIVE_PWM.get());

	private double motorSafteyExpireTime = 0.1;// sets the PWM to expire in 0.1 seconds after the last call of .Feed()

	public void enableSaftey() {
		ClimbDrive.setSafetyEnabled(true);
		ClimbDrive.setExpiration(motorSafteyExpireTime);// see comment above
	}

	public void disableSafety() {
		ClimbDrive.setSafetyEnabled(false);
	}

	public void set(double value) {
		if (value < -1) {
			ClimbDrive.set(-1);
			System.out.println("Climb Drive: Tried to make lower than -1");
		} else if (value > 1) {
			ClimbDrive.set(1);
			System.out.println("Climb Drive: Tried to make higher than 1");
		} else {
			ClimbDrive.set(value);
		}
		ClimbDrive.feed();
	}

	public void stop() {
		disableSafety();
		ClimbDrive.set(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}