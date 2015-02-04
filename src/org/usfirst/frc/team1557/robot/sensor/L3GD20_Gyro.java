package org.usfirst.frc.team1557.robot.sensor;

import edu.wpi.first.wpilibj.I2C;

public class L3GD20_Gyro {
	
	public enum Scale {
		scale250, scale500, scale2000;
	}
	
	private final int devAddress = 0b1101_011;
	private final int regWhoAmI = 0x0f;
	private final int regCtrl1 = 0x20;
	private final int regCtrl4 = 0x23;
	private final int regOutXL = 0x28;
	private final int regOutXH = 0x29;
	private final int regOutYL = 0x2a;
	private final int regOutYH = 0x2b;
	private final int regOutZL = 0x2c;
	private final int regOutZH = 0x2d;
	private Scale currScale = Scale.scale250;
	private I2C gyro;
	
	public L3GD20_Gyro() {
		try {
			gyro = new I2C(I2C.Port.kOnboard, devAddress);
			
			byte[] buf = new byte[1];
			gyro.read(regWhoAmI, 1, buf);
			if((buf[0] & 0b1111_1111) != 0b1101_0100) {
				throw new Exception("Wrong device at L3GD20 Gyroscope address!");
			}
			
			gyro.write(regCtrl1, 0b1111_1111);
		} catch(Exception ex) {
			ex.printStackTrace();
			if(gyro != null) {
				gyro.free();
				gyro = null;
			}
		}
	}
	
	public void setScale(Scale scale) {
		if(gyro != null) {
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
	}
	
	public double readRateX() {
		return readRate(regOutXL, regOutXH);
	}
	
	public double readRateY() {
		return readRate(regOutYL, regOutYH);
	}
	
	public double readRateZ() {
		return readRate(regOutZL, regOutZH);
	}
	
	public double readRate(int lowAddr, int highAddr) {
		double scaledVal = 0;
		if(gyro != null) {
			int val;
			byte[] buf = new byte[1];
			
			gyro.read(lowAddr, 1, buf);
			val = ((int) buf[0]) & 0b0000_0000_1111_1111;
			
			gyro.read(highAddr, 1, buf);
			val |= ((int) buf[0]) << 8;
			
			switch(currScale) {
			case scale250:
				scaledVal = val * 250 / 0b0111_1111_1111_1111;
				break;
			case scale500:
				scaledVal = val * 500 / 0b0111_1111_1111_1111;
				break;
			case scale2000:
				scaledVal = val * 2000 / 0b0111_1111_1111_1111;
				break;
			}
		}
		
		return scaledVal;
	}
	
	//TODO may return incorrect values
	public boolean isConnected() {
		return gyro != null;
	}
}
