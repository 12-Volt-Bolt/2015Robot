package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Joystick Axis IDs
	public static int leftXAxis = 0;
	public static int leftYAxis = 1;
	public static int leftTrigger = 2;
	public static int rightTrigger = 3;
	public static int rightXAxis = 4;
	public static int rightYAxis = 5;

	// Joystick Buttons
	public static int yButton = 4;
	public static int aButton = 1;
	public static int bButton = 2;
	public static int leftBumper = 5;
	public static int rightBumper = 6;

	// Mecanum Talon IDs
	public static int frontLeftTalonID = 0;
	public static int rearLeftTalonID = 2;
	public static int frontRightTalonID = 1;
	public static int rearRightTalonID = 3;

	// Lifter Talon ID
	public static int lifterTalonID = 4;
	
	// SmartDashboard Keys
	public static String lifterKey = "Lifter Scale Speed";
	public static String speedKey = "Drive Scale Speed";
	
	//Autonomous lifter Settings
	public static String lifterTime = "Auto Lifter Time";
	public static String lifterSpeed = "Auto Lifter Speed";
	
	
	{
	SmartDashboard.putNumber(lifterSpeed, 1);
	SmartDashboard.putNumber(lifterTime, 1);
	}
	
}
