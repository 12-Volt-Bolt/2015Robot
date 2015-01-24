package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.I2C;

public class L3GD20 {

	public enum Scale {
		scale250, scale500, scale2000;
	}
	
	private final int devAddress = 0b1101_011;
	private final int regWhoAmI = 0x0f;
	private final int regCtrl1 = 0x20;
	private final int regCtrl4 = 0x23;
	private final int regOutZL = 0x2c;
	private final int regOutZH = 0x2d;
	private I2C i2c;
	private Scale currScale = Scale.scale250;
	
	public L3GD20() {
		try {
			i2c = new I2C(I2C.Port.kOnboard, devAddress);
			
			if(i2c.addressOnly()) {
				throw new Exception("Could not connect to L3GD20!");
			}
			
			byte[] buf = new byte[1];
			i2c.read(regWhoAmI, 1, buf);
			if(buf[0] != 0b1101_0100) {
				throw new Exception("Wrong device at L3GD20 address!");
			}
			
			i2c.write(regCtrl1, 0b0000_1111);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setScale(Scale scale) {
		switch(scale) {
		case scale250:
			i2c.write(regCtrl4, 0b0000_0000);
			break;
		case scale500:
			i2c.write(regCtrl4, 0b0001_0000);
			break;
		case scale2000:
			i2c.write(regCtrl4, 0b0010_0000);
			break;
		}
		currScale = scale;
	}
	
	public double readAngleZ() {
		int val;
		byte[] buf = new byte[1];
		
		i2c.read(regOutZL, 1, buf);
		val = buf[0];
		
		i2c.read(regOutZH, 1, buf);
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
}
