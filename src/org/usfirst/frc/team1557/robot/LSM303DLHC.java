//package org.usfirst.frc.team1557.robot;
//
//import edu.wpi.first.wpilibj.I2C;
//
//public class LSM303DLHC {
//
//	public enum Scale {
//		scale250, scale500, scale2000;
//	}
//	
//	private final int accel_devAddress = 0b0011_001;
//	private final int accel_regWhoAmI = 0x0f;
//	private final int accel_regCtrl1 = 0x20;
//	private final int accel_regCtrl4 = 0x23;
//	private final int accel_regOutZL = 0x2c;
//	private final int accel_regOutZH = 0x2d;
//	private I2C accel;
//	private Scale currScale = Scale.scale250;
//
//	
//	public LSM303DLHC() {
//		try {
//			accel = new I2C(I2C.Port.kOnboard, accel_devAddress);
//			
//			if(accel.addressOnly()) {
//				throw new Exception("Could not connect to LSM303DLHC Accelerometer!");
//			}
//			
//			byte[] buf = new byte[1];
//			accel.read(accel_regWhoAmI, 1, buf);
//			if(buf[0] != 0b1101_0100) {
//				throw new Exception("Wrong device at LSM303DLHC Accelerometer address!");
//			}
//			
//			accel.write(accel_regCtrl1, 0b0000_1111);
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	public void setScale(Scale scale) {
//		switch(scale) {
//		case scale250:
//			accel.write(accel_regCtrl4, 0b0000_0000);
//			break;
//		case scale500:
//			accel.write(accel_regCtrl4, 0b0001_0000);
//			break;
//		case scale2000:
//			accel.write(accel_regCtrl4, 0b0010_0000);
//			break;
//		}
//		currScale = scale;
//	}
//	
//	public double readAccelZ() {
//		int val;
//		byte[] buf = new byte[1];
//		
//		accel.read(accel_regOutZL, 1, buf);
//		val = buf[0];
//		
//		accel.read(accel_regOutZH, 1, buf);
//		val |= ((int) buf[0]) << 8;
//		
//		double scaledVal = 0;
//		switch(currScale) {
//		case scale250:
//			scaledVal = val / 250;
//			break;
//		case scale500:
//			scaledVal = val / 500;
//			break;
//		case scale2000:
//			scaledVal = val / 2000;
//			break;
//		}
//		
//		return scaledVal;
//	}
//}
