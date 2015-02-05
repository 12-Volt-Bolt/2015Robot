package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.frontLeftTalonID;
import static org.usfirst.frc.team1557.robot.RobotMap.frontRightTalonID;
import static org.usfirst.frc.team1557.robot.RobotMap.leftXAxis;
import static org.usfirst.frc.team1557.robot.RobotMap.leftYAxis;
import static org.usfirst.frc.team1557.robot.RobotMap.rearLeftTalonID;
import static org.usfirst.frc.team1557.robot.RobotMap.rearRightTalonID;
import static org.usfirst.frc.team1557.robot.RobotMap.rightXAxis;
import static org.usfirst.frc.team1557.robot.RobotMap.rightYAxis;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for drive train
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
		// changeMode(CANTalon.ControlMode.Speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MecanumDriveCommand());
	}

	/**
	 * Tank drive
	 * @param left Speed for the left side, from -1 to 1.
	 * @param right Speed for the right side, from -1 to 1.
	 */
	public void tankDrive(double left, double right) {
		if (drive == null) {
			drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		}
		drive.tankDrive(left, right);
	}

	/**
	 * Mecanum drive
	 * @param x Lateral translation, strafing left to right.
	 * @param y Forward translation, moving forward and backward.
	 * @param r Speed of rotation
	 */
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
		
		output();

	}

	/**
	 * Sets SmartDashboard values based on the state of the subsystem
	 */
	private void output() {
		// SmartDashboard Stuff
		SmartDashboard.putNumber("frontRightCurrent", frontRight.getOutputCurrent());
		SmartDashboard.putNumber("frontLeftCurrent", frontLeft.getOutputCurrent());
		SmartDashboard.putNumber("rearLeftCurrent", rearLeft.getOutputCurrent());
		SmartDashboard.putNumber("rearRightCurrent", rearRight.getOutputCurrent());

		SmartDashboard.putNumber("frontRightThrottle", frontRight.get());
		SmartDashboard.putNumber("frontLeftThrottle", frontLeft.get());
		SmartDashboard.putNumber("rearLeftThrottle", rearLeft.get());
		SmartDashboard.putNumber("rearRightThrottle", rearRight.get());

		SmartDashboard.putNumber("Left Joystick X", OI.mainJoy.getRawAxis(leftXAxis));
		SmartDashboard.putNumber("Left Joystick Y", OI.mainJoy.getRawAxis(leftYAxis));

		SmartDashboard.putNumber("Right Joystick X", OI.mainJoy.getRawAxis(rightXAxis));
		SmartDashboard.putNumber("Right Joystick Y", OI.mainJoy.getRawAxis(rightYAxis));
	}

	/**
	 * Changes the control mode for all motors.
	 * @param mode The CANTalon ControlMode to set all motors to.
	 */
	private void changeMode(CANTalon.ControlMode mode) {
		frontLeft.changeControlMode(mode);
		frontRight.changeControlMode(mode);
		rearLeft.changeControlMode(mode);
		rearRight.changeControlMode(mode);
	}
}
