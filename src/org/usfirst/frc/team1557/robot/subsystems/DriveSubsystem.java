package org.usfirst.frc.team1557.robot.subsystems;
import org.usfirst.frc.team1557.robot.RobotMap;
import static org.usfirst.frc.team1557.robot.RobotMap.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	public static CANTalon frontLeft;
	public static CANTalon rearLeft;
	public static CANTalon frontRight;
	public static CANTalon rearRight;
	RobotDrive drive;
	public static double motorScale = 0.35;
	public DriveSubsystem() {
		frontLeft = new CANTalon(frontLeftTalonID);
		rearLeft = new CANTalon(rearLeftTalonID);
		frontRight = new CANTalon(frontRightTalonID);
		rearRight = new CANTalon(rearRightTalonID);
		//frontLeft.reverseOutput(true);
		//rearLeft.reverseOutput(true);
		/*
		 * frontLeft = new CANTalon(1); backLeft = new CANTalon(2); frontRight =
		 * new CANTalon(3); backRight = new CANTalon(4);
		 */
		drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}

	public void initDefaultCommand() {

		// setDefaultCommand(new TankDriveCommand());
			
	}
	//TankDrive
	public void tankDrive(double x, double y) {
		drive.tankDrive(x, y);
	}

	// IT DON'T WORK 
	public void mecanumDrive(double x, double y, double rotation) {

		drive.mecanumDrive_Polar(x / 10, y, rotation / 10);

	}
	public void mixDrive(double leftX, double leftY, double rightX, double rightY){
		double averageX = (leftX + rightX) / 2;
		frontLeft.set((-leftY - averageX) * motorScale); 
		rearLeft.set((-leftY + averageX) * motorScale); 
		frontRight.set((rightY - averageX) * motorScale);
		rearRight.set((rightY + averageX) * motorScale);
		
		
	}
	// hi levoo
		// Mikel Pls
		// Taylo halp
		// Kevin Pl0x
}
