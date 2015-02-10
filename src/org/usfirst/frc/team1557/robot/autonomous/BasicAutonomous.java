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
public class BasicAutonomous extends CommandGroup {
	public BasicAutonomous() {
		
		// Initially grab the recycle bin
		addSequential(new AutoSetClamp(true));		
		// Lift the bin up
		addSequential(new AutoLifterCommand(0.7, 1));
		//addSequential(new WaitCommand(0.25));
		
		// Move Forward to get over the bin
		addSequential(new AutoMecanumTime(0, -0.5, 0, 0.5));
		addSequential(new WaitCommand(0.25));
		
		// Drop Can onto the bin
		addSequential(new AutoSetClamp(false));
		addSequential(new WaitCommand(0.25));
		
		// Lower arm to grab bin
		addSequential(new AutoLifterCommand(-0.5, 1));
		addSequential(new WaitCommand(0.25));
		
		// Grab bin (tm)
		addSequential(new AutoSetClamp(true));
		addSequential(new WaitCommand(0.25));
		
		// Lift arm back up to clear bump
		addSequential(new AutoLifterCommand(0.5, 0.6));
		addSequential(new WaitCommand(0.25));
		
		// Rotate 90deg counterclockwise
		addSequential(new AutoMecanumTime(0, 0, -0.4, 1.1));
		addSequential(new WaitCommand(0.25));
		
		// Drive into Autozone	
		addSequential(new AutoMecanumTime(0, -0.5, 0, 1));
		addSequential(new WaitCommand(1));
		
		// Lower stuff(tm)
		addSequential(new AutoLifterCommand(-0.3, 0.2));

		// Release stuff(tm)
		addSequential(new AutoSetClamp(false));

	}

}
