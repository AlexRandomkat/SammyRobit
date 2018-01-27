package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import org.usfirst.frc.team6644.robot.Robot;
import org.usfirst.frc.team6644.robot.RobotPorts;

public class DriveMotors extends Subsystem {
	private static Spark leftDrivePWM;
	private static Spark rightDrivePWM;
	private static RobotDrive drive;
	private double motorSafteyExpireTime = 0.3;// sets the PWM to expire in 0.3 seconds after the last call of .Feed()

	/*
	 * 
	 * class variables for driving
	 */
	private boolean isRunning = false;
	// joystick stuff
	private Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());
	private double left = 0;
	private double right = 0;
	// controller stuff
	private boolean lastXValue = false;
	private boolean currentXValue = false;
	private boolean disableMotors = false;
	private XboxController controller = new XboxController(RobotPorts.CONTROLLER.get());
	private boolean rumble = false;
	private boolean lastBValue = false;
	private boolean currentBValue = false;
	protected double movementX;
	protected double movementY;
	private double controllerSensitivity = 0.7;
	private double sensitivityStep = 0.005;
	private double sensitivityHigh = 1;
	private double sensitivityLow = 0.6;

	/*
	 * 
	 * class variables for gyro
	 */
	private static ADXRS450_Gyro gyro;

	public DriveMotors() {
		leftDrivePWM = new Spark(RobotPorts.LEFT_DRIVE_PWM.get());
		rightDrivePWM = new Spark(RobotPorts.RIGHT_DRIVE_PWM.get());

		drive = new RobotDrive(leftDrivePWM, rightDrivePWM);

		gyro = new ADXRS450_Gyro();
	}

	/*
	 * 
	 * methods for drive motors
	 */
	public void enableSaftey() {
		drive.setSafetyEnabled(true);
		drive.setExpiration(motorSafteyExpireTime);
	}

	public void disableSafety() {
		drive.setSafetyEnabled(false);
		drive.setExpiration(motorSafteyExpireTime);
	}

	public void arcadeDrive(double speed, double angle) {
		drive.arcadeDrive(speed, angle);
	}

	public void arcadeDrive(GenericHID stick) {
		drive.arcadeDrive(stick);
	}

	public void tankDrive(double left, double right) {
		// left and right should be double values at/between -1 and 1.

		// Use enableSaftey for turning on drive motor safety. Not much sense in turning
		// safety on in one motor but not the other.
		// DO NOT HAVE MOTOR INPUTS GREATER IN MAGNITUDE THAN 1
		if (Math.abs(left) > 1 || Math.abs(right) > 1) {
			if (left > 1) {
				if (right > 1) {
					left = 1;
					right = 1;
				} else if (right < -1) {
					left = 1;
					right = -1;
				} else {
					left = 1;
				}
			} else if (left < -1) {
				if (right > 1) {
					left = -1;
					right = 1;
				} else if (right < -1) {
					left = -1;
					right = -1;
				} else {
					left = -1;
				}
			} else {
				if (right > 1) {
					right = 1;
				} else {
					right = -1;
				}
			}
			drive.tankDrive(left, right);
			System.out.println("DANGER: MOTOR OUTPUTS ARE GREATER IN MAGNITUDE THAN 1");
		} else {
			drive.tankDrive(left, right);
		}
	}

	public void stop() {
		disableSafety();
		drive.tankDrive(0, 0);
		Robot.drivemotors.setIsRunning(false);
	}

	public void startAutoMode() {
		disableSafety();
		resetGyro();
		setIsRunning(true);
	}

	public void startTeleopMode() {
		enableSaftey();
		setIsRunning(true);
	}

	/*
	 * 
	 * methods for driving in Teleop and Autonomous
	 */
	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void driveWithJoystick() {
		double forwardModifier = 1 - Math.abs(joystick.getY());
		double sensitivity = (-joystick.getRawAxis(3) + 1) / 2;
		if (disableMotors) {
			sensitivity = 0;
		}
		left = forwardModifier * joystick.getX() - joystick.getY() * sensitivity;
		right = -forwardModifier * joystick.getX() - joystick.getY() * sensitivity;
		tankDrive(left, right);
	}

	public void driveWithDualInputs() {
		driveWithJoystick();
		currentXValue = controller.getBumper(Hand.kRight);
		if (lastXValue != currentXValue) {
			lastXValue = currentXValue;
			if (currentXValue) {
				disableMotors = !disableMotors;
			}
		}

		currentBValue = joystick.getRawButton(2);
		if (lastBValue != currentBValue) {
			lastBValue = currentBValue;
			if (currentBValue) {
				rumble = !rumble;
			}
		}
		if (rumble) {
			setRumble(1, 1);
		} else {
			setRumble(0, 0);
		}
	}

	public void driveWithController() {
		movementX = controller.getRawAxis(0) * -1;
		movementY = controller.getRawAxis(1) * -1;
		if (disableMotors) {
			movementX = 0;
			movementY = 0;
		}
		int POV = controller.getPOV();
		if (POV != -1) {
			POV += 0;
			if (POV < 90 && POV > -90 && controllerSensitivity < sensitivityHigh) {
				controllerSensitivity += sensitivityStep;
			} else if (POV < 270 && POV > 90 && controllerSensitivity > sensitivityLow) {
				controllerSensitivity -= sensitivityStep;
			}
		}
		movementX *= controllerSensitivity;
		movementY *= controllerSensitivity;
		// TODO: Move this to UpdateSmartDashboard
		SmartDashboard.putNumber("POV value - 90: ", POV);
		currentBValue = controller.getBButton();
		if (lastBValue != currentBValue) {
			lastBValue = currentBValue;
			if (currentBValue) {
				rumble = !rumble;
			}
		}

		currentXValue = controller.getBumper(Hand.kRight);
		if (lastXValue != currentXValue) {
			lastXValue = currentXValue;
			if (currentXValue) {
				disableMotors = !disableMotors;
			}
		}

		if (rumble) {
			setRumble(1, 1);
		} else {
			setRumble(0, 0);
		}
		Robot.drivemotors.arcadeDrive(movementY, movementX);
		// TODO: Move these into the SmartDashboard command.
		SmartDashboard.putNumber("POV value: ", controller.getPOV());
		SmartDashboard.putNumber("Sensitivity: ", controllerSensitivity);
	}

	public void setRumble(double right, double left) {
		controller.setRumble(RumbleType.kLeftRumble, left);
		controller.setRumble(RumbleType.kRightRumble, right);
	}

	/*
	 * 
	 * methods for gyro
	 */
	public void calibrateGyro() {
		gyro.calibrate();
	}

	public void resetGyro() {
		gyro.reset();
		System.out.println("Gyro reset");
	}

	public double getDegrees() {
		return gyro.getAngle() % 360;
	}

	public double getDegreesTotal() {
		return gyro.getAngle();
	}

	public double getRadians() {
		return getDegrees() * Math.PI / 180;
	}

	public double getRadiansTotal() {
		return getDegreesTotal() * Math.PI / 180;
	}

	/*
	 * stuff for SmartDashboard
	 */
	public boolean isRunning() {
		return isRunning;
	}

	public double[] getDriveOutputs() {
		// returns an array [left,right]
		double[] driveOutputs = new double[2];
		driveOutputs[0] = left;
		driveOutputs[1] = right;
		return driveOutputs;
	}

	public void moveStraight(double speed, double time) {
		// Boolean: go staright
		// pid: time
	}

	public void setHeading(double heading) {
		// double: heading
		//
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
