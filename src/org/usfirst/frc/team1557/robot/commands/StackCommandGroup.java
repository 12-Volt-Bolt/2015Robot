package org.usfirst.frc.team1557.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackCommandGroup extends CommandGroup {

	public StackCommandGroup() {
		addSequential(new ToggleClampCommand());
		addSequential(new StackCommand());
		addSequential(new ToggleClampCommand());

	}
}
