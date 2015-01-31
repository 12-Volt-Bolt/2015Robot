package org.usfirst.frc.team1557.robot.commands;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MecanumDriveCommand extends Command {
	boolean speed = false;

	public MecanumDriveCommand() {
		// use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Shifting; Should probably put this somewhere else.
		if (OI.mainJoy.getRawButton(bButton)) {
			speed = !speed;
			if (!speed) {
				SmartDashboard.putNumber(RobotMap.speedKey, 1);
			} else {
				SmartDashboard.putNumber(RobotMap.speedKey, 0.6);
			}

		}
		Robot.driveSystem.mecanumCartesian(
				OI.mainJoy.getRawAxis(leftXAxis)
						* SmartDashboard.getNumber(RobotMap.lifterKey, 1),
				OI.mainJoy.getRawAxis(leftYAxis)
						* SmartDashboard.getNumber(RobotMap.lifterKey, 1),
				OI.mainJoy.getRawAxis(rightXAxis));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
