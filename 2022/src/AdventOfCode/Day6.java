package AdventOfCode;

import java.io.*;
import java.util.Scanner;

public class Day6 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day6Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		String input = in.nextLine();
		String sub;
		System.out.println(input.length());
		
		//break into substrings (for loop)
		
		for (int i=0; i<input.length()-14; i++) {
			sub = input.substring(i,i+14);
			//System.out.println(sub + ", " + i);
			if (checkValid(sub)) {
				System.out.println(sub + ", " + (i+14));
			}
		}
		
		
		
		
		in.close();
	}
	
	public static boolean checkValid(String input) {
		
		String a = "";
		
		for (int i=0; i<13; i++) {
			a = input.replace(input.substring(0,1),"");
			if (a.length()>0) {
				input = a;
			}
		}
		
		if (a.length()>0) {
			return true;
		}
		
		//replace first letter?
		//replace second letter?
		//replace third letter?
		
		
		//remove first 3 characters, if remaining string is length 1, then valid**
		
		
		return false;
	}

}
