package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.sensor.L3GD20_Gyro;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Accel;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * If any commands require SensorSubsystem, they must call
 * SensorSubsystem.update in their execute method
 */
public class SensorSubsystem extends Subsystem {
	L3GD20_Gyro gyro = null;
	LSM303DLHC_Accel accel = null;
	double gyroAngle = 0;

	double vel = 0;
	double currentPos = 0;
	int i = 0;
	double lastTime = -1;

	/**
	 * Updates Gyro and Accel. Must be called in an execute or
	 */
	public void updateSensor() {
		if (gyro == null || accel == null)
			return;

		double dt;
		long now = System.currentTimeMillis();
		if (lastTime == -1) {
			lastTime = now;
		}

		// Calculates the change in time since last time
		dt = (now - lastTime) / 1000;

		// Writes the Gyro Angle
		gyroAngle += gyro.readRateZ() * dt;

		// Writes the Accelerometer acceleration
		vel += accel.readRateY() * dt * 32.1740485564304;

		// Writes the position that was calculated in the Y Axis
		currentPos += vel * dt;

		// Output the values onto the SmrtDshbrd
		output();

		// Resets the time value for next time
		lastTime = now;
	}

	public void resetAccel() {
		currentPos = 0;
		vel = 0;
	}

	public void resetGyro() {
		gyroAngle = 0;
	}

	public double getAngleZ() {
		return gyroAngle;
	}

	public double getCurrentYPos() {
		return currentPos;
	}

	private void output(){ 
		
		SmartDashboard.putNumber("Count", i++);
		SmartDashboard.putNumber("Gyro Angle", gyroAngle);
		SmartDashboard.putNumber("Accelerometer Y Position", currentPos);

	}

	private boolean isinit = false;

	public void init() {
		if (!isinit) {
			isinit = true;
			gyro = new L3GD20_Gyro();
			accel = new LSM303DLHC_Accel();
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		setDefaultCommand(new Command() {

			{
				requires(Robot.sensorSystem);
			}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void initialize() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void execute() {
				updateSensor();
			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub

			}
		});
	}
}
