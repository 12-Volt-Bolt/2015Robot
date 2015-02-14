package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.sensor.L3GD20_Gyro;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Accel;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Magneto;

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

	enum Orientation {

	}

	L3GD20_Gyro gyro = null;
	LSM303DLHC_Accel accel = null;
	LSM303DLHC_Magneto magneto = null;
	double fil = 0.5;
	double nosePass = 0;
	double dt;
	
	double gyroSensitivity = 1;	
	double accelSensitivity = 1;
	
	/**
	 * Accumulated z angle
	 */
	double gyroAngle = 0;
	double magAngle = 0;

	public double readRateX() {
		return accel.readRateX();
	}

	public double readRateY() {
		return accel.readRateY();
	}

	public double readRateZ() {
		return accel.readRateZ();
	}

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
	 * Updates Gyro and Accelerometer. Must be called continuously, i.e. in
	 * execute.
	 */
	
	/**
	 * Turns any angle in degrees to a positive angle in the interval [0, 360)
	 * @param  angle Angle in degrees
	 * @return       The angle, normalized
	 */
	private double wrap(double angle) {
		angle %= 360;
		if (angle < 0)
			angle += 360;
		return angle;
	}
	
	Thread sensorThread = new Thread(new Runnable() {

		@Override
		public void run() {
			while (true) {
				updateSensor();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.err.println("You done messed up");
					e.printStackTrace();
					break;
				}
			}
		}

	});

	private void updateSensor() {
		int counter = 0;
		if (gyro == null || accel == null)
			return;

		while (gyro.available()) {
			// long now = System.currentTimeMillis();
			// if (lastTime == -1) {
			// lastTime = now;
			// }

			// Calculates the change in time since last time
			// dt = (now - lastTime) / 1000.0;

			// Writes the Gyro Angle

			gyroAngle += gyro.readRateX() / 190; // dt;
			
			if( Math.abs(gyroAngle) > 360 ) {
				wrap(gyroAngle);
			}

			// Writes the Accelerometer acceleration
			// nosePass = (1 - fil) * nosePass + fil * vel;
			// vel = vel - nosePass;
			// vel += accel.readRateY() * dt * 32.1740485564304;

			// Writes the position that was calculated in the Y Axis
			// currentPos += vel * dt;

			// Output the values onto the SmrtDshbrd
			output();

			// Resets the time value for next time
			// lastTime = now;
			if (counter >= 30) {
				counter = 0;
				break;
			}
		}
	}

	/**
	 * Applies the Complementary Filter to the Gyroscope & Accelerometer Data
	 * 
	 * Gyroscope X Axis requires
	 *  Y Axis Accelerometer Data for accelRAW1 
	 *  Z Axis Accelerometer Data for accelRAW2
	 * 
	 * Gyroscope Y Axis requires
	 *  X Axis Accelerometer Data for accelRAW1 
	 *  Z Axis Accelerometer Data for accelRAW2
	 * 
	 * Gyroscope Z Axis requires
	 *  X Axis Accelerometer Data for accelRAW1 
	 *  Y Axis Accelerometer Data for accelRAW2
	 * 
	 * @param currAngle - The Current Angle for the Intended Axis
	 * @param gyroRAW - Gyroscope Input
	 * @param accelRAW1 - Accelerometer Data from Axis 1
	 * @param accelRAW2 - Accelerometer Data from Axis 2
	 */
	public double complementaryFilter(double currAngle, double gyroRAW, double accelRAW1, double accelRAW2) {
		double gyroData = gyroRAW / gyroSensitivity;
		double accelData = Math.atan2( accelRAW1, accelRAW2 ) * 180 / Math.PI;
		
		currAngle = 0.98 * ( currAngle + gyroData * dt ) + 0.02 * accelData;
		return currAngle;
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
	 * 
	 * @return The accumulated angle of the gyro.
	 */
	public double getAngleX() {
		return gyroAngle;
	}

	/**
	 * Gets the accumulated y position of the accelerometer
	 * 
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
		SmartDashboard.putNumber("Magneto X", magneto.readRateX());
		SmartDashboard.putNumber("Magneto Y", magneto.readRateY());
		SmartDashboard.putNumber("Magneto Z", magneto.readRateZ());
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
			magneto = new LSM303DLHC_Magneto();
			sensorThread.start();
			sensorThread.setName("Sensor Thread");
		}
	}

	public void initDefaultCommand() {

	}
}
