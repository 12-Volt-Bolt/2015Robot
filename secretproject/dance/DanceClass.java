package org.usfirst.frc.team1557.robot.dance;

import java.util.Random;

import org.usfirst.frc.team1557.robot.autonomous.AutoLifterCommand;
import org.usfirst.frc.team1557.robot.autonomous.AutoMecanumTime;
import org.usfirst.frc.team1557.robot.commands.ToggleClampCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Halp meh! Halp meh plz
 */

public class DanceClass extends CommandGroup {

	private long startTime;

	public DanceClass() {
		Random random = new Random();
		startTime = System.currentTimeMillis();
		// random.next
	}

	private long getElapsed() {
		return (System.currentTimeMillis() - startTime);
	}

	private void strafe(double x, double y, double time) {
		addSequential(new AutoMecanumTime(x, y, 0, time));
	}

	private void clap(int quantity) {
		addSequential(new ToggleClampCommand());
	}

	private void hop(int quantity) {
		addSequential(new AutoLifterCommand(1, 1));
	}

	private void slide(String direction, double duration) {
		switch (direction) {
		case "left":
			addSequential(new AutoMecanumTime(-0.25, 0, 0, duration));
			break;
		case "right":
			addSequential(new AutoMecanumTime(0.25, 0, 0, duration));
			break;
		case "forward":
			addSequential(new AutoMecanumTime(0, 0.25, 0, duration));
			break;
		case "backward":
			addSequential(new AutoMecanumTime(0, 0.25, 0, duration));
			break;
		default:
			break;
		}
	}

	private void turn(String clock, double duration) {
		switch (clock) {
		case "clockwise":
			addSequential(new AutoMecanumTime(0, 0, 0.25, duration));
			break;
		case "counterClockwise":
			addSequential(new AutoMecanumTime(0, 0, -0.25, duration));
			break;
		default:
			break;
		}
	}

	private void reverse(String clock) {
		double duration = 1;
		switch (clock) {
		case "clockwise":
			addSequential(new AutoMecanumTime(0, 0, 0.5, duration));
			break;
		case "counterClockwise":
			addSequential(new AutoMecanumTime(0, 0, -0.5, duration));
			break;
		default:
			break;
		}
	}

	private void stomp() {
		
	}
	
	private void chaCha() {
		
	}
	
	Thread halp = new Thread(new Runnable() {

		@Override
		public void run() {
			while (true) {
				switch (1) {

				case 1:
					// This time we're gonna get funky
					// Everybody clap your hands
					// Clap clap clap clap your hands
					// Clap clap clap clap your hands
					clap(1);
					break;

				case 1:
					//  Alright we gonna do the basic steps
					// slide to the left
					slide("left", 1);
					break;

				case 1:
					// slide to the right
					slide("right", 1);
					break;
				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// One hop this time
					hop(1);
					break;

				// Right foot lets stomp
				// Left foot lets stomp
				// Cha cha real smooth

				case 1:
					// Now turn it out
					turn("clockwise", 1);
					break;

				case 1:
					// To the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// One hop this time
					hop(1);
					break;

				// Right foot lets stomp
				// Left foot lets stomp
				// Cha cha now y'all

				case 1:
					// Now it's time to get funky
					// To the right
					slide("right", 1);
					break;

				case 1:
					// To the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// One hop this time
					hop(1);
					break;

				case 1:
					// One hop this time
					hop(1);
					break;

				// Right foot two stomps
				// Left foot two stomps

				case 1:
					// Slide to the left
					slide("left", 1);
					break;

				case 1:
					// Slide to the right
					slide("right", 1);
					break;

				// Criss cross, criss cross
				// Cha cha real smooth

				// Lets go to work

				case 1:
					// To the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// Two hops this time
					hop(2);
					break;

				case 1:
					// Two hops this time
					hop(2);
					break;

				// Right foot two stomps
				// Left foot two stomps
				// Hands on your knees, hands on your knees
				// How low can you go
				// All the way to the floor
				// Like you never never stoped
				// Can you bring it to the top
				// Like you never never stoped

				// Get funky with it
				// Ooooooooh yeah (come on)
				// Cha cha now y'all

				case 1:
					// Turn it out
					turn("clockwise", 1);
					break;

				case 1:
					// To the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// Five hops this time
					hop(5);
					break;

				// Right foot lets stomp
				// Left foot lets stomp
				// Right foot again
				// Left foot again
				// Right foot lets stomp
				// Left foot lets stomp
				// FREEEZE

				case 1:
					// Everybody clap your hands
					clap(1);
					break;

				// (Come on y'all) (Check it out y'all)
				// How low can you go?
				// Can you go down low?
				// All the way to the floor
				// How low can you go?
				// Can you bring it to the top?
				// Like you never never stop?

				case 1:
					// Can you bring it to the top, one hop
					hop(1);
					break;

				// Right foot now
				// Left foot now y'all
				// Cha cha real smooth

				case 1:
					// Turn it out
					turn("clockwise", 1);
					break;

				case 1:
					// To the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// One hop this time
					hop(1);
					break;

				case 1:
					// One hop this time
					hop(1);
					break;

				case 1:
					// Reverse
					reverse("clockwise");
					break;

				case 1:
					// Reverse
					reverse("counterClockwise");
					break;

				case 1:
					// Slide to the left
					slide("left", 1);
					break;

				case 1:
					// Slide to the right
					slide("right", 1);
					break;

				case 1:
					// Reverse
					reverse("clockwise");
					break;

				case 1:
					// Reverse
					reverse("counterClockwise");
					break;

				case 1:
					// Reverse
					reverse("clockwise");
					break;

				case 1:
					// Reverse
					reverse("counterClockwise");
					break;

				// Cha cha now y'all, cha cha again
				// Cha cha now y'all, cha cha again

				case 1:
					// Turn it out
					turn("clockwise", 1);
					break;

				case 1:
					// To the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				case 1:
					// Two hops
					hop(2);
					break;

				case 1:
					// Two hops
					hop(2);
					break;

				case 1:
					// Two hops
					hop(2);
					break;

				case 1:
					// Two hops
					hop(2);
					break;

				// Right foot lets stomp
				// left foot let stomp
				// Charlie brown

				case 1:
					// Turn it out now
					turn("clockwise", 1);
					break;

				case 1:
					// Slide to the right
					slide("right", 1);
					break;

				case 1:
					// Slide to the left
					slide("left", 1);
					break;

				case 1:
					// Take it back now y'all
					slide("backward", 1);
					break;

				// Cha cha now y'all

				default:
					break;
				}
			}
		}

	});
}
