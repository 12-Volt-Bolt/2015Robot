package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LockSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DoubleSolenoid lockSolenoid = new DoubleSolenoid(
			RobotMap.lockSolenoidForward, RobotMap.lockSolenoidReverse);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void toggleLock() {
		if (lockSolenoid.get() == DoubleSolenoid.Value.kForward) {
			lockSolenoid.set(DoubleSolenoid.Value.kReverse);
		} else if (lockSolenoid.get() == DoubleSolenoid.Value.kReverse) {
			lockSolenoid.set(DoubleSolenoid.Value.kForward);
		} else {
			lockSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void setLock(boolean state) {
		if (state) {
			lockSolenoid.set(DoubleSolenoid.Value.kForward);
		} else {
			lockSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
}
