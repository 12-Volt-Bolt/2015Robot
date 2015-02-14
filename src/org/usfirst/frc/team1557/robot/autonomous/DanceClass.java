package org.usfirst.frc.team1557.robot.autonomous;

import java.util.Random;

import org.usfirst.frc.team1557.robot.AutonomousPlans;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DanceClass extends CommandGroup {

	public DanceClass() {
		Random random = new Random();
		// random.next

	}

	private void strafe(double x, double y, double time) {
		addSequential(new AutoMecanumTime(x, y, 0, time));
		addSequential(new WaitCommand(0.1));
		addSequential(new AutoMecanumTime(-x, -y, 0, time));
	}

	private void turn() {

	}
}
