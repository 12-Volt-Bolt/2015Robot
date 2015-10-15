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
			addSequential(new AutoMecanumTime(-0.5, -0.5, 0, 0.5));
			shmancyWait(0.25);
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
			strafeAway(true);
			shmancyWait(0.25);
			ToToTo(true);
			shmancyWait(0.25);
			strafeAway(false);
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
			strafeToZone();
			shmancyWait(0.5);
			addSequential(new AutoLiftLimitDown());
			shmancyWait(0.5);
			addSequential(new AutoSetClamp(false));
			addSequential(new AutoLockCommand(false));
			break;

		case LEFT_BOTH_NO_DROP:
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
			strafeToZone();
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
			addSequential(new AutoLifterCommand(0.5, 0.42));
			shmancyWait(0.1);
			getBin(0.25);
			shmancyWait(0.25);
			driveBinToTote();
			shmancyWait(0.25);
			releaseAndLower(0.2);
			shmancyWait(0.25);
			addSequential(new AutoSetClamp(true));
			shmancyWait(0.5);
			addSequential(new AutoLifterCommand(1, 1.15));
			shmancyWait(0.2);
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

		case RIGHT_BOTH_NO_DROP:
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
			break;
		case RIGHT_CENTER_TOTE:

			break;
		case RIGHT_TRIFORCE:
			// RIP
			//
			addSequential(new AutoLockCommand(false));
			getToteStrafe();
			// First Tote Acquired
			ToToTo(true);
			strafeAway(false);
			addSequential(new AutoSetClamp(false));
			addSequential(new AutoLiftLimitDown());
			getToteStrafe();

			// Second Tote Acquired

			ToToTo(true);
			strafeAway(false);
			addSequential(new AutoSetClamp(false));
			addSequential(new AutoLiftLimitDown());
			getToteStrafe();
			addSequential(new AutoLockCommand(true));
			// Third Tote Acquired
			addSequential(new AutoMecanumTime(1, 0, 0, 0.5));
			addSequential(new AutoSetClamp(false));

			break;
		case RIGHT_BOTH_UNSTACK:
			addSequential(new AutoLockCommand(false));
			addSequential(new AutoLifterCommand(0.5, 0.42));
			shmancyWait(0.1);
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
			/**
			 * Stacks and doesn't move
			 */
		case STACK_NO_MOVE:
			addSequential(new AutoLockCommand(false));
			addSequential(new AutoLifterCommand(0.5, 0.42));
			shmancyWait(0.1);
			getBin(0.25);
			shmancyWait(0.25);
			driveBinToTote();
			shmancyWait(0.25);
			releaseAndLower(0.2);			
			addSequential(new AutoSetClamp(false));
			addSequential(new AutoLockCommand(false));
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
	//BEN WAS HERE
	private void getToteStrafe() {
		addSequential(new AutoSetClamp(true));
		addSequential(new WaitCommand(0.1));
		addParallel(new AutoLifterCommand(0.7, 0.8));
		strafeAway(true);

	}
	/**
	 *  Moves up then 
	 * @param time
	 */
	private void reverseGetBin(double time) {
	}

	/**
	 * } Releases the clamp while also lowering the arm into load position.
	 */
	private void releaseAndLower(double time) {
		addSequential(new AutoSetClamp(false));
		addSequential(new WaitCommand(time + 0.3));
		addSequential(new AutoMecanumTime(0, 0.45, 0, 0.125));
		addSequential(new AutoLiftLimitDown());
		addSequential(new AutoMecanumTime(0, -0.45, 0, 0.125));
	}

	/**
	 * Strafes in the given direction. If Up equals true the robot will strafe
	 * upwards; otherwise, the robot will strafe downwards.
	 * 
	 * @param up
	 *            Directional control
	 */
	private void strafeAway(boolean up) {
		// Values should be aprox the same but inverted.
		double speed = (up) ? 0.6 : -0.6;
		addSequential(new AutoMecanumTime(speed, 0, 0, 0.6));
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
			addSequential(new AutoMecanumTime(0, 0, 0.40, 1.15));
		} else {
			addSequential(new AutoMecanumTime(0, 0, -0.40, 1.15));
		}
	}

	private void driveBinToTote() {
		addSequential(new AutoMecanumTime(0, -0.45, 0, 0.5));
	}
	//Changed Time 2.35 -> 2.75
	private void driveOverBump() {
		addSequential(new AutoMecanumTime(0, -0.41, 0, 2.75));
	}

	private void shmancyWait(double time) {
		addSequential(new WaitCommand(time));

	}

	private void strafeToZone() {
		addSequential(new AutoMecanumTime(-0.6, 0, -0.02, 2.5));
	}

}
