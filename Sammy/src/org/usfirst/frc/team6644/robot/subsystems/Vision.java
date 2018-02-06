package org.usfirst.frc.team6644.robot.subsystems;

import org.opencv.core.Mat;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	public static CvSink cvSink = CameraServer.getInstance().getVideo();
	Thread cameraThread;
	public int imageWidth = 640;
	public int imageHeight = 480;
	public int fps = 7;
	public static Mat toBeProcessed = new Mat();
	public void init() {
		camera.setResolution(imageWidth, imageHeight);
		camera.setFPS(fps);
		//cvSink.grabFrame(toBeProcessed);
		
		
		
		
		/*
		 * cameraThread = new Thread(() -> {
		 * 
		 * CvSink cvSink = CameraServer.getInstance().getVideo();
		 *  CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
		 * 
		 * Mat source = new Mat();
		 *  Mat output = new Mat(); 
		 *  System.out.println("Thread");
		 * 
		 * while (!Thread.interrupted()) {
		 *  cvSink.grabFrame(source);
		 * Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
		 * outputStream.putFrame(output);
		 *  } 
		 *  }
		 *  ); 
		 *  cameraThread.start();
		 */
	}

	public void initDefaultCommand() {
//		while(!Thread.interrupted()) {cvSink.grabFrame(toBeProcessed);}
		// start camera stuff
		// CameraServer.getInstance().startAutomaticCapture();//simple camera stuff
		/*
		 * new Thread(() -> { UsbCamera camera =
		 * CameraServer.getInstance().startAutomaticCapture(); camera.setResolution(64,
		 * 64);
		 * 
		 * CvSink cvSink = CameraServer.getInstance().getVideo(); CvSource outputStream
		 * = CameraServer.getInstance().putVideo("Blur", 640, 480);
		 * 
		 * Mat source = new Mat(); Mat output = new Mat();
		 * 
		 * while (!Thread.interrupted()) { cvSink.grabFrame(source);
		 * Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
		 * outputStream.putFrame(output); } }).start();
		 */
		// advanced camera stuff
		// end camera stuff

	}
}