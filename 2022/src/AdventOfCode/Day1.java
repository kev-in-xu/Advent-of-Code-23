package AdventOfCode;

import java.io.*;
import java.util.Scanner;

public class Day1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day1Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		int calories = 0;
		int maxCal = 0;
		int secondCal = 0;
		int thirdCal = 0;

		String input;
		
		while (in.hasNext()) {
			input = in.nextLine();
			if (input.equals("")) {
				System.out.println("new line");
				calories = 0;
			} else if (in.hasNextInt()) {
				calories += Integer.parseInt(input);
				System.out.println(calories);
				if (calories > maxCal) {
					thirdCal = secondCal;
					secondCal = maxCal;
					maxCal = calories;
				} else if (calories > secondCal) {
					thirdCal = secondCal;
					secondCal = calories;
				} else if (calories > thirdCal) {
					thirdCal = calories;
				}
			}
		}
		
		System.out.println("maxCal = " + maxCal);
		System.out.println("secondCal = " + secondCal);
		System.out.println("thirdCal = " + thirdCal);
		System.out.println(maxCal + secondCal + thirdCal);
		
		
		in.close();
	}

}
