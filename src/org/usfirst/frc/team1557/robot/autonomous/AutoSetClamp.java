package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Automatically toggles the clamp
 */
public class AutoSetClamp extends Command {
	
    private boolean state;
/**
 *  Sets the clamp to the specified state
 * @param state True is closed and false is opened
 */
	public AutoSetClamp(boolean state) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clampSystem);
        this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.clampSystem.setPiston(state);
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
}
