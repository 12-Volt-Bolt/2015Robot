package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackCommandGroup extends CommandGroup {
    
    public  StackCommandGroup() {
    	requires(Robot.lifterSystem);
    	requires(Robot.clampSystem);
    	
    	addSequential(new StackCommand());
    	addSequential(new ToggleClampCommand());
    	
    }
}
