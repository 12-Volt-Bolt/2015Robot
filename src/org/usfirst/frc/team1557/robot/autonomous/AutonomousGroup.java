package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.L3GD20;
import org.usfirst.frc.team1557.robot.RoboGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGroup extends CommandGroup {
    public  AutonomousGroup() {
    	RoboGyro gyro;
    	gyro = new L3GD20();
    	addSequential(new AutoMecanumStrafe(1, 0.5, 2, gyro));
    }
}
