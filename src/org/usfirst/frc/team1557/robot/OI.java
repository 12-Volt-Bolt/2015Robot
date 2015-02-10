package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.commands.SetClampCommand;
import org.usfirst.frc.team1557.robot.commands.SetLockCommand;
import org.usfirst.frc.team1557.robot.commands.StackCommandGroup;
import org.usfirst.frc.team1557.robot.commands.ToggleClampCommand;
import org.usfirst.frc.team1557.robot.commands.ToggleLockCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick mainJoy = new Joystick(0);
	public static Joystick altJoy = new Joystick(1);
	Button yButton = new JoystickButton(mainJoy, RobotMap.yButton);
	Button mainRightClampButton = new JoystickButton(mainJoy,
			RobotMap.rightBumper);
	Button mainLeftClampButton = new JoystickButton(mainJoy,
			RobotMap.leftBumper);
	Button clampButton = new JoystickButton(altJoy, RobotMap.altClampButton);
	Button lockButton = new JoystickButton(altJoy, RobotMap.lockButton);
	Button stackButton = new JoystickButton(altJoy, RobotMap.stackButton);

	// Joystick = Drive
	// Triggers = Elevator
	// Bumpers = Arms

	/**
	 * Gets the value of the given axis for the main joystick.
	 * 
	 * @param axis
	 *            The axis to retrieve, i.e. RobotMap.leftXAxis
	 * @return The value, from -1 to 1 of the given axis.
	 */
	public static double mainAxis(int axis) {
		return mainJoy.getRawAxis(axis);
	}

	public static double altAxis(int axis) {
		return altJoy.getRawAxis(axis);
	}

	public OI() {
		// aButton.whenPressed(new ToggleClampCommand());
	}

	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	public void initialize() {
		// yButton.whenPressed(new StackCommandGroup());

		if (!RobotMap.override) {
			mainRightClampButton.whenPressed(new SetClampCommand(true));
			mainLeftClampButton.whenPressed(new SetClampCommand(false));
		}
		clampButton.whenPressed(new ToggleClampCommand());
		lockButton.whenPressed(new ToggleLockCommand());
		//stackButton.whenPressed(new StackCommandGroup());
	}

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
