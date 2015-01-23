package org.usfirst.frc.team1557.robot;
/*
 * Mecanum Drive Library
 * Designed 2014
 * Version: 2.0.1
 * For FRC or related use only
 *
 * Created by AI Robotics #4529
 * www.ai-robotics.com.au
 * info@ai-robotics.com
 *
 * Changelog:
 * Fixed Turn Function - S
 */

import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class Mecanum 
{
    final float pi = (float) java.lang.Math.PI;

    SpeedController frontLeftWheel;
    SpeedController frontRightWheel;
    SpeedController backLeftWheel;
    SpeedController backRightWheel;
    
    //Gyro gyro;
    
    Joystick joystick;
    
    public float max(float a, float b) {
        return (a > b) ? a : b;
    }
    
    public void init(SpeedController frontLeft, SpeedController frontRight, SpeedController backLeft, SpeedController backRight, int joystickNumber, int gyroPin) {   
        
    	frontLeftWheel = frontLeft;
    	frontRightWheel = frontRight;
    	backLeftWheel = backLeft;
    	backRightWheel = backRight;
    	
    	
        joystick = new Joystick(joystickNumber);
        
        //gyro = new Gyro(gyroPin);
        //gyro.reset();
    }
    

	public void init(DriveSubsystem driveSystem, Joystick mainJoy) {
		frontLeftWheel = driveSystem.frontLeft;
    	frontRightWheel = driveSystem.frontRight;
    	backLeftWheel = driveSystem.rearLeft;
    	backRightWheel = driveSystem.rearRight;
    	
		this.joystick = mainJoy;
	}
    
    public int normAngle(int angle){
        angle = angle % 360;
        
       if (angle<0) {
            angle += 360;
        }
        
        return angle;
    }
    
     public float getJoystickMagnitude() {
        float jX = (float) joystick.getX();
        float jY = (float) -joystick.getY();
        return (float) Math.abs(Math.sqrt(Math.pow(jX, 2) + Math.pow(jY, 2)));   
    }
    
    public float getJoystickDirection() {
        float jX = (float) joystick.getX();
        float jY = (float) -joystick.getY();
        
        float mainStickDirection = (float) (Math.toDegrees(Math.atan(Math.abs(jY/jX))));
         
        if(jX<0) {
            if(jY<0) {
                mainStickDirection = 270 - mainStickDirection;
            }
            else if(jY>0) {
                mainStickDirection = 270 + mainStickDirection;
            }
            else {
                mainStickDirection = 270;
            }
        }
        else {
            if(jY<0) {
                mainStickDirection = 90 + mainStickDirection;
            }
            else if(jY==0) {
                mainStickDirection = 90;
            }
            else {
                mainStickDirection = 90 - mainStickDirection;
            }
        }

        //This sets dead zones near 4 major axis, + half's sensitivity in general
        if(mainStickDirection > 350 | mainStickDirection < 10) {
            mainStickDirection = 0;
        } else if(mainStickDirection > 170 && mainStickDirection < 190) {
            mainStickDirection = 180;
        } else if(mainStickDirection > 80 && mainStickDirection < 100) {
            mainStickDirection = 90;
        } else if(mainStickDirection > 260 && mainStickDirection < 280) {
            mainStickDirection = 270;
        }
        
        if(mainStickDirection % 2 == 1) {
            mainStickDirection -= 1;
        }
        return (int) mainStickDirection;
    }
    
    /*public float fod(float mainStickDirection)
    {
        float desiredAngle;
        float gyroAngle = normAngle((int) gyro.getAngle());
        float errorAngle = 10;
        
        if(mainStickDirection - gyroAngle < errorAngle && mainStickDirection - gyroAngle > -errorAngle) {
            desiredAngle = gyroAngle;
        } else {
            desiredAngle = mainStickDirection - gyroAngle;
        }
        
        if(desiredAngle  < 0) {
            desiredAngle +=360;
        }
        
        desiredAngle = (float) Math.toRadians(desiredAngle);
        
        return desiredAngle;
    }*/
    
    public void drive(float desiredSpeed, float desiredAngle, float turnSpeed, boolean fod) //0-1,0-2pi (radians), 0-1
    {   
            if(fod == true) {
            	throw new NoSuchMethodError("check em");
            	
            	//desiredAngle = fod(desiredAngle);
            } else {
                desiredAngle = (float) Math.toRadians(desiredAngle);
            }

            float frontLeft;
            float frontRight;
            float backLeft;
            float backRight;

            frontLeft = ((float) Math.sin(desiredAngle + pi/4)+ turnSpeed);
            frontRight = (float) ((float) Math.cos(desiredAngle + pi/4)- turnSpeed);
            backLeft = (float) ((float) Math.cos(desiredAngle + pi/4)+ turnSpeed);
            backRight = (float) ((float) Math.sin(desiredAngle + pi/4)- turnSpeed);

            float scaleFactor = max(max(max(Math.abs(frontLeft),Math.abs(frontRight)),Math.abs(backLeft)),Math.abs(backRight));

            frontLeftWheel.set(-desiredSpeed*frontLeft/scaleFactor);
            frontRightWheel.set(desiredSpeed*frontRight/scaleFactor);
            backLeftWheel.set(-desiredSpeed*backLeft/scaleFactor);
            backRightWheel.set(desiredSpeed*backRight/scaleFactor);
    }
    
    public void turn(float speed){
        frontLeftWheel.set(speed);
        frontRightWheel.set(speed);
        backLeftWheel.set(speed);
        backRightWheel.set(speed);
    }

}
