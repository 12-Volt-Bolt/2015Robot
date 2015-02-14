package org.usfirst.frc.team1557.robot.sensor;

public class KalmanFilter {

	// TODO THESE ARE NOT THE DROIDS YOU ARE LOOKING FOR!!!
	float gyroAdc = 0;
	float gyroZero = 0;
	float gyroSensitivity = 0;

	float accelAdc = 0;
	float accelZero = 0;
	float accelSensitivity = 0;

	float gyroRate = (gyroAdc - gyroZero) / gyroSensitivity;

}
