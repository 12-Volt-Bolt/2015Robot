package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.AutoPosition;
import org.usfirst.frc.team1557.robot.Robot;

import static org.usfirst.frc.team1557.robot.RobotMap.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensoredAutonomous extends CommandGroup {
	public SensoredAutonomous() {
		addSequential(new AutoSetClamp(true));
		addSequential(new WaitCommand(1));
		// Pick up
		addSequential(new AutoLifterCommand(0.7, 1));
		addSequential(new WaitCommand(1));
		// Move Forward
		addSequential(new AutoMecanumTime(0, -0.5, 0, 0.5));
		addSequential(new WaitCommand(1));
		// Lower Arm
		//addSequential(new AutoLifterCommand(0.6, 0.4));
		// Drop Can
		addSequential(new AutoSetClamp(false));
		addSequential(new WaitCommand(1));
		// Lower arm
		addSequential(new AutoLifterCommand(-0.3, 0.8));
		addSequential(new WaitCommand(1));
		// Grab stuff(tm)
		addSequential(new AutoSetClamp(true));
		addSequential(new WaitCommand(1));
		// Lift arm back up
		addSequential(new AutoLifterCommand(0.5, 0.7));
		addSequential(new WaitCommand(1));
		// Rotate
		addSequential(new AutoMecanumTime(0, 0, -0.75, 0.1));
		addSequential(new WaitCommand(1));
		// Drive into Autozone
		// TODO: fancy distance calcs

		if ((AutoPosition) Robot.positionChooser.getSelected() == AutoPosition.RIGHT) {
			addSequential(new AutoBumpClimbDrive());
		} else if ((AutoPosition) Robot.positionChooser.getSelected() == AutoPosition.CENTER) {
			addSequential(new AutoBumpClimbDrive());
		} else {
			addSequential(new AutoMecanumTime(0, 0.5, 0, 5));
		}
		
		addSequential(new WaitCommand(1));
		// Lower stuff(tm)
		addSequential(new AutoLifterCommand(0.3, 1));

		// Release stuff(tm)
		addSequential(new AutoSetClamp(false));

	}

}
