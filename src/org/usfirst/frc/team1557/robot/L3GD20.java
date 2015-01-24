package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.I2C;

public class L3GD20 implements RoboGyro {

	public enum Scale {
		scale250, scale500, scale2000;
	}
	
	private final int devAddress = 0b1101_011;
	private final int regWhoAmI = 0x0f;
	private final int regCtrl1 = 0x20;
	private final int regCtrl4 = 0x23;
	private final int regOutZL = 0x2c;
	private final int regOutZH = 0x2d;
	private I2C gyro;
	private Scale currScale = Scale.scale250;
	
	public L3GD20() {
		try {
			gyro = new I2C(I2C.Port.kOnboard, devAddress);
			
			if(gyro.addressOnly()) {
				throw new Exception("Could not connect to L3GD20 Gyroscope!");
			}
			
			byte[] buf = new byte[1];
			gyro.read(regWhoAmI, 1, buf);
			if(buf[0] != 0b1101_0100) {
				throw new Exception("Wrong device at L3GD20 Gyroscope address!");
			}
			
			gyro.write(regCtrl1, 0b0000_1111);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setScale(Scale scale) {
		switch(scale) {
		case scale250:
			gyro.write(regCtrl4, 0b0000_0000);
			break;
		case scale500:
			gyro.write(regCtrl4, 0b0001_0000);
			break;
		case scale2000:
			gyro.write(regCtrl4, 0b0010_0000);
			break;
		}
		currScale = scale;
	}
	
	public double readAngleZ() {
		int val;
		byte[] buf = new byte[1];
		
		gyro.read(regOutZL, 1, buf);
		val = buf[0];
		
		gyro.read(regOutZH, 1, buf);
		val |= ((int) buf[0]) << 8;
		
		double scaledVal = 0;
		switch(currScale) {
		case scale250:
			scaledVal = val / 250;
			break;
		case scale500:
			scaledVal = val / 500;
			break;
		case scale2000:
			scaledVal = val / 2000;
			break;
		}
		
		return scaledVal;
	}

	@Override
	public double getAngleZ() {
		// TODO Auto-generated method stub
		return 0;
	}
}
