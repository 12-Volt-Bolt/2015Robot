
package org.usfirst.frc.team1557.robot.commands;
import static org.usfirst.frc.team1557.robot.RobotMap.*;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;



/**
 *
 */
public class TankDriveCommand extends Command {
	
	public TankDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
	}
	//He will yell at me about how I am doing 
	// Called just before this Command runs the first time
	protected void initialize() {
		
		

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveSystem.mixDrive(OI.mainJoy.getRawAxis(leftXAxis), OI.mainJoy.getRawAxis(leftYAxis),OI.mainJoy.getRawAxis(rightXAxis),OI.mainJoy.getRawAxis(rightYAxis));
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
