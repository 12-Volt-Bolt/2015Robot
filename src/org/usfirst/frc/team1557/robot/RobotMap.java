package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    	public static int leftXAxis = 0;
    	public static int leftYAxis = 1;
    	public static int leftTrigger = 2;
    	public static int rightTrigger = 3;
    	public static int rightXAxis = 4;
    	public static int rightYAxis = 5;
    	
    	public static int yButton = 4;
    	public static int leftBumper = 5;
    	public static int rightBumper = 6;

    	public static boolean driveBoo = true;
    	
    	
    	
    	//Talon IDs
    	public static int frontLeftTalonID = 1;
    	public static int rearLeftTalonID = 3;//2
    	public static int frontRightTalonID = 2;//3
    	public static int rearRightTalonID = 4;
    	
    	
    	public static int lifterTalonID = 5;
    	    	public RobotMap(){

    	}
    	/*
    	frontLeft = new CANTalon(1);
		backLeft = new CANTalon(2);
		frontRight = new CANTalon(3);
		backRight = new CANTalon(4);
    	 */
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
}
