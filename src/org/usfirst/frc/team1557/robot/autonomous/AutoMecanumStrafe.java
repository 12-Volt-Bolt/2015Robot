package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Strafes the robot.
 * Uses gyroscope to maintain it's original rotation.
 */
public class AutoMecanumStrafe extends Command {
	double x, y;
	double startingAngle;
	double angleDif;
	double time;

	/**
	 * Rotation correction will be at it's maximum if the robot is this far off the desired rotation.
	 */
	public double maxTurnAngle = 90;

	/**
	 * Creates this command.
	 * @param  x    Sideways movement speed
	 * @param  y    Forward movement speed
	 * @param  time Time to run
	 */
	public AutoMecanumStrafe(double x, double y, double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
		requires(Robot.sensorSystem);
	
		this.x = x;
		this.y = y;

		this.time = time;
	}



	/**
	 * Determines the direction and speed to rotate the robot to the desired angle
	 * @param  current The current angle
	 * @param  dest    The desired angle
	 * @return         The rotation speed desired to rotate the robot.
	 */
	private double direction(double current, double dest) {
		double angleDif;
		angleDif = wrap(dest - current);

		angleDif = wrap(angleDif - 0);
		if (angleDif > 180) {
			angleDif = angleDif - 360;
		}

		angleDif = (Math.min(maxTurnAngle, Math.max(-maxTurnAngle, angleDif)));
		return angleDif / maxTurnAngle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.sensorSystem.resetGyro();

		startingAngle = Robot.sensorSystem.getAngleX();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		
		
		Robot.driveSystem.mecanumCartesian(x, y,
				direction(Robot.sensorSystem.getAngleX(), startingAngle));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timeSinceInitialized() >= time;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
