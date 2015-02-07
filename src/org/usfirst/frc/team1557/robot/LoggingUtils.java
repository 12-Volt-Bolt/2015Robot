package org.usfirst.frc.team1557.robot;

public class LoggingUtils {
	
	public static void logStack() {
		try {
			throw new Exception();
		} catch(Exception e) {
			System.out.println("Current stack:");
			StackTraceElement[] els = e.getStackTrace();
			for(int i = 0; i < els.length; i++) {
				System.out.println(els[i].getFileName() + "; " + els[i].getMethodName() + ":" + els[i].getLineNumber());
			}
		}
	}
}
