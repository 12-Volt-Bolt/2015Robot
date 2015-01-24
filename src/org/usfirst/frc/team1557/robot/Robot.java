package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.autonomous.AutonomousGroup;
import org.usfirst.frc.team1557.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClampSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.LifterSubsystem;

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

	public static OI oi;
	Command tankDriveCommand;
	Command mecanumDriveCommand;
	Command mixDriveCommand;
	// Command lifterCommand;
	CommandGroup autonomousCommand;

	public static DriveSubsystem driveSystem;
	public static LifterSubsystem lifterSystem;
	public static ClampSubsystem clampSystem;

	// Compressor compresser;

	// Select the mode of Driving used by DriveSubsystem
	SendableChooser driveChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// compresser = new Compressor();
		// compresser.start();
		oi = new OI();

		driveSystem = new DriveSubsystem();
		lifterSystem = new LifterSubsystem();
		// clampSystem = new ClampSubsystem();

		// instantiate the command used for the autonomous period
		// // lifterCommand = new LifterCommand();
		autonomousCommand = new AutonomousGroup();

		driveChooser = new SendableChooser();
		driveChooser.addDefault("Mecanum Magic", new MecanumDriveCommand());
		driveChooser.addObject("Tedious Tank", new TankDriveCommand());
		SmartDashboard.putData(driveSystem);
		//SmartDashboard.putData);
		SmartDashboard.putNumber("lifterSpeed", 1);
		SmartDashboard.putData(Scheduler.getInstance());

		// new ImageInfo;

		// open the camera at the IP address assigned. This is the IP address
		// that the camera
		// can be accessed through the web interface.

		/*
		 * try { CaptureRunnable runnable = new CaptureRunnable("cam0"); Thread
		 * captureThread = new Thread(runnable); captureThread.start(); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */

	}

	/*
	 * private class CaptureRunnable implements Runnable { String name;
	 * 
	 * public CaptureRunnable(String name) { this.name = name; }
	 * 
	 * @Override public void run() { Image frame = NIVision.imaqCreateImage(
	 * NIVision.ImageType.IMAGE_RGB, 0);
	 * 
	 * 
	 * int id = NIVision.IMAQdxOpenCamera(name,
	 * NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	 * NIVision.IMAQdxConfigureGrab(id); NIVision.IMAQdxStartAcquisition(id);
	 * 
	 * while (true) { //NIVision.IMAQdxGrab(id, frame, 1);
	 * camera.getImage(frame);
	 * 
	 * //ImageInfo info = NIVision.imaqGetImageInfo(frame);
	 * //System.out.printf("I has an image: %s %s", info.xRes, info.yRes);
	 * 
	 * //Thread.sleep(100);
	 * 
	 * break; }
	 * 
	 * }
	 * 
	 * }
	 */

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand.start();
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

		//((Command) driveChooser.getSelected()).start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	// int debounce = 0;

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// if (SmartDashboard.getBoolean("drive", true)) {
		// if (!mecanumDriveCommand.isRunning()) {
		// tankDriveCommand.cancel();
		// mecanumDriveCommand.start();
		// }
		// } else {
		// if (!tankDriveCommand.isRunning()) {
		// mecanumDriveCommand.cancel();
		// tankDriveCommand.start();
		// }
		// }

		/*
		 * debounce++;
		 * 
		 * if (debounce > 3) { debounce = 0; NIVision.Rect rect = new
		 * NIVision.Rect(10, 10, 100, 100);
		 * 
		 * camera.getImage(frame); NIVision.imaqDrawShapeOnImage(frame, frame,
		 * rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
		 * 
		 * CameraServer.getInstance().setImage(frame); }
		 */
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		((Command) driveChooser.getSelected()).start();

	}
}
