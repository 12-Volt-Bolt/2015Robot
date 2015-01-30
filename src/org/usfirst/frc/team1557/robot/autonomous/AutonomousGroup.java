package org.usfirst.frc.team1557.robot.autonomous;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGroup extends CommandGroup {
    public  AutonomousGroup() {
    	//Pick up
    	addSequential(new AutoLifterCommand(1, 2)); 
    	
    	//Move Forward
    	addSequential(new AutoMecanumTime(1, 1, 0, 1));
    	
    	//Drop Can
    	addSequential(new AutoToggleClamp());
    	
    	//Lower arm
    	addSequential(new AutoLifterCommand(-1, 2));
    	
    	//Grab stuff(tm)
    	addSequential(new AutoToggleClamp());
    	
    	//Lift arm back up
    	addSequential(new AutoLifterCommand(1, 1));
    	
    	//Rotate 
    	addSequential(new AutoMecanumTime(0, 0, 1, 0.5));
    	
    	//Drive into Autozone
    	addSequential(new AutoMecanumTime(1, 1, 0, 5));
    	
    	//Lower stuff(tm)
    	addSequential(new AutoLifterCommand(-1, 2));
    	
    	//Release stuff(tm)
    	addSequential(new AutoToggleClamp());
    	
    }
    
}
