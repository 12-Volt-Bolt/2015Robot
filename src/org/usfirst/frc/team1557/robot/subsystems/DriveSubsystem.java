package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
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
		// changeMode(1);
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
		double v_FrontLeft = r - y + x, v_FrontRight = r + y + x, v_BackLeft = r
				- y - x, v_BackRight = r + y - x;

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

	private void output() {
		// SmartDashboard Stuff
		SmartDashboard.putNumber("frontRightCurrent",
				frontRight.getOutputCurrent());
		SmartDashboard.putNumber("frontLeftCurrent",
				frontLeft.getOutputCurrent());
		SmartDashboard
				.putNumber("rearLeftCurrent", rearLeft.getOutputCurrent());
		SmartDashboard.putNumber("rearRightCurrent",
				rearRight.getOutputCurrent());

		SmartDashboard.putNumber("frontRightThrottle", frontRight.get());
		SmartDashboard.putNumber("frontLeftThrottle", frontLeft.get());
		SmartDashboard.putNumber("rearLeftThrottle", rearLeft.get());
		SmartDashboard.putNumber("rearRightThrottle", rearRight.get());

		SmartDashboard.putNumber("Left Joystick X",
				OI.mainJoy.getRawAxis(leftXAxis));
		SmartDashboard.putNumber("Left Joystick Y",
				OI.mainJoy.getRawAxis(leftYAxis));

		SmartDashboard.putNumber("Right Joystick X",
				OI.mainJoy.getRawAxis(rightXAxis));
		SmartDashboard.putNumber("Right Joystick Y",
				OI.mainJoy.getRawAxis(rightYAxis));

	}

	/**
	 * 1=Speed, 2=Voltage, 3=Current, 4=PercentVBus(Default)
	 */
	@SuppressWarnings("unused")
	private void changeMode(int mode) {
		if (mode == 4) {
			frontLeft.changeControlMode(CANTalon.ControlMode.PercentVbus);
			frontRight.changeControlMode(CANTalon.ControlMode.PercentVbus);
			rearLeft.changeControlMode(CANTalon.ControlMode.PercentVbus);
			rearRight.changeControlMode(CANTalon.ControlMode.PercentVbus);
		} else if (mode == 1) {
			frontLeft.changeControlMode(CANTalon.ControlMode.Speed);
			frontRight.changeControlMode(CANTalon.ControlMode.Speed);
			rearLeft.changeControlMode(CANTalon.ControlMode.Speed);
			rearRight.changeControlMode(CANTalon.ControlMode.Speed);
		} else if (mode == 2) {
			frontLeft.changeControlMode(CANTalon.ControlMode.Voltage);
			frontRight.changeControlMode(CANTalon.ControlMode.Voltage);
			rearLeft.changeControlMode(CANTalon.ControlMode.Voltage);
			rearRight.changeControlMode(CANTalon.ControlMode.Voltage);
		} else if (mode == 3) {
			frontLeft.changeControlMode(CANTalon.ControlMode.Current);
			frontRight.changeControlMode(CANTalon.ControlMode.Current);
			rearLeft.changeControlMode(CANTalon.ControlMode.Current);
			rearRight.changeControlMode(CANTalon.ControlMode.Current);
		}
	}
}
