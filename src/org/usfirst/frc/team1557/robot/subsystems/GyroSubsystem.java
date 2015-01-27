package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.L3GD20;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GyroSubsystem extends Subsystem {
	L3GD20 gyro = new L3GD20();
	
	public boolean isFinished() {
		return true;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new Command() {
			
			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void initialize() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void execute() {
				gyro.readRateZ();
				
			}
			
			@Override
			protected void end() {
				// TODO Auto-generated method stub
				
			}
		});
    }
}

