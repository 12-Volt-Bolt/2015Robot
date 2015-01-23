package org.usfirst.frc.team1557.robot.subsystems;
import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.commands.LifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.hal.CanTalonSRX;

/**
 *
 */
public class LifterSubsystem extends Subsystem {

	//CANTalon lifter;

	public LifterSubsystem() {
		//lifter = new CANTalon(lifterTalonID);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new LifterCommand());

	}

	public void lift(double x) {	
		//lifter.set(x);
		
	}
	
}
