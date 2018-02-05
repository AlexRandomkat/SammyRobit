package org.usfirst.frc.team6644.robot;

import org.opencv.core.Mat;

//commands
import org.usfirst.frc.team6644.robot.commands.AccelerometerTest;
import org.usfirst.frc.team6644.robot.commands.DisplayVision;
import org.usfirst.frc.team6644.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team6644.robot.commands.TurnToYellow;
import org.usfirst.frc.team6644.robot.commands.UpdateSmartDashboard;

//subsystems
import org.usfirst.frc.team6644.robot.subsystems.DriveMotors;
import org.usfirst.frc.team6644.robot.subsystems.ForceSensor;
import org.usfirst.frc.team6644.robot.subsystems.IRSensor;
import org.usfirst.frc.team6644.robot.subsystems.PDM;
import org.usfirst.frc.team6644.robot.subsystems.PCM;

//other stuff
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	// public static NetworkTable table;
	public static OI oi;

	// public static boolean Tabling;

	// essential subsystems
	public static DriveMotors drivemotors;
	public static PDM pdm;
	public static PCM pcm;

	// sensors
	public static IRSensor frontIR;
	public static IRSensor leftIR;
	public static IRSensor rightIR;
	public static ForceSensor force;
	public static int i;
	public static DisplayVision displayvisionthing;
	public static TurnToYellow turny;
	protected static String status = new String("0 0 0");
	// public static CvSource outputStream =
	// CameraServer.getInstance().putVideo("Blur", 640, 480);
	public static Mat sworce = new Mat();
	// test
	public static Joystick joystick = new Joystick(RobotPorts.JOYSTICK.get());
	// end test

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		// ROBOT MUST BE STILL WHEN TURNED ON
		drivemotors = new DriveMotors();
		pdm = new PDM();
		pcm = new PCM();
		pdm.clearStickyFaults();
		pcm.clearAllPCMStickyFaults();
		pcm.startCompressor();
		pcm.printCompressorStats();
		pdm.printPDMStats();
		drivemotors.calibrateGyro();
		oi = new OI();
		frontIR = new IRSensor(RobotPorts.FRONT_IR.get());
		leftIR = new IRSensor(RobotPorts.LEFT_IR.get());
		rightIR = new IRSensor(RobotPorts.RIGHT_IR.get());
		force = new ForceSensor();

		i = 0;
		turny = new TurnToYellow();
		displayvisionthing = new DisplayVision();

		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		// clear everything that may be on the scheduler
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// might use the commented out stuff below later
		/*
		 * autonomousCommand = chooser.getSelected();
		 * 
		 * // String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); // switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * // MyAutoCommand(); break; case "Default Auto": default: autonomousCommand =
		 * new // ExampleCommand(); break; }
		 * 
		 * 
		 * // schedule the autonomous command (example) if (autonomousCommand != null) {
		 * autonomousCommand.start(); }
		 */

		// add commands to scheduler for autonomous mode

		// AutonomousCommandsA autonomousCommands=new AutonomousCommandsA();
		// Scheduler.getInstance().add(autonomousCommands);

		// Scheduler.getInstance().add(new AutonomousMoveStraight(3.75, 0.6));
		// Scheduler.getInstance().add(new AvoidAutonomous(.6));
		UpdateSmartDashboard outputs = new UpdateSmartDashboard();
		Scheduler.getInstance().add(outputs);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		displayvisionthing.execute();
		while (displayvisionthing.hasFoundBlob()) {
			Scheduler.getInstance().run();
			displayvisionthing.execute();
			if (displayvisionthing.hasFoundBlob()) {
				turny.execute();
				status = turny.getTurning();
			} // end if
			SmartDashboard.putString("Turning", status);
		} // end while
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}

		// add command to drive robot with joystick and send stuff to SmartDashboard
		DriveWithJoystick drive = new DriveWithJoystick();
		// DriveWithDualInput drive = new DriveWithDualInput();
		// DriveWithDualInput drive = new DriveWithDualInput();
		UpdateSmartDashboard outputs = new UpdateSmartDashboard();
		Scheduler.getInstance().add(drive);
		Scheduler.getInstance().add(outputs);
		Scheduler.getInstance().add(new AccelerometerTest());
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		i++;

		// displayvisionthing.execute();

		Scheduler.getInstance().run();

	}

	@Override
	public void testInit() {
		/*
		 * Scheduler.getInstance().removeAll(); Scheduler.getInstance().add(new
		 * DriveWithController());
		 */
		// Robot.Table();
	}
	// static int counter = 0;
	// static double[] defaultValue = new double[0];
	// public static void Table() {
	// NetworkTable table = NetworkTable.getTable("GRIP/myBlobsReport");
	// int counter=0;
	// double[] defaultValue = new double[0];
	// //while (Tabling) {
	// for(int tables = 0; tables < 120; tables++) {
	// double[] areas = table.getNumberArray("size", defaultValue);
	// double[] Xs = table.getNumberArray("centerX", defaultValue);
	// double[] Ys = table.getNumberArray("centerY", defaultValue);
	// for (double area : areas) {
	// System.out.println("areas: "+ area+"\t xOBJECT: "+Xs[counter] + "\t yOBJECT:
	// "+Ys[counter] );
	// counter++;
	// }
	// System.out.println();
	// Timer.delay(1);
	// }

	// }

	/**
	 * This function is called periodically during test mode
	 */

	@Override
	public void testPeriodic() {
		/*
		 * LiveWindow.run(); // test stuff try {
		 * System.out.println(joystick.getRawAxis(3)); Thread.sleep(50); } catch
		 * (InterruptedException e) { System.out.println(e); }
		 */
		// end test stuff

		Scheduler.getInstance().run();

	}// end test
}
