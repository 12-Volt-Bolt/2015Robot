package org.usfirst.frc.team1557.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClampSubsystem extends Subsystem {
	Solenoid piston = new Solenoid(0, 0);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void togglePiston() {
		piston.set(!piston.get());
	}
}
