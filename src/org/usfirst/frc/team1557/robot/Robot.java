package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.autonomous.AutonomousGroup;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClampSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.SensorSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.LifterSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	 * Doesn't completely crash
	 *  Y
	 * 
	 * Gyro/Accel input test
	 *  N
	 * 
	 * Correct values
	 * 
	 * Accel Y should be ~= 0
	 * 
	 * Gyro should stay ~= 0
	 * 
	 * Gyro Drift Amount
	 * 
	 * Mecanum drives correctly
	 *  Y
	 * 
	 * Lifter lifts 
	 * 	Y
	 * 
	 * Limit switch status
	 * 
	 * Lifter doesn't fall down?
	 *  Y
	 * 
	 * See if we can change DriveSystems with SmrtDshbrd
	 */
	
	/**
	 * If true, subsystems should not access motors.
	 */
	public static boolean HEADLESS = false;

	public static OI oi;

	// Command lifterCommand;

	public static DriveSubsystem driveSystem;
	public static LifterSubsystem lifterSystem;
	public static ClampSubsystem clampSystem;
	public static SensorSubsystem sensorSystem;

	//Compressor compresser;

	// Select the mode of Driving used by DriveSubsystem
	SendableChooser driveChooser;
	SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		//compresser = new Compressor();
		//compresser.start();
		oi = new OI();

		if (!HEADLESS) {
			driveSystem = new DriveSubsystem();
			lifterSystem = new LifterSubsystem();
			sensorSystem = new SensorSubsystem();
			
			//clampSystem = new ClampSubsystem();

			// instantiate the command used for the autonomous period
			// lifterCommand = new LifterCommand();

			autoChooser = new SendableChooser();

			driveChooser = new SendableChooser();

			driveChooser.addDefault("Magical Mecanum",
					new MecanumDriveCommand());
			driveChooser.addObject("Tedious Tank", new TankDriveCommand());

			SmartDashboard.putData("Drive Chooser", driveChooser);

			autoChooser.addDefault("Atrocious Autonomous",
					new AutonomousGroup());
			SmartDashboard.putData("Autonmous Chooser", autoChooser);
			// SmartDashboard.putData);

			SmartDashboard.putNumber(RobotMap.lifterKey, 1);
			SmartDashboard.putData(Scheduler.getInstance());
			SmartDashboard.putData(driveSystem);
			
		}
		
		oi.initialize();

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	boolean sensor = false;
	public void autonomousInit() {
		// schedule the autonomous command (example)
		
		if (!HEADLESS) {
			// ((Command) (autoChooser.getSelected())).start();
			sensorSystem.init();
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
		if (((Command) (autoChooser.getSelected())) != null) {
			((Command) (autoChooser.getSelected())).cancel();
		}
		if (!HEADLESS) {
			((Command) driveChooser.getSelected()).start();
			sensorSystem.init();
		}
		
		
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();

		if (!HEADLESS) {
			((Command) driveChooser.getSelected()).start();
		}
	}
}
