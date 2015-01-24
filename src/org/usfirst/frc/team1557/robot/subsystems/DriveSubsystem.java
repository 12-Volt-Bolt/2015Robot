package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	public static CANTalon frontLeft;
	public static CANTalon rearLeft;
	public static CANTalon frontRight;
	public static CANTalon rearRight;
	RobotDrive drive;

	public DriveSubsystem() {
		frontLeft = new CANTalon(frontLeftTalonID);
		rearLeft = new CANTalon(rearLeftTalonID);
		frontRight = new CANTalon(frontRightTalonID);
		rearRight = new CANTalon(rearRightTalonID);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MecanumDriveCommand());
	}

	// TankDrive
	public void tankDrive(double x, double y) {
		if (drive == null) {
			drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		}
		drive.tankDrive(x, y);
	}

	public void mecanumCartesian(double x, double y, double r) {
		double v_FrontLeft = r - y + x, 
				v_FrontRight = r + y + x, 
				v_BackLeft = r - y - x, 
				v_BackRight = r + y - x;

		// This segment below gets the largest value or 1 so we can divide by
		// it,
		// making sure that the values going to the motor never exceed the -1.0
		// to 1.0
		// range the motor controllers expect
		double f = 1;
		if (Math.abs(v_FrontLeft) > f)
			f = Math.abs(v_FrontLeft);
		if (Math.abs(v_FrontRight) > f)
			f = Math.abs(v_FrontRight);
		if (Math.abs(v_BackLeft) > f)
			f = Math.abs(v_BackLeft);
		if (Math.abs(v_BackRight) > f)
			f = Math.abs(v_BackRight);

		// Scale
		f = f / SmartDashboard.getNumber("speedMultiplier", 0.75);

		frontLeft.set(v_FrontLeft / f);
		frontRight.set(v_FrontRight / f);
		rearLeft.set(v_BackLeft / f);
		rearRight.set(v_BackRight / f);

	}

}
