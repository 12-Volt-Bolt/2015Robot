package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoBumpClimbDrive extends Command {
	/**
	 * AutoBumpClimbDrive | ABCD
	 */
	public AutoBumpClimbDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);

	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// TODO: finish values
		double thresh = 0.2;
		double speed = 0.5;
		
		while (isAprox( Robot.sensorSystem.readRateX(), 1, thresh)) {
			Robot.driveSystem.mecanumCartesian(0, speed, 0);
			Timer.delay(0.02);
			
		}
		while (isAprox( Robot.sensorSystem.readRateX(), 0.7, thresh)) {
			Robot.driveSystem.mecanumCartesian(0, speed, 0);
			Timer.delay(0.02);
		}
		while (isAprox( Robot.sensorSystem.readRateX(), 1, thresh)) {
			Robot.driveSystem.mecanumCartesian(0, speed, 0);
			Timer.delay(0.02);
		}
		while (isAprox( Robot.sensorSystem.readRateX(), 0.7, thresh)) {
			Robot.driveSystem.mecanumCartesian(0, speed, 0);
			Timer.delay(0.02);
		}
		Robot.driveSystem.mecanumCartesian(0, speed, 0);
		Timer.delay(0.5);
		Robot.driveSystem.mecanumCartesian(0, 0, 0);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	/**
	 * 
	 * 
	 * @param a Starting value
	 * @param b Value to subtract
	 * @param scale The threshold
	 * @return Whether or not a - b is smaller or equal to scale
	 */
	
	private boolean isAprox(double a, double b, double scale) {
		return Math.abs(a - b) <= scale;
	}
}
