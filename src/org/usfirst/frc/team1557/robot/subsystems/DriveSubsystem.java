package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	public static CANTalon frontLeft;
	public static CANTalon rearLeft;
	public static CANTalon frontRight;
	public static CANTalon rearRight;
	RobotDrive drive;
	// This should NEVER exceed 1
	public static double motorScale = 1;

	public DriveSubsystem() {
		frontLeft = new CANTalon(frontLeftTalonID);
		rearLeft = new CANTalon(rearLeftTalonID);
		frontRight = new CANTalon(frontRightTalonID);
		rearRight = new CANTalon(rearRightTalonID);
		
		
		 frontLeft.reverseOutput(true);
		 rearLeft.reverseOutput(true);
		/*
		 * frontLeft = new CANTalon(1); backLeft = new CANTalon(2); frontRight =
		 * new CANTalon(3); backRight = new CANTalon(4);
		 */
		drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}

	public void initDefaultCommand() {

		setDefaultCommand(new MecanumDriveCommand());

	}

	// TankDrive
	public void tankDrive(double x, double y) {
		if (drive == null) {
			//drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		}
		drive.tankDrive(x, y);
	}

	// //TankDrive
	// public void tankDrive(double x, double y) {
	// drive.tankDrive(x, y);
	// }

	// IT DON'T WORK
	public void mecanumDrive(double magnitude, double angleDegrees, double turnSpeed) {
		// y = forward movement
		//x = strafe right 
		//z = rotate clockwise
		
		//frontLeft = y + x + z
		//frontRight = y -x - z
		//rearLeft = y - x + z
		//rearRight = y + x -z
		
		//if(fr || fl || rl||rr|| > 1?){
		// 
		//	}
		//drive.mecanumDrive_Polar(x , y, rotation);

		Robot.mech.drive(
				(float) magnitude, 
				(float) angleDegrees, 
				(float) turnSpeed, 
				false);
	}

	public void mixDrive(double leftX, double leftY, double rightX,
			double rightY) {
		double averageX = (leftX + rightX)/2.0;
		
		frontLeft.set((-leftY - averageX) * motorScale);
		rearLeft.set((-leftY + averageX) * motorScale);
		frontRight.set((rightY - averageX) * motorScale);
		rearRight.set((rightY + averageX) * motorScale);

	}

	// public void mixDrive(double leftX, double leftY, double rightX, double
	// rightY){
	// double averageX = (leftX + rightX) / 2;
	// frontLeft.set((-leftY - averageX) * motorScale);
	// rearLeft.set((-leftY + averageX) * motorScale);
	// frontRight.set((rightY - averageX) * motorScale);
	// rearRight.set((rightY + averageX) * motorScale);
	//
}
