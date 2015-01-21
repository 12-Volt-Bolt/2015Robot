package org.usfirst.frc.team1557.robot.autonomous;

import static org.usfirst.frc.team1557.robot.Robot.*;

import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveFoward extends Command {

	private double x;

	public AutoDriveFoward(double x) {
		this.x = x;
		requires(driveSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		
		DriveSubsystem.frontLeft.set(0.3);
		Timer.delay(2);
		DriveSubsystem.frontLeft.set(0);
		
		DriveSubsystem.rearLeft.set(0.3);
		Timer.delay(2);
		DriveSubsystem.rearLeft.set(0);
		
		DriveSubsystem.frontRight.set(0.3);
		Timer.delay(2);
		DriveSubsystem.frontRight.set(0);
		
		DriveSubsystem.rearRight.set(0.3);
		Timer.delay(2);
		DriveSubsystem.rearRight.set(0);
		
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
