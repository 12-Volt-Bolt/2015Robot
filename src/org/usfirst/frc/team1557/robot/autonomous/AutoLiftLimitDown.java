package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLiftLimitDown extends Command {
	boolean finish;

	public AutoLiftLimitDown() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lifterSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lifterSystem.stack(-0.65);
		if (Robot.lifterSystem.getDownLimit()) {
			Robot.lifterSystem.stack(0);
			finish = true;
		}
		;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finish;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
