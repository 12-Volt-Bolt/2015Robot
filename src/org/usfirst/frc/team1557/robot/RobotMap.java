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
	public static int leftZAxis = 3;
	public static int leftTrigger = 2;
	public static int rightTrigger = 3;
	public static int rightXAxis = 4;
	public static int rightYAxis = 5;
	// Joystick Buttons
//	public static int yButton = 4;
//	public static int aButton = 1;
//	public static int bButton = 2;
//	public static int leftBumper = 5;
//	public static int rightBumper = 6;
	// Alt Joystick Buttons
	public static int lockButton = 2;
	public static int altClampButton = 1;
	public static int altDislodgeButton = 7;
	public static int overrideButton = 10;
	public static int stackButton = 3;
	// Alt Joystick Axes
	public static int altYAxis = 1;
	public static int altZAxis = 2;
	// Mecanum Talon IDs
	public static int frontLeftTalonID = 0;
	public static int rearLeftTalonID = 2;
	public static int frontRightTalonID = 1;
	public static int rearRightTalonID = 3;
	// Lifter Talon ID
	public static int lifterTalonID = 4;
	// Solenoid IDs
	public static int clampSolenoidForward = 0;
	public static int clampSolenoidReverse = 1;
	public static int lockSolenoidForward = 2;
	public static int lockSolenoidReverse = 3;

	// SmartDashboard Keys
	public static String lifterKey = "Lifter Scale Speed";
	public static String speedKey = "Drive Scale Speed";

	// Autonomous lifter Settings
	public static String lifterTime = "Auto Lifter Time";
	public static String lifterSpeed = "Auto Lifter Speed";

	// Misc global values
	public static boolean override = false;
	public static boolean dislodge = false;
	// public static double n(String name, double def) {
	// try {
	//
	// return SmartDashboard.getNumber(name);
	// } catch (Exception e) {
	// SmartDashboard.putNumber(name, def);
	// return def;
	// }
	//
	// }
}
