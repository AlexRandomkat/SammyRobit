package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team6644.robot.RobotPorts;
/**
 *
 */
public class DriveMotors extends Subsystem {
	Spark leftDrivePWM=new Spark(RobotPorts.LEFT_DRIVE_PWM.get());
	Spark rightDrivePWM=new Spark(RobotPorts.RIGHT_DRIVE_PWM.get());
	
	public void enableSaftey(){
		leftDrivePWM.setSafetyEnabled(true);
		leftDrivePWM.setExpiration(0.2);//sets the PWM to expire in 0.2 seconds after the last call of .Feed()
		rightDrivePWM.setSafetyEnabled(true);
		rightDrivePWM.setExpiration(0.2);//see comment above
	}
	public void disableSafety(){
		leftDrivePWM.setSafetyEnabled(false);
		rightDrivePWM.setSafetyEnabled(false);
	}
    public void updateDrive(double left,double right){
    	//left and right should be double values at/between -1 and 1.
    	
    	//Use enableSaftey for turning on drive motor safety. Not much sense in turning safety on in one motor but not the other.
    	if(leftDrivePWM.isSafetyEnabled()){
    		leftDrivePWM.Feed();
    		rightDrivePWM.Feed();
    	}
    	leftDrivePWM.set(left);
    	rightDrivePWM.set(right);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

