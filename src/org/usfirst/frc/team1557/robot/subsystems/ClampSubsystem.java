package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClampSubsystem extends Subsystem {
	// Don't push to Robot if the Solenoid is not hooked up. If they aren't
	// comment anything onvolving them out.
	DoubleSolenoid piston = new DoubleSolenoid(0, 1);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	private boolean isClamp = false;

	public void togglePiston() {

		if (isClamp) {
			piston.set(DoubleSolenoid.Value.kReverse);
			isClamp = false;
		} else {
			piston.set(DoubleSolenoid.Value.kForward);
			isClamp = true;
		}
	}

	public boolean isClamp() {
		return isClamp;
	}
}
