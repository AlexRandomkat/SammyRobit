package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 *
 */
public class PDM extends Subsystem {
	private PowerDistributionPanel pdm = new PowerDistributionPanel();

	public void clearStickyFaults() {
		pdm.clearStickyFaults();
	}
	
	public void printPDMStats() {
		System.out.println("\n\n****\t\tPower Distribution Module Stats:\t\t***");
		System.out.println("\t\tTemperature:"+getTemperature());
		System.out.println("\t\tVoltage:"+getVoltage());
		System.out.println("\t\tTotal Current:"+getTotalCurrent());
		System.out.println("\t\tTotal Power:"+getTotalPower());
		System.out.println("\t\tTotal Energy:"+getTotalEnergy());
		System.out.println("--------------------------------------");
	}

	public double getCurrent(int channel) {
		return pdm.getCurrent(channel);
	}

	public double getTemperature() {
		return pdm.getTemperature();
	}

	public double getTotalCurrent() {
		return pdm.getTotalCurrent();
	}

	public double getTotalEnergy() {
		return pdm.getTotalEnergy();
	}

	public double getTotalPower() {
		return pdm.getTotalPower();
	}

	public double getVoltage() {
		return pdm.getVoltage();
	}

	public void resetTotalEnergy() {
		pdm.resetTotalEnergy();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
