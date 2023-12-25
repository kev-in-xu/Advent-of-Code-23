package AoC23;

import java.io.*;
import java.util.*;

public class day05 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("day05.txt");
		Scanner in = new Scanner(SourceFile);
		
		String input;
		
		ArrayList<String> inputs = new ArrayList<>();
		
		while (in.hasNext()) {
			input = in.nextLine();
			inputs.add(input);
			System.out.println(input);
		}
		in.close();
		
		String[] seeds = inputs.get(0).substring(7).split(" ");
		
		int[][] seedSoil = new int[31-3][3];
		
		for (int i=3; i<31; i++) {
			String[] temp = inputs.get(i).split(" ");
			for (String s : temp) {
				
			}
		}
		
		seedSoil = []
				for i in range(3,31):
				    seedSoil.append(lines[i].split())
		
		for (String seed : seeds) {
			System.out.println(seed);
		}
		
	}
}