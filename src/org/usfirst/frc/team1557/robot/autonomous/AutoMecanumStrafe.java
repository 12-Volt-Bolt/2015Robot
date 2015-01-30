package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMecanumStrafe extends Command {
	double x, y;
	double startingAngle;
	double angleDif;
	double time;

	public double maxTurnAngle = 90;

	public AutoMecanumStrafe(double x, double y, double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
		requires(Robot.gyroSystem);
		this.x = x;
		this.y = y;

		this.time = time;
	}

	private double wrap(double angle) {
		angle %= 360;
		if (angle < 0)
			angle += 360;
		return angle;
	}

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
		Robot.gyroSystem.reset();
		startingAngle = Robot.gyroSystem.getAngleZ();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gyroSystem.update();
		Robot.driveSystem.mecanumCartesian(x, y,
				direction(Robot.gyroSystem.getAngleZ(), startingAngle));
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
