package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

/**
 *
 */
public class FuckTheDriveMotors extends Command {
	DriveMotors drivemotors=new DriveMotors();
	Joystick joystick=new Joystick(RobotPorts.JOYSTICK.get());
    public FuckTheDriveMotors() {
        requires(drivemotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivemotors.enableSaftey();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double forwardModifier=1-Math.abs(joystick.getX());
    	double left=forwardModifier*joystick.getY()+joystick.getX();
    	double right=forwardModifier*joystick.getY()-joystick.getX();
    	if(left>=0){
    		if(right>=0){
    			drivemotors.updateDrive(1,1);
        	}else{
        		drivemotors.updateDrive(1,-1);
        	}
    	}else{
    		if(right>=0){
    			drivemotors.updateDrive(-1,1);
        	}else{
        		drivemotors.updateDrive(-1,-1);
        	}
    	}
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
