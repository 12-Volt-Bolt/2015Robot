package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class TableReader {
	private final String WKEY = "W", 
			SKEY = "S", 
			AKEY = "A",
			DKEY = "D",
			MOUSEKEY = "MOUSE";
	NetworkTable table;
	boolean W = false, S = false, A = false, D = false;
	double MOUSE = 0;

	public void init(String key) {
		table = NetworkTable.getTable(key);
	}

	public void update() {
		W = table.getBoolean(WKEY);
		S = table.getBoolean(SKEY);
		A = table.getBoolean(AKEY);
		D = table.getBoolean(DKEY);
		MOUSE = table.getDouble(MOUSEKEY);
	}
}
