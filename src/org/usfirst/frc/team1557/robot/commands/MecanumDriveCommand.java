package org.usfirst.frc.team1557.robot.commands;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Mecanum drive from input from the main joystick.
 */
public class MecanumDriveCommand extends Command {
	double speed = 0.75;
	double xRamp = 0.1;
	double ySpeed = 0;
	double turn = 0;
	double currentR = 0;
	double currentY = 0;
	//double R = 0;
	//double preR = 0;
	//double limit = 0.1;

	public MecanumDriveCommand() {
		requires(Robot.driveSystem);
	}
	//GHOST COMPUTER!!!!
	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		ySpeed = (Math.abs(OI.mainAxis(leftYAxis)) > Math.abs(OI
				.mainAxis(rightYAxis))) ? OI.mainAxis(leftYAxis) : OI
				.mainAxis(rightYAxis);
		speed = SmartDashboard.getNumber(speedKey, speed);
		ySpeed = (Math.abs(ySpeed) > 0.09) ? ySpeed : 0;
		
		// TODO: create custom speed multiplier key
//		R = OI.mainAxis(rightXAxis) * speed;
//		R += 1;
//		if (R > preR) {
//			if (R - preR > limit) {
//				R += limit;
//			}
//		} else {
//			if (preR - R > limit) {
//				R -= limit;
//			}
//		}
//		R -= 1;
		
		//Rotate ramp
		double requestedR = OI.mainAxis(rightXAxis);
		if(Math.abs(requestedR) > 0.1) {
			currentR = currentR * 0.825 + requestedR * 0.175;
		} else {
			currentR = 0;
		}
		
		double requestedY = OI.mainAxis(leftYAxis);
		if(Math.abs(requestedY) > 0.1) {
			currentY = currentY * 0.825 + requestedY * 0.175;
		} else {
			currentY = 0;
		}
		
		Robot.driveSystem.mecanumCartesian(OI.mainAxis(leftXAxis) * (speed + xRamp),
				currentY * speed, currentR);//OI.mainAxis(rightXAxis));
		//preR = R;
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
