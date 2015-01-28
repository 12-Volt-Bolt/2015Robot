package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.sensor.L3GD20;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GyroSubsystem extends Subsystem {
	L3GD20 gyro = new L3GD20();
	double gyroAngle = 0;
	public boolean isFinished() {
		return true;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new Command() {
			double lastTime = -1;
			double time;
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
				if(lastTime == -1){
				lastTime = System.currentTimeMillis();
				}
				time = (System.currentTimeMillis() - lastTime) / 1000;
				gyroAngle += gyro.readRateZ() * time;
				
				
			}
			
			@Override
			protected void end() {
				// TODO Auto-generated method stub
				
			}
		});
    }
}

