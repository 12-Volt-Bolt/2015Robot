package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.autonomous.AutonomousGroup;
import org.usfirst.frc.team1557.robot.autonomous.Vision;
import org.usfirst.frc.team1557.robot.commands.LifterCommand;
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
	Command lifterCommand;
	CommandGroup autonomousCommand;

	public static DriveSubsystem driveSystem;
	public static LifterSubsystem lifterSystem;
	public static ClampSubsystem armSystem;

	// Compressor compresser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	public void robotInit() {
		// compresser.start();
		oi = new OI();
		driveSystem = new DriveSubsystem();
		lifterSystem = new LifterSubsystem();
		armSystem = new ClampSubsystem();

		// instantiate the command used for the autonomous period
		tankDriveCommand = new TankDriveCommand();
		mecanumDriveCommand = new MecanumDriveCommand();
		lifterCommand = new LifterCommand();
		autonomousCommand = new AutonomousGroup();

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
		// if (autonomousCommand != null) {
		//
		// autonomousCommand.start();
		// }

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

		// lifterCommand.start();
		tankDriveCommand.start();
		// mecanumDriveCommand.start();

	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	int debounce = 0;

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

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
		mecanumDriveCommand.start();

		// LiveWindow.addActuator("DriveSubSystem", "frontRight",
		// DriveSubsystem.frontRight);
	}
}
