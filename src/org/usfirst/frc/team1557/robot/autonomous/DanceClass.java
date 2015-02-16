//package org.usfirst.frc.team1557.robot.autonomous;
//
//import java.util.Random;
//
//import org.usfirst.frc.team1557.robot.AutonomousPlans;
//import org.usfirst.frc.team1557.robot.commands.ToggleClampCommand;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//import edu.wpi.first.wpilibj.command.WaitCommand;
//
///**
// *
// */
//public class DanceClass extends CommandGroup {
//
//	public DanceClass() {
//		Random random = new Random();
//		// random.next
//
//	}
//
//	private void strafe(double x, double y, double time) {
//		addSequential(new AutoMecanumTime(x, y, 0, time));
//		addSequential(new WaitCommand(0.1));
//		addSequential(new AutoMecanumTime(-x, -y, 0, time));
//	}
//
//
//	
//	private void clap(int quantity) {
//		addSequential(new ToggleClampCommand());
//	}
//	
//	private void hop(int quantity) {
//		addSequential(new AutoLifterCommand(m_startTime, m_startTime));
//	}
//	
//	private void slide(String direction, double duration, int iteration) {
//		switch(direction) {
//		case "left":
//			addSequential(new AutoMecanumTime(-0.25, 0, 0, duration));
//			break;
//		case "right":
//			addSequential(new AutoMecanumTime(0.25, 0, 0, duration));
//			break;
//		case "forward":
//			addSequential(new AutoMecanumTime(0, 0.25, 0, duration));
//			break;
//		case "backward":
//			addSequential(new AutoMecanumTime(0, 0.25, 0, duration));
//			break;
//		default:
//			break;
//		}
//	}
//	
//	private void turn(String clock, double duration, int iteration) {
//		switch(clock) {
//		case "clockwise":
//			addSequential(new AutoMecanumTime(0, 0, 0.25, duration));
//			break;
//		case "counterClockwise":
//			addSequential(new AutoMecanumTime(0, 0, -0.25, duration));
//			break;
//		default:
//			break;
//		}
//	}
//	
//	private void reverse(String clock, double duration, int iteration) {
//		switch(clock) {
//		case "clockwise":
//			addSequential(new AutoMecanumTime(0, 0, 0.5, duration));
//			break;
//		case "counterClockwise":
//			addSequential(new AutoMecanumTime(0, 0, -0.5, duration));
//			break;
//		default:
//			break;
//		}
//	}
//	
//	public void chaCha() {
//
//		//This time we're gonna get funky
//		//Everybody clap your hands
//		//Clap clap clap clap your hands
//		//Clap clap clap clap your hands
//		clap(1);
//		
//		//Alright we gonna do the basic steps
//		//slide to the left
//		slide("left", 1, 1);
//		//slide to the right
//		slide("right", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//One hop this time
//		hop(1);
//		//Right foot lets stomp
//		//Left foot lets stomp
//		//Cha cha real smooth
//
//		//Now turn it out
//		turn("clockwise", 1, 1);
//		//To the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//One hop this time
//		hop(1);
//		//Right foot lets stomp
//		//Left foot lets stomp
//		//Cha cha now y'all
//
//		//Now it's time to get funky
//		//To the right
//		slide("right", 1, 1);
//		//To the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//One hop this time, one hop this time
//		hop(1);
//		hop(1);
//		//Right foot two stomps
//		//Left foot two stomps
//		//Slide to the left
//		slide("left", 1, 1);
//		//Slide to the right
//		slide("right", 1, 1);
//		//Criss cross, criss cross
//		//Cha cha real smooth
//
//		//Lets go to work
//		//To the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//Two hops this time, two hops this time
//		hop(2);
//		hop(2);
//		//Right foot two stomps
//		//Left foot two stomps
//		//Hands on your knees, hands on your knees
//		//How low can you go
//		//All the way to the floor
//		//Like you never never stoped
//		//Can you bring it to the top
//		//Like you never never stoped
//
//		//Get funky with it
//		//Ooooooooh yeah (come on)
//		//Cha cha now y'all
//
//		//Turn it out
//		turn("clockwise", 1, 1);
//		//To the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//Five hops this time
//		hop(5);
//		//Right foot lets stomp
//		//Left foot lets stomp
//		//Right foot again
//		//Left foot again
//		//Right foot lets stomp
//		//Left foot lets stomp
//		//FREEEZE
//		
//		//Everybody clap your hands
//		clap(1);
//		//(Come on y'all) (Check it out y'all)
//		//How low can you go?
//		//Can you go down low?
//		//All the way to the floor
//		//How low can you go?
//		//Can you bring it to the top?
//		//Like you never never stop?
//		//Can you bring it to the top, one hop
//		
//		hop(1);
//		//Right foot now
//		//Left foot now y'all
//		//Cha cha real smooth
//		
//		//Turn it out
//		turn("clockwise", 1, 1);
//		//To the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//One hop this time
//		hop(1);
//		//One hop this time
//		hop(1);
//		//Reverse (reverse)
//		//Slide to the left
//		slide("left", 1, 1);
//		//Slide to the right
//		slide("right", 1, 1);
//		//Reverse, reverse
//		//Reverse, reverse
//		//
//		//Cha cha now y'all, cha cha again
//		//Cha cha now y'all, cha cha again
//		//
//		//Turn it out
//		turn("clockwise", 1, 1)
//		//To the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//Two hops two hops
//		hop(2);
//		hop(2);
//		//Two hops two hops
//		hop(2);
//		hop(2);
//		//Right foot lets stomp
//		//left foot let stomp
//		//Charlie brown
//		//Turn it out now
//		turn("clockwise", 1, 1);
//
//		//Slide to the right
//		slide("right", 1, 1);
//		//Slide to the left
//		slide("left", 1, 1);
//		//Take it back now y'all
//		slide("backward", 1, 1);
//		//Cha cha now y'all
//	}
//}
