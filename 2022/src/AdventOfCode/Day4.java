package AdventOfCode;

import java.io.*;
import java.util.Scanner;

public class Day4 {
	public static void main (String[] args) throws FileNotFoundException {
		
		int count = 0;
		int elf1Min, elf1Max, elf2Min, elf2Max;
		
		
		File SourceFile = new File("Day4Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		
		while (in.hasNextLine()) {
			String entry = in.nextLine();
			elf1Min = Integer.parseInt(entry.substring(0,entry.indexOf('-')));
			entry = entry.substring(entry.indexOf('-')+1);
			
			elf1Max = Integer.parseInt(entry.substring(0,entry.indexOf(',')));
			entry = entry.substring(entry.indexOf(',')+1);
			
			elf2Min = Integer.parseInt(entry.substring(0,entry.indexOf('-')));
			entry = entry.substring(entry.indexOf('-')+1);
			
			elf2Max = Integer.parseInt(entry);
			
			
			/*
			 * FOR PART 1: complete overlap
			 * 
			//elf1 in elf2 range
			if (elf1Min >= elf2Min && elf1Max <= elf2Max || elf2Min >= elf1Min && elf2Max <= elf1Max) {
				count++;
				System.out.println(elf1Min + " " + elf1Max + " " + elf2Min + " " + elf2Max);
			}
			*/
			
			//PART 2: ANY OVERLAP
			
			if (!(elf1Max < elf2Min || elf2Max < elf1Min)) {
				count++;
				System.out.println(elf1Min + " " + elf1Max + " " + elf2Min + " " + elf2Max);
			}
			
		
		}
		//read file line by line
		
		//check condition
		
		
		
		//print count
		System.out.println("count = " + count);
		in.close();
	}
}
