package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.lifterKey;
import static org.usfirst.frc.team1557.robot.RobotMap.lifterTalonID;

import org.usfirst.frc.team1557.robot.commands.LifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.hal.CanTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LifterSubsystem extends Subsystem {

	CANTalon lifter;

	// DigitalInput limitUp;
	// DigitalInput limitDown;

	public LifterSubsystem() {
		// limitUp = new DigitalInput(1);
		// limitDown = new DigitalInput(0);
		lifter = new CANTalon(lifterTalonID);
		lifter.enableBrakeMode(true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new LifterCommand());

	}
	/**
	 * Speed at which the lifter will move. Positive is up
	 * 
	 * @param speed
	 */
	public void lift(double speed) {
		//Inverse Speed
		speed *= -1; 
		speed /= 2;
		// if (limitDown.get()) {
		// if (x < 0) {
		// x = 0;
		// }
		// } else if (limitUp.get()) {
		// if (x > 0) {
		// x = 0;
		// }
		// }

		// && !limitUp.get() && !limitDown.get()
		// && Robot.clampSystem.isClamp()

		if (speed >= -0.1 && speed <= 0.1) {

			lifter.set(-0.1);
		} else {
			lifter.set(speed * SmartDashboard.getNumber(lifterKey, 1));
		}
		
		SmartDashboard.putNumber("Lifter Speed", speed);
		SmartDashboard.putNumber("Lifter Motor Throttle", getThrottle());
		SmartDashboard.putNumber("Lifter Motor Current", getCurrent());
		SmartDashboard.putNumber("Lifter Motor Voltage", getVoltage());
	}

	/**
	 *
	 * Will negate whatever speed you input. Positive is upwards and negative is downwards
	 * 
	 * @param speed
	 * 
	 */
	public void stack(double speed) {
		
		lifter.set(-speed);
		
	}
	/**
	*Gets the Current of the Lifter Talon
	*/
	public double getCurrent(){
		return lifter.getOutputCurrent();
	}
	/**
	*Gets the Voltage of the Lifter Talon
	*/
	public double getVoltage(){
		return lifter.getOutputVoltage();
	}
	/**
	*Gets the Throttle of the Lifter Talon
	*/
	public double getThrottle(){
		return lifter.get();
	}
}
