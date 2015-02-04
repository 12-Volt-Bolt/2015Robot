package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.sensor.L3GD20_Gyro;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Accel;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for accelerometer and gyroscope
 * 
 * If any commands require SensorSubsystem, they must call
 * SensorSubsystem.updateSensor in their execute method
 */
public class SensorSubsystem extends Subsystem {
	L3GD20_Gyro gyro = null;
	LSM303DLHC_Accel accel = null;
	double fil = 0.2;
	double nosePass = 0;
	double dt;
	/**
	 * Accumulated z angle
	 */
	double gyroAngle = 0;

	/**
	 * Accumulated velocity
	 */
	double vel = 0;

	/**
	 * Accumulated position
	 */
	double currentPos = 0;

	/**
	 * Timestamp of the last tick
	 */
	long lastTime = -1;

	/**
	 * Updates Gyro and Accelerometer. Must be called continuously, i.e. in execute.
	 */
	public void updateSensor() {
		if (gyro == null || accel == null)
			return;

		
		long now = System.currentTimeMillis();
		if (lastTime == -1) {
			lastTime = now;
		}

		// Calculates the change in time since last time
		dt = (now - lastTime) / 1000.0;

		// Writes the Gyro Angle
		gyroAngle += gyro.readRateZ() * dt;

		// Writes the Accelerometer acceleration
//		nosePass = (1 - fil) * nosePass + fil * vel;
//		vel = vel - nosePass;
		vel += accel.readRateY() * dt * 32.1740485564304;

		// Writes the position that was calculated in the Y Axis
		currentPos += vel * dt;

		// Output the values onto the SmrtDshbrd
		output();
		
		// Resets the time value for next time
		lastTime = now;
	}

	/**
	 * Resets the accumulated position of the accelerometer
	 */
	public void resetAccel() {
		currentPos = 0;
		vel = 0;
	}

	/**
	 * Resets the accumulated angle of the gyro
	 */
	public void resetGyro() {
		gyroAngle = 0;
	}

	/**
	 * Gets the accumulated angle of the gyro.
	 * @return The accumulated angle of the gyro.
	 */
	public double getAngleZ() {
		return gyroAngle;
	}

	/**
	 * Gets the accumulated y position of the accelerometer
	 * @return The accumulated forward position of the acceleromter
	 */
	public double getCurrentYPos() {
		return currentPos;
	}

	/**
	 * Sets SmartDashboard values based on the state of the subsystem
	 */
	private void output() {
		SmartDashboard.putNumber("DT", dt);
		SmartDashboard.putNumber("Gyro Angle", gyroAngle);
		SmartDashboard.putNumber("Accelerometer Y Position", currentPos);
	}

	/**
	 * Whether this subsystem has been initialzed yet.
	 */
	private boolean isinit = false;

	/**
	 * Creates the gyroscope and accelerometer.
	 */
	public void init() {
		if (!isinit) {
			isinit = true;
			gyro = new L3GD20_Gyro();
			accel = new LSM303DLHC_Accel();
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Command() {
			{
				requires(Robot.sensorSystem);
			}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void execute() {
				updateSensor();
			}

			@Override
			protected void interrupted() {}

			@Override
			protected void initialize() {}

			@Override
			protected void end() {}
		});
	}
}
