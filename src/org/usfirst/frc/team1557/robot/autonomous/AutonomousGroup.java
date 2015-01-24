package org.usfirst.frc.team1557.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGroup extends CommandGroup {
    public  AutonomousGroup() {
    	addSequential(new AutoDriveFoward(1));
    }
}
