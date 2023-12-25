package AdventOfCode;

import java.io.*;

public class Day5Inputs {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String[] crates = new String[9];
		
		crates[0] = "RPCDBG";
		crates[1] = "HVG";
		crates[2] = "NSQDJPM";
		crates[3] = "PSLGDCNM";
		crates[4] = "JBNCPFLS";
		crates[5] = "QBDZVGTS";
		crates[6] = "BZMHFTQ";
		crates[7] = "CMDBF";
		crates[8] = "FCQG";
		
		PrintWriter out = new PrintWriter("Day5Inputs.txt");
		
		/*
		
		for (int i = 0; i<9; i++) {
			out.println(i+1 + ": " + crates[i]);
		}
		
		out.println();
		*/
		
		out.println(instruct(1,7,4));
		out.println(instruct(3,4,7));
		out.println(instruct(4,3,4));
		out.println(instruct(5,6,9));
		out.println(instruct(1,8,1));
		out.println(instruct(2,3,2));
		out.println(instruct(3,4,6));
		out.println(instruct(1,3,6));

		//generate instructions
		out.close();
				
	}
	
	public static String instruct(int num, int from, int to) {
		return "move " + num + " from " + from + " to " + to;
	}
}
