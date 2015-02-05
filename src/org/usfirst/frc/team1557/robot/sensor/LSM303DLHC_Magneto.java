//package org.usfirst.frc.team1557.robot.sensor;
//
//import edu.wpi.first.wpilibj.I2C;
//
//public class LSM303DLHC_Magneto {
//
//	public enum Speed {
//		HZ075, HZ150, HZ300, HZ750, HZ1500, HZ3000, HZ7500, HZ22000;
//	}
//	
//	
//	private final int devAddress = 0b0011_110;
//	private final int regCtrl1 = 0x20;
//	private final int regCtrl4 = 0x23;
//	private final int regOutXL = 0x04;
//	private final int regOutXH = 0x03;
//	private final int regOutYL = 0x08;
//	private final int regOutYH = 0x07;
//	private final int regOutZL = 0x06;
//	private final int regOutZH = 0x05;
//	private Speed currRate = Speed.HZ075;
//	private I2C magneto;
//
//	// TODO allow setting of data rate
//	public LSM303DLHC_Magneto() {
//		try {
//			magneto = new I2C(I2C.Port.kOnboard, devAddress);
//
//			byte[] buf = new byte[1];
//			magneto.read(regCtrl1, 1, buf);
//			if ((buf[0] != 0b0000_0111) && (buf[0] != 0b0101_0111)) {
//				throw new Exception(
//						"Wrong device at LSM303DLHC Magnetometer address!");
//			}
//
//			magneto.write(regCtrl1, 0b0101_0111);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			if (magneto != null) {
//				magneto.free();
//				magneto = null;
//			}
//		}
//	}
//
//	public void setScale(Speed rate) {
//		if (magneto != null) {
//			switch (rate) {
//			case HZ075:
//				magneto.write(regCtrl4, 0b0000_0000);
//				break;
//			case HZ150:
//				magneto.write(regCtrl4, 0b0000_0100);
//				break;
//			case HZ300:
//				magneto.write(regCtrl4, 0b0000_1000);
//				break;
//			case HZ750:
//				magneto.write(regCtrl4, 0b0000_1100);
//				break;
//			case HZ1500:
//				magneto.write(regCtrl4, 0b0001_0000);
//				break;
//			case HZ3000:
//				magneto.write(regCtrl4, 0b0001_0100);
//				break;
//			case HZ7500:
//				magneto.write(regCtrl4, 0b0001_1000);
//				break;
//			case HZ22000:
//				magneto.write(regCtrl4, 0b0001_1100);
//				break;
//			}
//			currRate = rate;
//		}
//	}
//
//	public double readRateX() {
//		return readRate(regOutXL, regOutXH);
//	}
//
//	public double readRateY() {
//		return readRate(regOutYL, regOutYH);
//	}
//
//	public double readRateZ() {
//		return readRate(regOutZL, regOutZH);
//	}
//
//	public double readRate(int lowAddr, int highAddr) {
//		double scaledVal = 0;
//		if (magneto != null) {
//			int val;
//			byte[] buf = new byte[1];
//
//			magneto.read(lowAddr, 1, buf);
//			val = ((int) buf[0]) & 0b0000_0000_1111_1111;
//
//			magneto.read(highAddr, 1, buf);
//			val |= ((int) buf[0]) << 8;
//
//			switch (currRate) {
//			case HZ075:
//				scaledVal = ((double) val) * 2 / 0b0111_1111_1111_1111;
//				break;
//			case HZ150:
//				scaledVal = ((double) val) * 4 / 0b0111_1111_1111_1111;
//				break;
//			case HZ300:
//				scaledVal = ((double) val) * 8 / 0b0111_1111_1111_1111;
//				break;
//			case HZ750:
//				scaledVal = ((double) val) * 16 / 0b0111_1111_1111_1111;
//				break;
//			case HZ1500:
//				scaledVal = ((double) val) * 2 / 0b0111_1111_1111_1111;
//				break;
//			case HZ3000:
//				scaledVal = ((double) val) * 4 / 0b0111_1111_1111_1111;
//				break;
//			case HZ7500:
//				scaledVal = ((double) val) * 8 / 0b0111_1111_1111_1111;
//				break;
//			case HZ22000:
//				scaledVal = ((double) val) * 16 / 0b0111_1111_1111_1111;
//				break;
//			}
//			
//		}
//
//		return scaledVal;
//	}
//
//	// TODO may return incorrect values
//	public boolean isConnected() {
//		return magneto != null;
//
//	}
//}
