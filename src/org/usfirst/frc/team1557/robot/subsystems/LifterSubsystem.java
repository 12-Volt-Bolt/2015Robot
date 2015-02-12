package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.lifterKey;
import static org.usfirst.frc.team1557.robot.RobotMap.lifterTalonID;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.commands.LifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.hal.CanTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LifterSubsystem extends Subsystem {

	CANTalon lifter;

	DigitalInput limitUp;
	DigitalInput limitDown;

	public LifterSubsystem() {
		limitUp = new DigitalInput(1);
		limitDown = new DigitalInput(0);
		lifter = new CANTalon(lifterTalonID);
		lifter.enableBrakeMode(true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new LifterCommand());

	}

	/**
	 * Speed at which the lifter will move. Positive is up? Who knows anymore
	 * 
	 * @param speed
	 */
	public void lift(double speed) {
		// Inverse Speed
		// speed *= -1;
		if (getDownLimit()) {
			if (speed > 0) {
				speed = 0;
			}
		} else if (getUpLimit()) {
			if (speed < 0) {
				speed = 0;
			}
		}

		if (speed >= -0.1 && speed <= 0.1) {
			lifter.set(-0.10);
		} else {
			lifter.set(speed);
		}

		SmartDashboard.putNumber("Lifter Speed", speed);
		SmartDashboard.putNumber("Lifter Motor Throttle", getThrottle());
		SmartDashboard.putNumber("Lifter Motor Current", getCurrent());
		SmartDashboard.putNumber("Lifter Motor Voltage", getVoltage());
	}

	/**
	 *
	 * Will negate whatever speed you input. Positive is upwards and negative is
	 * downwards
	 * 
	 * @param speed
	 * 
	 */
	public void stack(double speed) {
		lifter.set(-speed);
	}

	/**
	 * Gets the inverted downwards limit switch.
	 * 
	 * @return Whether or not the limit switch is pressed. True being pressing
	 *         and false being unpressed.
	 */
	public boolean getDownLimit() {
		return !limitDown.get(); // || limitUp.get();
	}

	/**
	 * Gets the inverted upwards limit switch.
	 * 
	 * @return Whether or not the limit switch is pressed. True being pressing
	 *         and false being unpressed.
	 */
	public boolean getUpLimit() {
		// TODO: Hook this up to the correct limit switch;
		return !limitUp.get(); // || limitUp.get();
	}

	/**
	 * Gets the Current of the Lifter Talon
	 */
	public double getCurrent() {
		return lifter.getOutputCurrent();
	}

	/**
	 * Gets the Voltage of the Lifter Talon
	 */
	public double getVoltage() {
		return lifter.getOutputVoltage();
	}

	/**
	 * Gets the Throttle of the Lifter Talon
	 */
	public double getThrottle() {
		return lifter.get();
	}
}
