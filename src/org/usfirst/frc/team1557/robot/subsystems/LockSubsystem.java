package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LockSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid lockSolenoid = new Solenoid(RobotMap.positionLockSolenoid);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void toggleLock(){
    	lockSolenoid.set(!lockSolenoid.get());
    }
    public void setLock(boolean state){
    	lockSolenoid.set(state);
    }
}

