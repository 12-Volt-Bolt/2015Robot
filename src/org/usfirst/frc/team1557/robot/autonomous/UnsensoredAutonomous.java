package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.AutoPosition;
import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnsensoredAutonomous extends CommandGroup {

	public UnsensoredAutonomous() {
		addSequential(new AutoSetClamp(true));
		// Pick up
		addSequential(new AutoLifterCommand(0.6, 1));

		// Move Forward
		addSequential(new AutoMecanumTime(0, 0.5, 0, 0.8));

		// Drop Can
		addSequential(new AutoSetClamp(false));

		// Lower arm
		addSequential(new AutoLifterCommand(-0.5, 1));

		// Grab stuff(tm)
		addSequential(new AutoSetClamp(true));

		// Lift arm back up
		addSequential(new AutoLifterCommand(0.5, 1));

		// Rotate
		addSequential(new AutoMecanumTime(0, 0, 0.5, 0.5));

		// Drive into Autozone
		// TODO: fancy distance calcs

		if ((AutoPosition) Robot.positionChooser.getSelected() == AutoPosition.RIGHT) {
			addSequential(new AutoMecanumTime(0, 0.5, 0, 3.5));
		} else if ((AutoPosition) Robot.positionChooser.getSelected() == AutoPosition.CENTER) {
			addSequential(new AutoMecanumTime(0, 0.5, 0, 3.5));
		} else {
			addSequential(new AutoMecanumTime(0, 0.5, 0, 3));
		}
		// Lower stuff(tm)
		addSequential(new AutoLifterCommand(0.3, 1));

		// Release stuff(tm)
		addSequential(new AutoSetClamp(false));
	}
}
