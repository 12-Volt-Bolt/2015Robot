package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Automatically drives the robot for the given time.
 */
public class AutoMecanumTime extends Command {
	double x, y, r, time;

	/**
	 * Creates this command
	 * @param  x    Sideways movement speed
	 * @param  y    Forwards movement speed
	 * @param  r    Rotation speed
	 * @param  time Time to move
	 */
	public AutoMecanumTime(double x, double y, double r, double time) {
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
		/* PID Logic
		 * 
		 *  P = Kp * Kerr
		 * Kerr = Target value - Current value
		 * Kp = Sensitivity value
		 * 
		 * 	I = Ki * Ierr
		 * Ierr = Previous Ierr + Kerr
		 * Ki = Sensitivity value
		 * 
		 * 	D = Derr * Kd
		 * Derr = Kerr - Previous Kerr
		 * Kd = Sensitivity value
		 * 
		 * All values are based off of Kerr
		 */
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
