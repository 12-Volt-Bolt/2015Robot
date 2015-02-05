package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Automatically drives forward with the given speeds, until the robot has moved
 * forward to the given position, or until time has been exceeded.
 */
public class AutoMecanumPos extends Command {
	/**
	 * Positional inputs
	 */
	double x, y, r;

	/**
	 * Desired distance
	 */
	double pos;
	/**
	 * 
	 * 
	 */
	double P = 0, I = 0, D = 0;
	/**
	 * 
	 */
	double preP = 0;
	/**
	 * 
	 * 
	 */
	double output = 0;
	/**
	 * 
	 * 
	 */
	double lastTime = -1;

	/**
	 * Creates the command.
	 * 
	 * @param x
	 *            Sideways movement speed
	 * @param y
	 *            Forward movement speed
	 * @param r
	 *            Rotation speed
	 * @param pos
	 *            Desired forward distance
	 * @param time
	 *            Maximum command time
	 */
	public AutoMecanumPos(double x, double y, double r, double pos, double time) {
		requires(Robot.driveSystem);
		requires(Robot.sensorSystem);

		this.x = x;
		this.y = y;
		this.r = r;

		this.pos = pos;

		setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.sensorSystem.resetAccel();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double dt;
		Robot.sensorSystem.updateSensor();
		long now = System.currentTimeMillis();
		if (lastTime == -1) {
			lastTime = now;
		}
		// Calculates the change in time since last time
		dt = (now - lastTime) / 1000;

		P = (pos - Robot.sensorSystem.getCurrentYPos()) / pos;
//		I = I + (P * dt);
//		D = (P - preP) / dt;
//		preP = P;
//		output = (0.6*P) + (0.2*I) + (0.2*D); 
		
		Robot.driveSystem.mecanumCartesian(x, P, r);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.sensorSystem.getCurrentYPos() >= pos || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
