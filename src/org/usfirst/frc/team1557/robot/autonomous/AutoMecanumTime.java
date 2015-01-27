package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMecanumTime extends Command {
	double x, y, r, time;

	public AutoMecanumTime(double x, double y, double r, double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
		this.x = x;
		this.y = y;
		this.r = r;

		setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.driveSystem.mecanumCartesian(x, y, r);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
