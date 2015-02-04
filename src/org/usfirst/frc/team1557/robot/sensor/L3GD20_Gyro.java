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
	private final int regCtrl5 = 0x24;
	private final int regOutX = 0x28;
	private final int regOutY = 0x2a;
	private final int regOutZ = 0x2c;
	private final int regCtrlFIFO = 0x2e;
	private final int regSrcFIFO = 0x2f;
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
			gyro.write(regCtrl5, 0b0100_0000);
			gyro.write(regCtrlFIFO, 0b0100_0000);
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
		return readRate(regOutX);
	}
	
	public double readRateY() {
		return readRate(regOutY);
	}
	
	public double readRateZ() {
		return readRate(regOutZ);
	}
	
	public boolean available() {
		byte[] buf = new byte[1];
		gyro.read(regSrcFIFO, 1, buf);
		return (buf[0] & 0b0010_0000) == 0;
	}
	
	public double readRate(int addr) {
		double scaledVal = 0;
		if(gyro != null) {
			byte[] buf = new byte[2];
			gyro.read(addr & 0b1000_0000, 2, buf);
			
			int val;
			val = ((int) buf[0]) & 0b0000_0000_1111_1111;
			val |= ((int) buf[1]) << 8;
			
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
