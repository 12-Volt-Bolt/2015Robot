package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the lifter using input from the triggers
 */
public class LifterCommand extends Command {

	public LifterCommand() {
		requires(Robot.lifterSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.lifterSystem.lift(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Math.abs(OI.altAxis(RobotMap.altYAxis)) > 0.075) {
			Robot.lifterSystem.lift(-OI.altAxis(RobotMap.altYAxis));
		}  else {
			Robot.lifterSystem.lift(0);
		}

	}
 
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lifterSystem.lift(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.lifterSystem.lift(0);
	}
}
