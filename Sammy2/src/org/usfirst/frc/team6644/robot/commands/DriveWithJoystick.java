package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.RobotPorts;

import edu.wpi.first.wpilibj.Joystick;
/**
 *
 */
public class DriveWithJoystick extends Command {
	DriveMotors drivemotors=new DriveMotors();
	Joystick joystick=new Joystick(RobotPorts.JOYSTICK.get());
    public DriveWithJoystick() {
        requires(drivemotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivemotors.enableSaftey();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Assumes "the typical convention for joysticks and gamepads is for Y to be negative as they joystick is pushed away from the user ... and for X to be positive as the joystick is pushed to the right."
    	//^^^Check this assumption in the Driver Station before deploying code.
    	double forwardModifier=1-Math.abs(joystick.getX());
    	drivemotors.updateDrive(forwardModifier*joystick.getY()+joystick.getX(),forwardModifier*joystick.getY()-joystick.getX());//I'm pretty sure it's impossible for any of the left or right PWM inputs to be out of the range of [-1,1], double check that.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivemotors.updateDrive(0,0);
    	drivemotors.disableSafety();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
