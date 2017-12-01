package org.usfirst.frc.team6644.robot.commandGroups;

import org.usfirst.frc.team6644.robot.commands.AutonomousTest;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandsA extends CommandGroup {

    public AutonomousCommandsA() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	for(double i=0;i<=0.9;i+=0.1) {
    		addSequential(new AutonomousTest(i,-i,2));
    	}
    	addSequential(new AutonomousTest(1,1,4));
    	for(double i=0.9;i>=0.6;i-=0.1) {
    		addSequential(new AutonomousTest(i,-i,0.2+(i*0.15)));
    	}
    	for(double i=0.5;i>=0.0;i-=0.1) {
    		addSequential(new AutonomousTest(i,-i,0.2));
    	}
    }
}
