package AdventOfCode;

import java.io.*;


public class Day4Inputs {
	
	public static void main (String[] args) throws FileNotFoundException {
		
		try {
			/*
			int size = Integer.parseInt(args[0]);
			int min = Integer.parseInt(args[1]);
			int max = Integer.parseInt(args[2]);
			
			*/
			int size = 120;
			int min = 1;
			int max = 99;
			
			int elf1Min, elf1Max, elf2Min, elf2Max;
			
			
			PrintWriter out = new PrintWriter("Day4Inputs.txt");
			
			for (int i=0; i<size; i++) {
				//generates list
				
				int elf1rand1, elf1rand2, elf2rand1, elf2rand2;
						
				elf1rand1 = numGen(max,min);
				elf1rand2 = numGen(max,min);
				elf2rand1 = numGen(max,min);
				elf2rand2 = numGen(max,min);
			
				elf1Min = Math.min(elf1rand1, elf1rand2);
				elf1Max = Math.max(elf1rand1, elf1rand2);
				
				elf2Min = Math.min(elf2rand1, elf2rand2);
				elf2Max = Math.max(elf2rand1, elf2rand2);
				//generates range for elf1 and elf2
				
				
				String entry = elf1Min + "-" + elf1Max + "," + elf2Min + "-" + elf2Max + "\n";
				System.out.println(entry);
				out.print(entry);
				//format and prints entry
				
			}
	
			out.close();
		} catch (Exception e) {
			System.out.println("The file you entered does not exist");
            System.out.println(e);
		}
	}
	
	public static int numGen(int max, int min) {
		return (int) (Math.random()*(max-min))+min;
	}
}
