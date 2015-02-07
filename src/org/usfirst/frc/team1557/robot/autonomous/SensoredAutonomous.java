package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.AutoPosition;
import org.usfirst.frc.team1557.robot.Robot;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensoredAutonomous extends CommandGroup {
	public SensoredAutonomous() {
		addSequential(new AutoSetClamp(true));
		// Pick up
		addSequential(new AutoLifterCommand(n("AutoLiftUpSpeed1", 0.6), n("AutoLiftUpTime1", 1)));

		// Move Forward
		addSequential(new AutoMecanumTime(0, n("AutoDriveSpeed1",0.5), 0, n("AutoDriveTime1",0.8)));

		// Drop Can
		addSequential(new AutoSetClamp(false));

		// Lower arm
		addSequential(new AutoLifterCommand(n("AutoLiftDownSpeed1",-0.5), n("AutoLiftDownTime1",1)));

		// Grab stuff(tm)
		addSequential(new AutoSetClamp(true));

		// Lift arm back up
		addSequential(new AutoLifterCommand(n("AutoLiftUpSpeed2",0.5), n("AutoLiftUpSpeed",1)));

		// Rotate
		addSequential(new AutoMecanumTime(0, 0,n("AutoTurnSpeed",0.5), n("AutoTurnTime",0.5)));

		// Drive into Autozone
		// TODO: fancy distance calcs

		if ((AutoPosition) Robot.positionChooser.getSelected() == AutoPosition.RIGHT) {
			addSequential(new AutoBumpClimbDrive());
		} else if ((AutoPosition) Robot.positionChooser.getSelected() == AutoPosition.CENTER) {
			addSequential(new AutoBumpClimbDrive());
		} else {
			addSequential(new AutoMecanumTime(0, n("AutoRamplessSpeed",0.5), 0, n("AutoRamplessTime",5)));
		}
		// Lower stuff(tm)
		addSequential(new AutoLifterCommand(n("AutoLiftDownSpeed2",0.3), n("AutoLiftDownTime2",1)));

		// Release stuff(tm)
		addSequential(new AutoSetClamp(false));

	}

}
