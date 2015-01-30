package org.usfirst.frc.team1557.robot.subsystems;

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
	LSM303DLHC_Accel accel = new LSM303DLHC_Accel();
	double gyroAngle = 0;

	double lastTime = -1;

	/**
	 * Compound update
	 */
	public void update() {

		double dt;
		long now = System.currentTimeMillis();

		if (lastTime == -1) {
			lastTime = now;
		}
		dt = (now - lastTime) / 1000;
		gyroAngle += gyro.readRateZ() * dt;

		lastTime = now;
	}

	public void reset() {
		gyroAngle = 0;
	}

	public double getAngleZ() {
		return gyroAngle;
	}

	public void complementUpdate() {

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		setDefaultCommand(new Command() {
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
				update();
			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub

			}
		});
	}
}
