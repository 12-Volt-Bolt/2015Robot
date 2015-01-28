package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLifterCommand extends Command {
	double lift, time;

	//
	//Will change this to work with position instead of time when we get the encoders installed
	//
    public AutoLifterCommand(double lift, double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lifterSystem);
        this.lift = lift;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lifterSystem.lift(lift);
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
