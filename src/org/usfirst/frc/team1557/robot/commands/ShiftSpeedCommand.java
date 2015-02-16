package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShiftSpeedCommand extends Command {

	public ShiftSpeedCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if ((OI.mainJoy.getPOV() == 0)) {
			SmartDashboard.putNumber(RobotMap.speedKey, 1);
		} else if ((OI.mainJoy.getPOV() == 90)) {
			SmartDashboard.putNumber(RobotMap.speedKey, 0.25);
		} else if ((OI.mainJoy.getPOV() == 180)) {
			SmartDashboard.putNumber(RobotMap.speedKey, 0.5);
		} else if ((OI.mainJoy.getPOV() == 270)) {
			SmartDashboard.putNumber(RobotMap.speedKey, 0.75);
		}
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
