package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.sensor.LSM303DLHC_Accel;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 *
 */
public class AutoMecanumPos extends Command {
	double x, y, r;
	// lastTime = -1;
	// double vel;
	// double currentPos = 0;
	double pos;

	// LSM303DLHC_Accel accel;
	// BuiltInAccelerometer bla;

	public AutoMecanumPos(double x, double y, double r, double pos, double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
		requires(Robot.sensorSystem);
		this.x = x;
		this.y = y;
		this.r = r;
		this.pos = pos;
		setTimeout(time);
		// setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// accel = new LSM303DLHC_Accel();
		// bla = new BuiltInAccelerometer(Range.k4G);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if (lastTime == -1) {
		// lastTime = System.currentTimeMillis();
		// }
		//
		// double time = System.currentTimeMillis() - lastTime;
		// time = time / 1000;
		//
		// vel += accel.readRateY() * time * 32.1740485564304;
		//
		// currentPos += vel * time;
		Robot.sensorSystem.updateSensor();
		Robot.driveSystem.mecanumCartesian(x, y, r);
		// lastTime = System.currentTimeMillis();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.sensorSystem.getCurrenPos() >= pos || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
