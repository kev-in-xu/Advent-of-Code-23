package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day9Inputs.txt"); //RMBR UPDATE
		Scanner in = new Scanner(SourceFile);
		Monkey[] monkeys = new Monkey[8];
		long [][] monkeyItems = {
				{54, 53},
				{95, 88, 75, 81, 91, 67, 65, 84},
				{76, 81, 50, 93, 96, 81, 83},
				{83, 85, 85, 63},
				{85, 52, 64},
				{57},
				{60, 95, 76, 66, 91},
				{65, 84, 76, 72, 79, 65},
				};
		
		for (int i=0;i<8;i++) {
			monkeys[i] = new Monkey(i);
		}//initialize monkeys
		
		
		for (int i=0;i<8;i++) {
			for (long j : monkeyItems[i]) {
				monkeys[i].add(j);
			}
		}
		//give them initial items
		
		ArrayList<Long> thrown;
		ArrayList<Integer> targets;
		
		for (int i=0;i<10000;i++) {
			for(Monkey m : monkeys) {
				thrown = m.turn();
				targets = m.getTargets();
				for (int j=0;j<thrown.size();j++) {
					monkeys[targets.get(j)].add(thrown.get(j));
				}
			}
		}
		in.close();
		
		
		long max=0;
		long secondMax=0;
		for (Monkey m : monkeys) {
			System.out.println(m.getInspections());
			if (m.getInspections()>max) {
				secondMax = max;
				max = m.getInspections();
			} else {
				if (m.getInspections()>secondMax) {
					secondMax=m.getInspections();
				}
			}
		}
		
		System.out.println(max);
		System.out.println(secondMax);
		System.out.println(max*secondMax);
		
		
	}// main method end
}
/*
 * PLAN:
 * 
 * 1. MAKE MONKEY CLASS
 * - ARRAYLIST OF OBJECTS (STARTING)
 * - NUMBER OF INSPECTIONS
 * - THROW METHODS, RETURNING ARRAYLIST? (OBJECTS TARGET OBJECT TARGET?)
 * - HOW TO CUSTOMIZE UNIQUE OPERATIONS FOR EACH?
 * - - PROBABLY USE A SWITCH SYSTEM INSIDE MONKEY CLASS*
 * 
 */