package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.AutonomousPlans;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team1557.robot.commands.ToggleClampCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FancyAutoGroup extends CommandGroup {

	public FancyAutoGroup() {
		switch ((AutonomousPlans) Robot.autoChooser.getSelected()) {
		case BUMPLESS_DRIVE_ONLY:
			// Probably Done!
			addSequential(new AutoMecanumTime(0, 0.5, 0, 0.3));
			break;
		case BUMP_DRIVE_ONLY:

			break;
		case CENTER_BIN_ONLY:

			break;
		case CENTER_BOTH:

			break;
		case CENTER_LEFT_TOTE:

			break;
		case CENTER_RIGHT_TOTE:

			break;
		case LEFT_BIN_ONLY:

			break;
		case LEFT_BOTH:
			addSequential(new AutoLockCommand(false));
			addSequential(new AutoSetClamp(true));
			addSequential(new WaitCommand(0.25));
			// Lift the bin up
			addSequential(new AutoLifterCommand(0.71, 1));
			addSequential(new WaitCommand(0.25));

			// Move Forward to get over to the bin
			addSequential(new AutoMecanumTime(0, -0.43, 0, 0.51));
			addSequential(new WaitCommand(0.25));

			// Drop Can onto the bin
			addSequential(new AutoSetClamp(false));
			addSequential(new WaitCommand(0.2));
			// Lower arm to grab bin
			addSequential(new AutoLiftLimitDown());
			addSequential(new WaitCommand(0.25));
			// Drive forward to adjust the pick up position.
			addSequential(new AutoMecanumTime(0, -0.37, 0, 0.3));
			addSequential(new WaitCommand(0.25));
			// Grab bin (tm)
			addSequential(new AutoSetClamp(true));
			addSequential(new WaitCommand(0.5));

			// Lift arm back up to clear bump
			addSequential(new AutoLifterCommand(1, 1));
			addSequential(new WaitCommand(0.2));
			addSequential(new AutoLockCommand(true));
			addSequential(new WaitCommand(0.3));

			// Rotate 90deg counterclockwise
			addSequential(new AutoMecanumTime(0, 0, -0.42, 1.28));
			addSequential(new WaitCommand(0.25));
			// Drive into Autozone
			addSequential(new AutoMecanumTime(0, -0.6, 0, 2.5));

			addSequential(new WaitCommand(0.5));

			// Lower stuff(tm)
			addSequential(new AutoLiftLimitDown());
			addSequential(new WaitCommand(1));
			// Release stuff(tm)
			addSequential(new AutoSetClamp(false));
			// addSequential(new AutoMecanumTime(-0.2, 0, 0, 0.1));
			addSequential(new AutoLockCommand(false));
			break;
		case LEFT_CENTER_TOTE:

			break;
		case NO_OP:
			// Done!
			break;
		case RIGHT_BIN_ONLY:

			break;

		case RIGHT_BOTH:
			// BUMP
			addSequential(new AutoLockCommand(false));
			addSequential(new AutoSetClamp(true));
			addSequential(new WaitCommand(0.25));
			// Lift the bin up
			addSequential(new AutoLifterCommand(0.71, 1));
			addSequential(new WaitCommand(0.25));

			// Move Forward to get over to the bin
			addSequential(new AutoMecanumTime(0, -0.43, 0, 0.51));
			addSequential(new WaitCommand(0.25));

			// Drop Can onto the bin
			addSequential(new AutoSetClamp(false));
			addSequential(new WaitCommand(0.2));
			// Lower arm to grab bin
			addSequential(new AutoLiftLimitDown());
			addSequential(new WaitCommand(0.25));
			// Drive forward to adjust the pick up position.
			addSequential(new AutoMecanumTime(0, -0.37, 0, 0.3));
			addSequential(new WaitCommand(0.25));
			// Grab bin (tm)
			addSequential(new AutoSetClamp(true));
			addSequential(new WaitCommand(0.5));

			// Lift arm back up to clear bump
			addSequential(new AutoLifterCommand(1, 1));
			addSequential(new WaitCommand(0.2));
			addSequential(new AutoLockCommand(true));
			addSequential(new WaitCommand(0.3));

			// Rotate 90deg counterclockwise
			addSequential(new AutoMecanumTime(0, 0, -0.42, 1.28));
			addSequential(new WaitCommand(0.25));
			// Drive into Autozone
			addSequential(new AutoMecanumTime(0, -0.6, 0, 2.5));

			addSequential(new WaitCommand(0.5));

			// Lower stuff(tm)
			addSequential(new AutoLiftLimitDown());
			addSequential(new WaitCommand(1));
			// Release stuff(tm)
			addSequential(new AutoSetClamp(false));
			// addSequential(new AutoMecanumTime(-0.2, 0, 0, 0.1));
			addSequential(new AutoLockCommand(false));
			break;
		case RIGHT_CENTER_TOTE:

			break;
		case RIGHT_TRIFORCE:

			break;
		default:
			break;

		}

	}

	/**
	 * Clamps the arms while also lifting to slightly above single tote height.
	 */
	private void get() {
		addParallel(new AutoSetClamp(true));
		addSequential(new AutoLifterCommand(0.7, 1));
	}

	/**
	 * Releases the clamp while also lowering the arm into load position.
	 */
	private void release() {
		addParallel(new AutoSetClamp(false));
		// addSequential(new AutoLifterDown(0.3));
	}

	/**
	 * Strafes in the given direction. If Up equals true the robot will strafe
	 * upwards; otherwise, the robot will strafe downwards.
	 * 
	 * @param up
	 *            Directional control
	 */
	private void strafe(boolean up) {
		// Values should be aprox the same but inverted.
		double speed = (up) ? 0.4 : -0.4;
		addSequential(new AutoMecanumTime(speed, 0, 0, 0.7));
	}

	/**
	 * Move into from one tote position to another. Should be called after a
	 * strafe command.
	 * 
	 * @param dir
	 *            The direction to move to. True is forwards. False is
	 *            backwards.
	 */
	private void ToToTo(boolean dir) {
		double speed = (dir) ? 0.6 : -0.6;
		addSequential(new AutoMecanumTime(0, speed, 0, 1));
	}

	/**
	 * 
	 * @param clockwise
	 *            Whether to turn clockwise or counter-clockwise.
	 */
	private void turn(boolean clockwise) {

	}

	private void drive() {
		addSequential(new AutoMecanumTime(0, 0.6, 0, 0.7));
	}
}
