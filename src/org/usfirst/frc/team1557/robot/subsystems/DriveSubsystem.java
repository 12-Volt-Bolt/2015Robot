package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.OI;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	public static CANTalon frontLeft;
	public static CANTalon backLeft;
	public static CANTalon frontRight;
	public static CANTalon backRight;
	RobotDrive drive;
	public DriveSubsystem(){
		frontLeft = new CANTalon(1);
		backLeft = new CANTalon(2);
		frontRight = new CANTalon(3);
		backRight = new CANTalon(4);
		
		drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	}
	public void initDefaultCommand() {

		//setDefaultCommand(new TankDriveCommand());

	}
	public void TankDrive(double x, double y){
		drive.drive(OI.mainJoy.getRawAxis(1), OI.mainJoy.getRawAxis(3));
	}

	//Theoretical Mecanum Drive code?
	public void MecanumDrive(double x, double y){
		double rotation;
		
		//Bumpers for turning rotation. May be incorrect ports.
		if(OI.mainJoy.getRawButton(6)){
			rotation = 1;
		}else if(OI.mainJoy.getRawButton(7)){
			rotation = -1;
		}else{
			rotation = 0;
		}
		
		drive.mecanumDrive_Polar(x, y, rotation);
	}	//hi levoo
		//Mikel Pls
		//Taylo halp
		//
}

