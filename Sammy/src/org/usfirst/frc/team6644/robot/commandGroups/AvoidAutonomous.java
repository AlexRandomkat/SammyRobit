package org.usfirst.frc.team6644.robot.commandGroups;

import org.usfirst.frc.team6644.robot.commands.IRStop;
import org.usfirst.frc.team6644.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AvoidAutonomous extends CommandGroup {
	public AvoidAutonomous(double speed) {
		for (int i = 0; i != 1000; i++) {
			avoidLoop(speed);
		}
	}

	public void avoidLoop(double speed) {
		addSequential(new IRStop());
		addSequential(new Turn(speed, 90));
	}
}
