package AdventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 {
	
	static int score = 0;
	static String message = "";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day10Inputs.txt"); //RMBR UPDATE
		Scanner in = new Scanner(SourceFile);
		String input;
		
		int cycles = 0;
		int value = 1;
		int tempValue = 0;
		boolean processing = false;
		
		/*
		 * turn 1: read addx
		 * turn 2: nothing
		 * turn 3: added! + new read
		 */
		
		while(in.hasNext()) {
			
			cycles++; // start of a new cycle
			checkCycle(cycles, value); // time to check score!
			printScreen(cycles, value);
			
			if (processing) { // does not take input, finishes adding
				processing = false;
				value += tempValue;
				continue;
			}
			
			input = in.nextLine();  // takes input
			
			if (input.charAt(0) == 'n') { // noop = wait
				continue;
			}
			
			if (input.charAt(0) == 'a') { // addx = add in 2 turns
				processing = true;
				tempValue = Integer.parseInt(input.substring(5));
			}
			
		}// end while loop
		in.close();
		
		System.out.println("Score= " + score);
		System.out.println(message);
		
	} // end main method

	public static void checkCycle(int cycles, int value) {
		if ((cycles % 40) == 20) {
			System.out.println("Cycle: " + cycles);
			System.out.println("value: " + value);
			
			
			score += cycles*value;
			System.out.println("Score= " + score);
			System.out.println();
		}
	}
	
	public static void printScreen(int cycles, int value) {
		int position = cycles % 40 -1;
		
		if (Math.abs(position-value) <=1) {
			message += "#";
		} else {
			message += ".";
		}
		
		
		if (position+1 == 0) {
			message += "\n";
		}
	}
}
