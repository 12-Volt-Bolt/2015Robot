package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.AutonomousPlans;
import org.usfirst.frc.team1557.robot.Robot;

import static org.usfirst.frc.team1557.robot.RobotMap.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BoringAutonomous extends CommandGroup {
	public BoringAutonomous() {
		// Initially grab the recycle bin
		addSequential(new AutoLockCommand(false));
		addSequential(new AutoSetClamp(true));
		addSequential(new WaitCommand(0.25));
		// Lift the bin up
		addSequential(new AutoLifterCommand(0.71, 1));
		addSequential(new WaitCommand(0.25));

		// Move Forward to get over to the bin
		addSequential(new AutoMecanumTime(0, -0.5, 0, 0.51));
		addSequential(new WaitCommand(0.25));

		// Drop Can onto the bin
		addSequential(new AutoSetClamp(false));
		addSequential(new WaitCommand(0.5));

		// TODO Add Limit Switch Compatibility
		// Lower arm to grab bin
		addSequential(new AutoLiftLimitDown());
		addSequential(new WaitCommand(0.5));

		// Grab bin (tm)
		addSequential(new AutoSetClamp(true));
		addSequential(new WaitCommand(0.5));

		// Lift arm back up to clear bump
		addSequential(new AutoLifterCommand(1, 1));
		addSequential(new AutoLockCommand(true));
		Robot.lockSystem.setLock(true);
		addSequential(new WaitCommand(0.3));

		// Rotate 90deg counterclockwise
		addSequential(new AutoMecanumTime(0, 0, -0.4, 1.2));
		addSequential(new WaitCommand(0.25));
		// Drive into Autozone
		addSequential(new AutoMecanumTime(0, -0.42, 0, 2.5));

		addSequential(new WaitCommand(0.5));

		// Lower stuff(tm)
		addSequential(new AutoLiftLimitDown());
		addSequential(new WaitCommand(1));
		// Release stuff(tm)
		addSequential(new AutoSetClamp(false));
		// addSequential(new AutoMecanumTime(-0.2, 0, 0, 0.1));
		addSequential(new AutoLockCommand(false));
	}

}
