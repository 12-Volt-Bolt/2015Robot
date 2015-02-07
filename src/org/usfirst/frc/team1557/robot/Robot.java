package org.usfirst.frc.team1557.robot;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.autonomous.AutoBumpClimbDrive;
import org.usfirst.frc.team1557.robot.autonomous.AutoLifterCommand;
import org.usfirst.frc.team1557.robot.autonomous.AutoMecanumTime;
import org.usfirst.frc.team1557.robot.autonomous.AutoSetClamp;
import org.usfirst.frc.team1557.robot.autonomous.SensoredAutonomous;
import org.usfirst.frc.team1557.robot.autonomous.UnsensoredAutonomous;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team1557.robot.commands.ShiftSpeedCommand;
import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClampSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.LifterSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.LockSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.SensorSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/*
	 * Doesn't completely crash Y
	 * 
	 * Teleop Drive
	 * 
	 * Sensorless Autonomous
	 * 
	 * Sensored Autonomous
	 * 
	 * Gyro/Accel input test Y
	 * 
	 * Correct values
	 * 
	 * Accel Y should be ~= 0 N
	 * 
	 * Gyro should stay ~= 0 Y
	 * 
	 * Gyro Drift Amount N
	 * 
	 * 
	 * Mecanum drives correctly Y
	 * 
	 * Lifter lifts Y
	 * 
	 * Limit switch status
	 * 
	 * Lifter doesn't fall down? Y
	 * 
	 * See if we can change DriveSystems with SmrtDshbrd Y
	 * 
	 * Canceling gravity
	 * 
	 * 
	 * Tilt code / Testing
	 * 
	 * 
	 * Autonomous Lifter Amount
	 * 
	 * 
	 * Autonomous Drive speed amounts / Saturday
	 * 
	 * 0.5/1 Empty = 1.5 Units
	 */

	/**
	 * If true, subsystems should not access motors.
	 */
	public static boolean HEADLESS = false;

	public static OI oi;

	// Command lifterCommand;
	Command autonomousCommand;
	Command shiftCommand;
	public static DriveSubsystem driveSystem;
	public static LifterSubsystem lifterSystem;
	public static ClampSubsystem clampSystem;
	public static SensorSubsystem sensorSystem;
	public static LockSubsystem lockSystem;
	// Compressor compresser;

	// Select the mode of Driving used by DriveSubsystem
	SendableChooser driveChooser;
	public static SendableChooser positionChooser;
	SendableChooser autoAbelChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// compresser = new Compressor();
		// compresser.start();
		oi = new OI();

		if (!HEADLESS) {

			driveSystem = new DriveSubsystem();
			lifterSystem = new LifterSubsystem();
			sensorSystem = new SensorSubsystem();
			lockSystem = new LockSubsystem();
			clampSystem = new ClampSubsystem();
			shiftCommand = new ShiftSpeedCommand();
			// instantiate the command used for the autonomous period

			clampSystem = new ClampSubsystem();

			// lifterCommand = new LifterCommand();

			// Create choosers
			// TODO: fang options
			driveChooser = new SendableChooser();

			driveChooser.addDefault("Magical Mecanum",
					new MecanumDriveCommand());
			driveChooser.addObject("Tedious Tank", new TankDriveCommand());
			SmartDashboard.putData("Drive Chooser", driveChooser);
			positionChooser = new SendableChooser();
			positionChooser.addDefault("Right(Bump)", AutoPosition.RIGHT);
			positionChooser.addObject("Center(Bump)", AutoPosition.CENTER);
			positionChooser.addObject("Left(Bumpless)", AutoPosition.LEFT);
			SmartDashboard.putData("Starting Position", positionChooser);
			autoAbelChooser = new SendableChooser();
			autoAbelChooser.addDefault("Sensorless Autonomous",
					AutoChoice.SENSORLESS);
			autoAbelChooser.addObject("Sensored Autonomous",
					AutoChoice.SENSORABEL);
			SmartDashboard.putData("Sensor Choosing", autoAbelChooser);
			
			SmartDashboard.putNumber(RobotMap.lifterKey, 1);
			SmartDashboard.putData(Scheduler.getInstance());
			SmartDashboard.putData(driveSystem);

		}

		oi.initialize();
	}

	int count = 1;

	/**
	 * Adds the command to the SmrtDshbrd
	 * 
	 * @param command
	 */
	private void addSequential(Command command) {

		SmartDashboard.putData("Step " + count, command);
		count++;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	private enum AutoChoice {
		SENSORLESS, SENSORABEL

	}

	public void autonomousInit() {
		// schedule the autonomous command (example)

		if (!HEADLESS) {
			// ((Command) (autoChooser.getSelected())).start();

			if (autoAbelChooser.getSelected() == AutoChoice.SENSORABEL) {
				autonomousCommand = new SensoredAutonomous();

			} else {
				autonomousCommand = new UnsensoredAutonomous();
			}
			autonomousCommand.start();

			// TODO remove
			sensorSystem.init();
			sensorSystem.initDefaultCommand();
		}
	}

	// This function is called periodically during autonomous
	//
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}

		if (!HEADLESS) {
			((Command) driveChooser.getSelected()).start();
			shiftCommand.start();
			// TODO remove
			sensorSystem.init();
			sensorSystem.initDefaultCommand();
		}
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		sensorSystem.resetGyro();
		sensorSystem.resetAccel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// TODO remove
		// sensorSystem.updateSensor();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();

		if (!HEADLESS) {
			Command driveCommand = (Command) driveChooser.getSelected();
			driveCommand.start();
		}
	}
}
