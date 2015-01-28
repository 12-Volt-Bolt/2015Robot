package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;

//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClampSubsystem extends Subsystem {
	// Solenoid piston = new Solenoid(0, 0);
	
	
	//Don't push to Robot if the Solenoid is not hooked up. If they aren't comment anything onvolving them out.
	DoubleSolenoid piston = new DoubleSolenoid(0, 0, 0);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void togglePiston(boolean button) {

		if (piston.get() == DoubleSolenoid.Value.kForward && button) {
			piston.set(DoubleSolenoid.Value.kReverse);
		} else if ((piston.get() == DoubleSolenoid.Value.kReverse && button)) {
			piston.set(DoubleSolenoid.Value.kReverse);
		}

		// piston.set(!piston.get());
	}

	// piston.set(!piston.get());
}
