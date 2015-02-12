package org.usfirst.frc.team1557.robot.sensor;

import edu.wpi.first.wpilibj.I2C;

public class LSM303DLHC_Magneto {

	public enum Speed {
		HZ075, HZ150, HZ300, HZ750, HZ1500, HZ3000, HZ7500, HZ22000;
	}
	
	public enum Scale {
		G130, G190, G250, G400, G470, G560, G810;
	}
	
	private final int devAddress = 0b0011_110;
	private final int regIRA_REG_M = 0x0A; // These are Something ReadOnly
	private final int regIRB_REG_M = 0x0B; // & Important but will be
	private final int regIRC_REG_M = 0x0C; // used for Identification
	
	private final int regCRA_REG_M = 0x00; //Temperature Sensor Activation & Data Output Rate Register
	private final int regCRB_REG_M = 0x20; //Magnetometer Gain Register
	private final int regOutXL = 0x04;
	private final int regOutXH = 0x03;
	private final int regOutYL = 0x08;
	private final int regOutYH = 0x07;
	private final int regOutZL = 0x06;
	private final int regOutZH = 0x05;
	private Speed currSpeed = Speed.HZ1500;
	private Scale currScale = Scale.G130;
	private I2C magneto;

	public LSM303DLHC_Magneto() {
		try {
			magneto = new I2C(I2C.Port.kOnboard, devAddress);

			byte[] buf = new byte[1];
			byte[] key = new byte[3];
			magneto.read(regIRA_REG_M, 1, buf);
			System.arraycopy(buf, 0, key, 0, buf.length);
			magneto.read(regIRB_REG_M, 1, buf);
			System.arraycopy(buf, 0, key, 1, buf.length);
			magneto.read(regIRC_REG_M, 1, buf);
			System.arraycopy(buf, 0, key, 2, buf.length);
			if ((key[0] != 0b0100_1000) && (key[1] != 0b0101_0111) && (key[2] != 0b0011_0011)) {
				throw new Exception(
						"Wrong device at LSM303DLHC Magnetometer address!");
			}

//			magneto.write(regCtrl1, 0b0101_0111);
		} catch (Exception ex) {
			ex.printStackTrace();
			if (magneto != null) {
				magneto.free();
				magneto = null;
			}
		}
	}

	public void setScale(Scale scale) {
		if (magneto != null) {
			switch (scale) {
			case G130:
				magneto.write(regCRB_REG_M, 0b0010_0000);
				break;
			case G190:
				magneto.write(regCRB_REG_M, 0b0100_0000);
				break;
			case G250:
				magneto.write(regCRB_REG_M, 0b0110_0000);
				break;
			case G400:
				magneto.write(regCRB_REG_M, 0b1000_0000);
				break;
			case G470:
				magneto.write(regCRB_REG_M, 0b1010_0000);
				break;
			case G560:
				magneto.write(regCRB_REG_M, 0b1100_0000);
				break;
			case G810:
				magneto.write(regCRB_REG_M, 0b1110_0000);
				break;
			default:
				
				break;
			}
				
			currScale = scale;
		}
	}
	
	public void setSpeed(Speed rate) {
		if (magneto != null) {
			switch (rate) {
			case HZ075:
				magneto.write(regCRA_REG_M, 0b0000_0000);
				break;
			case HZ150:
				magneto.write(regCRA_REG_M, 0b0000_0100);
				break;
			case HZ300:
				magneto.write(regCRA_REG_M, 0b0000_1000);
				break;
			case HZ750:
				magneto.write(regCRA_REG_M, 0b0000_1100);
				break;
			case HZ1500:
				magneto.write(regCRA_REG_M, 0b0001_0000);
				break;
			case HZ3000:
				magneto.write(regCRA_REG_M, 0b0001_0100);
				break;
			case HZ7500:
				magneto.write(regCRA_REG_M, 0b0001_1000);
				break;
			case HZ22000:
				magneto.write(regCRA_REG_M, 0b0001_1100);
				break;
			}
			currSpeed = rate;
		}
	}
	
	public double readRate(int lowAddr, int highAddr) {
		double scaledVal = 0;
		if (magneto != null) {
			int val;
			byte[] buf = new byte[1];

			magneto.read(lowAddr, 1, buf);
			val = ((int) buf[0]) & 0b0000_0000_1111_1111;

			magneto.read(highAddr, 1, buf);
			val |= ((int) buf[0]) << 8;

			switch (currScale) {
			case G130:
				scaledVal = val * 1.3 / 0b0111_1111_1111_1111;
				break;
			case G190:
				scaledVal = val * 1.9 / 0b0111_1111_1111_1111;
				break;
			case G250:
				scaledVal = val * 2.5 / 0b0111_1111_1111_1111;
				break;
			case G400:
				scaledVal = val * 4.0 / 0b0111_1111_1111_1111;
				break;
			case G470:
				scaledVal = val * 4.7 / 0b0111_1111_1111_1111;
				break;
			case G560:
				scaledVal = val * 5.6 / 0b0111_1111_1111_1111;
				break;
			case G810:
				scaledVal = val * 8.1 / 0b0111_1111_1111_1111;
				break;
			}
		}

		return scaledVal;
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

	// TODO may return incorrect values
	public boolean isConnected() {
		return magneto != null;
	}

}
