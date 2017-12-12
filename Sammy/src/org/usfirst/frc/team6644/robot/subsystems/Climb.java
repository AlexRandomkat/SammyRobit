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
	private double motorSafteyExpireTime = 0.1;// sets the PWM to expire in 0.2 seconds after the last call of .Feed()

	public void enableSaftey() {
		ClimbDrive.setSafetyEnabled(true);
		ClimbDrive.setExpiration(motorSafteyExpireTime);// see comment above
	}

	public void disableSafety() {
		ClimbDrive.setSafetyEnabled(false);
	}

	public void updateClimb(double climmmb) {
		// left and right should be double values at/between -1 and 1.

		// Use enableSaftey for turning on drive motor safety. Not much sense in turning
		// safety on in one motor but not the other.
		if (ClimbDrive.isSafetyEnabled()) {
			ClimbDrive.Feed();
		}
		ClimbDrive.set(climmmb);//accounts for flipped orientation of motors
	}
	
	public void stop() {
		disableSafety();
		ClimbDrive.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

