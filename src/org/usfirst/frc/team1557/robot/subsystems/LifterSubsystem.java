package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.lifterKey;
import static org.usfirst.frc.team1557.robot.RobotMap.lifterTalonID;

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

	public void lift(double x) {

		if (limitDown.get()) {
			if (x < 0) {
				x = 0;
			}
		} else if (limitUp.get()) {
			if (x > 0) {
				x = 0;
			}
		}

		lifter.set(x * SmartDashboard.getNumber(lifterKey, 1));

		if (x > -0.05 && x < 0.05 && !limitUp.get() && !limitDown.get()) {
			
			lifter.set(0.1);
		}
	}

}
