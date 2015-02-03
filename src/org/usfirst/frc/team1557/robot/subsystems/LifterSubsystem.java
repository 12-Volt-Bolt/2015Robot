package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.lifterKey;
import static org.usfirst.frc.team1557.robot.RobotMap.lifterTalonID;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.commands.LifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
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
		setDefaultCommand(new LifterCommand());
	}

	/**
	 * Moves the lifter at the given speed.
	 * Negative speed values lift up, while positive values lift down.
	 * @param speed The speed to move the lifter at, from -1 to 1. 
	 */
	public void lift(double speed) {

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
		
		SmartDashboard.putNumber("Lifter Motor Speed", lifter.get());
	}

	/**
	 *
	 * Will negate whatever speed you input. Therefore, the speed should be
	 * positive to go down.
	 * 
	 * @param speed
	 * 
	 */
	public void stack(double speed) {
		lifter.set(-speed);
	}
}
