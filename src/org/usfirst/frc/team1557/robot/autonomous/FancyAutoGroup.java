package org.usfirst.frc.team1557.robot.autonomous;

import org.usfirst.frc.team1557.robot.AutonomousPlans;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team1557.robot.commands.ToggleClampCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
			// Start of tests
			get();
			break;
		case CENTER_LEFT_TOTE:
			release();
			break;
		case CENTER_RIGHT_TOTE:
			strafe(true);
			break;
		case LEFT_BIN_ONLY:
			ToToTo(true);
			break;
		case LEFT_BOTH:
			drive();
			break;
		case LEFT_CENTER_TOTE:

			break;
		case NO_OP:
			// Done!
			break;
		case RIGHT_BIN_ONLY:

			break;
		case RIGHT_BOTH:

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
		//addSequential(new AutoLifterDown(0.3));
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
