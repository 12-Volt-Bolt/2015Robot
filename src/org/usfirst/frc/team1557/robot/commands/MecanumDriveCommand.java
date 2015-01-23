package org.usfirst.frc.team1557.robot.commands;

import static org.usfirst.frc.team1557.robot.RobotMap.rightXAxis;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MecanumDriveCommand extends Command {

    public MecanumDriveCommand() {
        // se requires() here to declare subsystem dependencies
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
        	Robot.driveSystem.mecanumDrive(OI.mainJoy.getMagnitude() / 2.0, OI.mainJoy.getDirectionDegrees() + 180, OI.mainJoy.getRawAxis(rightXAxis));

        	SmartDashboard.putNumber("magnitude", OI.mainJoy.getMagnitude() / 2.0);
        	SmartDashboard.putNumber("direction", OI.mainJoy.getDirectionDegrees() + 180);
        	SmartDashboard.putNumber("turn", OI.mainJoy.getRawAxis(rightXAxis));
    	
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
