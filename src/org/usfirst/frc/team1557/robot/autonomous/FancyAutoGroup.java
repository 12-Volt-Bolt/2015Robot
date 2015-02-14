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
			addSequential(new AutoMecanumTime(0, 0.5, 0, 0.4));
			break;
		case BUMP_DRIVE_ONLY:
			addSequential(new AutoMecanumTime(0, 0.5, 0, 0.74));
			break;
		case CENTER_BIN_ONLY:

			break;
		case CENTER_BOTH:
			addSequential(new AutoLockCommand(false));
			getBin(0.25);
			shmancyWait(0.25);
			driveBinToTote();
			shmancyWait(0.25);
			releaseAndLower(0.2);
			shmancyWait(0.25);
			addSequential(new AutoSetClamp(true));
			shmancyWait(0.5);
			addSequential(new AutoLifterCommand(1, 1));
			shmancyWait(0.2);
			addSequential(new AutoLockCommand(true));
			shmancyWait(0.25);
			shmancyWait(0.25);
			addSequential(new AutoMecanumTime(-0.5, -0.5, 0, 0.5));
			addSequential(new AutoMecanumTime(-0.4, 0, 0, 2));
			shmancyWait(0.5);
			addSequential(new AutoLiftLimitDown());
			shmancyWait(0.5);
			addSequential(new AutoSetClamp(false));
			addSequential(new AutoLockCommand(false));
			break;
		case CENTER_LEFT_TOTE:
			addSequential(new AutoLockCommand(false));
			getBin(0.25);
			shmancyWait(0.25);
			strafeToBin(true);
			shmancyWait(0.25);
			ToToTo(true);
			shmancyWait(0.25);
			strafeToBin(false);
			shmancyWait(0.25);
			releaseAndLower(0.25);
			shmancyWait(0.25);
			getBin(0.25);
			shmancyWait(0.25);
			strafeToZone();
			shmancyWait(0.25);
			addSequential(new AutoSetClamp(false));
			break;
		case CENTER_RIGHT_TOTE:

			break;
		case LEFT_BIN_ONLY:

			break;
		case LEFT_BOTH:
			addSequential(new AutoLockCommand(false));
			getBin(0.25);
			shmancyWait(0.25);
			driveBinToTote();
			shmancyWait(0.25);
			releaseAndLower(0.2);
			shmancyWait(0.25);
			addSequential(new AutoSetClamp(true));
			shmancyWait(0.5);
			addSequential(new AutoLifterCommand(1, 1));
			shmancyWait(0.2);
			addSequential(new AutoLockCommand(true));
			shmancyWait(0.25);
			turn(false);
			shmancyWait(0.25);
			driveOverBump();
			shmancyWait(0.5);
			addSequential(new AutoLiftLimitDown());
			shmancyWait(0.5);
			addSequential(new AutoSetClamp(false));
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
			// DO NOT CHANGE
			addSequential(new AutoLockCommand(false));
			getBin(0.25);
			shmancyWait(0.25);
			driveBinToTote();
			shmancyWait(0.25);
			releaseAndLower(0.2);
			shmancyWait(0.25);
			addSequential(new AutoSetClamp(true));
			shmancyWait(0.5);
			addSequential(new AutoLifterCommand(1, 1));
			shmancyWait(0.2);
			addSequential(new AutoLockCommand(true));
			shmancyWait(0.25);
			turn(false);
			shmancyWait(0.25);
			driveOverBump();
			shmancyWait(0.5);
			addSequential(new AutoLiftLimitDown());
			shmancyWait(0.5);
			addSequential(new AutoSetClamp(false));
			addSequential(new AutoLockCommand(false));
			break;
		case RIGHT_CENTER_TOTE:

			break;
		case RIGHT_TRIFORCE:
			// RIP
			break;
		default:
			break;

		}

	}

	/**
	 * Clamps the arms while also lifting to slightly above single tote height.
	 */
	private void getBin(double time) {
		addSequential((new AutoSetClamp(true)));
		addSequential(new WaitCommand(time));
		addSequential(new AutoLifterCommand(0.7, 0.8));
	}

	/**
	 * Releases the clamp while also lowering the arm into load position.
	 */
	private void releaseAndLower(double time) {
		addSequential(new AutoSetClamp(false));
		addSequential(new WaitCommand(time));
		addSequential(new AutoLiftLimitDown());
	}

	/**
	 * Strafes in the given direction. If Up equals true the robot will strafe
	 * upwards; otherwise, the robot will strafe downwards.
	 * 
	 * @param up
	 *            Directional control
	 */
	private void strafeToBin(boolean up) {
		// Values should be aprox the same but inverted.
		double speed = (up) ? 0.4 : -0.4;
		addSequential(new AutoMecanumTime(speed, 0, 0, 0.5));
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
		double speed = (!dir) ? 0.6 : -0.6;
		addSequential(new AutoMecanumTime(0, speed, 0, 1));
	}

	/**
	 * 
	 * @param clockwise
	 *            Whether to turn clockwise or counter-clockwise.
	 */
	private void turn(boolean clockwise) {
		if (clockwise) {
			addSequential(new AutoMecanumTime(0, 0, 0.40, 1.28));
		} else {
			addSequential(new AutoMecanumTime(0, 0, -0.40, 1.28));
		}
	}

	private void driveBinToTote() {
		addSequential(new AutoMecanumTime(0, -0.45, 0, 0.5));
	}

	private void driveOverBump() {
		addSequential(new AutoMecanumTime(0, -0.41, 0, 2.6));
	}

	private void shmancyWait(double time) {
		addSequential(new WaitCommand(time));

	}

	private void strafeToZone() {
		addSequential(new AutoMecanumTime(-0.41, 0, 0, 2.5));
	}

}
