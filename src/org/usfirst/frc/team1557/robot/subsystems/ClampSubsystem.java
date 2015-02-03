package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for clamps
 */
public class ClampSubsystem extends Subsystem {
	// Don't push to Robot if the Solenoid is not hooked up. If they aren't
	// comment anything onvolving them out.
	DoubleSolenoid piston = new DoubleSolenoid(0, 1);

	public void initDefaultCommand() {
		
	}

	private boolean isClamp = false;

	/**
	 * Sets the state of the clamp.
	 * @param clamp True if the clamp is closed, false if the clamp is open.
	 */
	public void setPiston(boolean clamp) {
		if (clamp) {
			piston.set(DoubleSolenoid.Value.kForward);
		} else {
			piston.set(DoubleSolenoid.Value.kReverse);
		}

		this.isClamp = clamp;
	}

	/**
	 * Toggles the state of the clamp.
	 */
	public void togglePiston() {
		setPiston(!isClamp);
	}

	/**
	 * Gets whether the clamp is currently clamped.
	 * @return Whether the clamp is closed.
	 */
	public boolean isClamp() {
		return isClamp;
	}
}
