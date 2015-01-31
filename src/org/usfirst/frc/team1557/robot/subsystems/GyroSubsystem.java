package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.sensor.L3GD20_Gyro;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Accel;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * If any commands require GyroSubsystem, they must call GyroSubsystem.update in
 * their execute method
 */
public class GyroSubsystem extends Subsystem {
	L3GD20_Gyro gyro = new L3GD20_Gyro();
	double gyroAngle = 0;

	
	LSM303DLHC_Accel accel = new LSM303DLHC_Accel();
	double vel = 0;
	double currentPos = 0;

	

	double lastTime = -1;
	/**
	 * Updates Gyro and Accel. Must be called in an execute or 
	 */
	public void updateSensor() {

		double dt;
		long now = System.currentTimeMillis();
		if (lastTime == -1) {
			lastTime = now;
		}
		
		//Calculates the change in time since last time
		dt = (now - lastTime) / 1000;
		
		//Writes the Gyro Angle
		gyroAngle += gyro.readRateZ() * dt;
		
		//Writes the Accelerometer acceleration
		vel += accel.readRateY() * dt * 32.1740485564304;
		
		//Writes the position that was calculated in the Y Axis
		currentPos += vel * dt;
		
		//Resets the time value for next time
		lastTime = now;
	}
	
	public void resetAccel(){
		currentPos = 0;
		vel = 0;
	}
	public void resetGyro() {
		gyroAngle = 0;
	}

	public double getAngleZ() {
		return gyroAngle;
	}
	public double getCurrenPos(){
		return currentPos;
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		setDefaultCommand(new Command() {

			{
				requires(Robot.gyroSystem);
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
