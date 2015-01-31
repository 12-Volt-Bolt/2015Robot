package org.usfirst.frc.team1557.robot.sensor;

import edu.wpi.first.wpilibj.I2C;

public class LSM303DLHC_Accel {
	
	public enum Scale {
		g2, g4, g8, g16;
	}
	//							   0b1101_011
	private final int devAddress = 0b0011_001;
	private final int regCtrl1 = 0x20;
	private final int regCtrl4 = 0x23;
	private final int regOutXL = 0x28;
	private final int regOutXH = 0x29;
	private final int regOutYL = 0x2a;
	private final int regOutYH = 0x2b;
	private final int regOutZL = 0x2c;
	private final int regOutZH = 0x2d;
	private Scale currScale = Scale.g2;
	private I2C accel;
	
	public void testAllOfType(I2C.Port type) {
		System.out.println("Starting test of type " + type.toString());
		for(int i = 0; i < 512; i++) {
			try {
				accel = new I2C(type, i);
			
			System.out.println("Tested " + i + " or " + Integer.toBinaryString(i));
			
			//if(!accel.read(regCtrl1, 1, new byte[1])) {
			if(!accel.transaction(new byte[0], (byte) 0, new byte[0], (byte) 0) || !accel.addressOnly()) {
				System.out.println("Found I2C device at address " + i + " or " + Integer.toBinaryString(i));
			}
			} catch (Exception e) {
				//e.printStackTrace();
				//break;
			} finally {
				accel.free();
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//TODO allow setting of data rate
	public LSM303DLHC_Accel() {
		
		testAllOfType(I2C.Port.kMXP);
		testAllOfType(I2C.Port.kOnboard);
		
		
		try {
			accel = new I2C(I2C.Port.kOnboard, devAddress);
			
			if(accel.transaction(new byte[0], (byte) 0, new byte[0], (byte) 0)) {
				throw new Exception("Could not connect to LSM303DLHC Accelerometer!");
			}
			
			byte[] buf = new byte[1];
			accel.read(regCtrl1, 1, buf);
			if(buf[0] != 0b0000_0111) {
				throw new Exception("Wrong device at LSM303DLHC Accelerometer address!");
			}
			
			accel.write(regCtrl1, 0b0101_0111);
		} catch(Exception ex) {
			ex.printStackTrace();
			if(accel != null) {
				accel.free();
				accel = null;
			}
		}
	}
	
	public void setScale(Scale scale) {
		if(accel != null) {
			switch(scale) {
			case g2:
				accel.write(regCtrl4, 0b0000_0000);
				break;
			case g4:
				accel.write(regCtrl4, 0b0001_0000);
				break;
			case g8:
				accel.write(regCtrl4, 0b0010_0000);
				break;
			case g16:
				accel.write(regCtrl4, 0b0011_0000);
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
		if(accel != null) {
			int val;
			byte[] buf = new byte[1];
			
			accel.read(lowAddr, 1, buf);
			val = ((int) buf[0]) & 0b0000_0000_1111_1111;
			
			accel.read(highAddr, 1, buf);
			val |= ((int) buf[0]) << 8;
			
			switch(currScale) {
			case g2:
				scaledVal = val * 2 / 0b0111_1111_1111_1111;
				break;
			case g4:
				scaledVal = val * 4 / 0b0111_1111_1111_1111;
				break;
			case g8:
				scaledVal = val * 8 / 0b0111_1111_1111_1111;
				break;
			case g16:
				scaledVal = val * 16 / 0b0111_1111_1111_1111;
				break;
			}
		}
		
		return scaledVal;
	}
	
	//TODO may return incorrect values
	public boolean isConnected() {
		return accel != null;
		
	}
}
