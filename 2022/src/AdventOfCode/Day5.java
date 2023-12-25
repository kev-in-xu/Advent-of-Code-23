package AdventOfCode;

import java.util.Scanner;
import java.io.*;

public class Day5 {
	
	public static void main (String[] args) throws FileNotFoundException{
		
		String[] crates = new String[9];
		
		crates[0] = "JHGMZNTF";
		crates[1] = "VWJ";
		crates[2] = "GVLJBTH";
		crates[3] = "BPJNCDVL";
		crates[4] = "FWSMPRG";
		crates[5] = "GHCFBNVM";
		crates[6] = "DHGMR";
		crates[7] = "HNMVZD";
		crates[8] = "GNFH";
		
		
		File SourceFile = new File("Day5Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		int num,from,to;
		
		while (in.hasNextLine()) {
			
			//parse instructions
			String input = in.nextLine();
			
			num = Integer.parseInt(input.substring(5,input.indexOf('f')-1));
			input = input.substring(input.indexOf('f'));
			
			from = Integer.parseInt(input.substring(5,input.indexOf('t')-1)) - 1;
			input = input.substring(input.indexOf('t'));
			
			to = Integer.parseInt(input.substring(3)) - 1;
			
			System.out.println(num + ", " + from + ", " + to);
			
			
			//EXECUTE
			//delete from old
			//reverse and append onto new
			

			// crates[to] += reverse(crates[from].substring(crates[from].length()-num));
			// simulates picking up one at a time
			
			crates[to] += crates[from].substring(crates[from].length()-num);
			// simulates picking up blocks chunked at a time
			
			crates[from] = crates[from].substring(0,crates[from].length()-num);
			
			
		}
		
		for (String s : crates) {
			//System.out.println(s);
			System.out.print(s.substring(s.length()-1));
		}
		
		in.close();
	}
	
	
	public static String reverse(String input) {
		
		if (input.length() == 1) {
			return input;
		} else {
			return reverse(input.substring(1)) + input.substring(0,1);
		}
	}

}
