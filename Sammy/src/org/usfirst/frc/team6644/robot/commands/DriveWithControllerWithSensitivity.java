package org.usfirst.frc.team6644.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithControllerWithSensitivity extends DriveWithController {
	private double sensitivity = 0.7;
	private double sensitivityStep = 0.005;
	private double sensitivityHigh = 1;
	private double sensitivityLow = 0.6;

	public DriveWithControllerWithSensitivity() {
		super();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void calculateMotorOutputs() {
		super.calculateMotorOutputs();
		int POV = controller.getPOV();
		if (POV != -1) {
			POV += 0;
			if (POV < 90 && POV > -90 && sensitivity < sensitivityHigh) {
				sensitivity += sensitivityStep;
			} else if (POV < 270 && POV > 90 && sensitivity > sensitivityLow) {
				sensitivity -= sensitivityStep;
			}
		}

		movementX *= sensitivity;
		movementY *= sensitivity;
		SmartDashboard.putNumber("POV value - 90: ", POV);
	}

	@Override
	protected void execute() {
		calculateMotorOutputs();
		super.execute();
		SmartDashboard.putNumber("POV value: ", controller.getPOV());
		SmartDashboard.putNumber("Sensitivyty: ", sensitivity);

	}
}
