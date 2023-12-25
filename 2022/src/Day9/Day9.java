package Day9;

import java.io.*;
import java.util.*;

public class Day9 {
	
	static int maxX, minX, maxY, minY; // for mapping
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day9Inputs.txt"); //RMBR UPDATE
		Scanner in = new Scanner(SourceFile);
		String input;
		char dir;
		int dist;
		int[][] finalMap;
		
		Map model = new Map(191, 262, 1); // based on size calculated after parsing instructions
		model.startAt(47, 65); // shifted starting point so all coordinate were positive
		
		while (in.hasNext()) {
			input = in.nextLine();
			dir = input.charAt(0);
			dist = Integer.parseInt(input.substring(2));
			
			switch (dir) {
				case 'R':
					model.moveRight(dist);
					break;
				case 'L':
					model.moveLeft(dist);
					break;
				case 'D':
					model.moveDown(dist);
					break;
				case 'U':
					model.moveUp(dist);
					break;
			}
			
			/*
			System.out.println(model.gethX() + ", " 
					+ model.gethY() + ", " 
					+ model.gettX() + ", "
					+ model.gettY() + ", line =" + line);
			*/
				
		} // end parse while
		in.close();
		
		
		finalMap = model.getTailMap();
		
		int count = 0;
		for (int y=0;y<262;y++) {
			for (int x=0;x<191;x++) {
				System.out.print(finalMap[x][y]);
				if (finalMap[x][y] == 1) {
					count++;
				}
			}
			System.out.println();
		}
		
		
		System.out.println(count);
	} // end main method
	
	
	
	public static void updateMax(int x, int y) {
		if (x>maxX) {
			maxX=x;
		}
		if (x<minX) {
			minX=x;
		}
		if (y>maxY) {
			maxY=y;
		}
		if (y<minY) {
			minY=y;
		}
	}
	
}
/*
 * Tasks:
 * 1. figure out how big the size of the map needs to be
 * 1a. parse all instructions
 * 1b. keep track of x and y coordinate
 * 1c. keep track of maxX and maxY
 * DONE
 * 
 * 2. make map, shift starting point so coordinates begin at 0,0
 * DONE
 * 
 * 3. figure out tail moving rules
 * DONE
 * 
 * 4. set up map to keep track of tail locations
 * DONE
 * 
 * 5. count map markers
 * DONE
 * 
 */
