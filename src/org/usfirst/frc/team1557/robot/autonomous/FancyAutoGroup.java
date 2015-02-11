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

			break;
		case CENTER_LEFT_TOTE:

			break;
		case CENTER_RIGHT_TOTE:

			break;
		case LEFT_BIN_ONLY:

			break;
		case LEFT_BOTH:

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
	 * Grabs a tote, raises the arm, and strafes down all at once. After, it
	 * will drive to the next position
	 * 
	 * @param liftTime
	 *            Length of time the lift should run. This will depends on how
	 *            high you want to stack
	 * @param direction
	 *            The direction you want to move after the strafe
	 */
	private void grabStrafe(double liftTime, double direction) {
		addParallel(new ToggleClampCommand());
		addParallel(new AutoLifterCommand(0.5, liftTime));
		addSequential(new AutoMecanumTime(-0.4, 0, 0, 0.4));
		addSequential(new AutoMecanumTime(0, direction, 0, .7));
	}

	/**
	 * Strafes upwards.
	 */
	private void strafeUp() {
		addSequential(new AutoMecanumTime(0.4, 0, 0, 0.7));
	}

}
