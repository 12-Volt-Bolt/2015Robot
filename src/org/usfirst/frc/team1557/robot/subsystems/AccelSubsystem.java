package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Accel;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * If another command requires AccelSubsystem, they must call updateAccel in its execute method
 * 
 */
public class AccelSubsystem extends Subsystem {
	LSM303DLHC_Accel accel = new LSM303DLHC_Accel();
	double vel = 0;
	double currentPos = 0;

	double lastTime = -1;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	
	/**
	 * Updates the Accelerometer.
	 * 
	 */
	public void updateAccel() {

		double dt;
		long now = System.currentTimeMillis();
		if (lastTime == -1) {
			lastTime = now;
		}

		dt = (now - lastTime) / 1000;

		vel += accel.readRateY() * dt * 32.1740485564304;

		currentPos += vel * dt;
		lastTime = now;

	}

	public double getPos() {
		return currentPos;
	}

	public void reset() {
		currentPos = 0;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Command() {

			@Override
			protected void initialize() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void execute() {
				updateAccel();

			}

			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub

			}

		});
	}
}
