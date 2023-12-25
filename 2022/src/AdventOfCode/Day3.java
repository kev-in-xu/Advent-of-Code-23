package AdventOfCode;

import java.io.*;
import java.util.Scanner;

public class Day3 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day3Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		int count = 0;
		//String input;
		String first, second, third, secondEdited, thirdEdited;
		
		
		/* PART 1
		
		while (in.hasNextLine()) {
			input = in.nextLine();
			first = input.substring(0,(input.length()/2));
			second = input.substring((input.length()/2));
			
			for (int i=0; i<input.length(); i++) {
			
				second = second.replace(String.valueOf(first.charAt(i)), "");
				
				if (second.length() != first.length()) {
					count += valueOf(first.charAt(i));
					System.out.println(first.charAt(i) + ", " + valueOf(first.charAt(i)));
					break;
				}
				//replace letter in second by letter in first
				//if lengths are different, find value of string and add it to count
			}
			
		}
		System.out.println(count);
		
		*/
		
		//PART 2
		
		while (in.hasNextLine()) {
			first = in.nextLine();
			second = in.nextLine();
			third = in.nextLine();
			
			for (int i=0; i<first.length(); i++) {
				secondEdited = second.replace(String.valueOf(first.charAt(i)), "");
				thirdEdited = third.replace(String.valueOf(first.charAt(i)), "");
				
				if (secondEdited.length() != second.length() && thirdEdited.length() != third.length()) {
					// if lengths are different from original
					
					count += valueOf(first.charAt(i));
					System.out.println(first.charAt(i) + ", " + valueOf(first.charAt(i)));
					break;
				}
				//replace letter in second by letter in first
				//if lengths are different, find value of string and add it to count
			}
			
		}
		System.out.println(count);
		
		
		/*
		plan: 
		split string in half -DONE
		
		check for duplicates
		find duplicate
		
		System.out.println(((int) 'Z')-96+58);
		
		match value (-97 if lowercase, -38 (-97+59)if uppercase)
		
		*/
		in.close();
	}
	
	public static int valueOf(char c) {
		int value = ((int) c) - 96;
		if (value<0) {
			value +=58;
		}
		return value;
		
		// a-z --> 1-26
		// A-Z --> 27-52
	}

}
