package Day14;

import java.io.*;
import java.util.*;

public class Day14 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day14Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		int[][] cave = new int[180][400]; // 0-199 y, 200-799 x
		
		
		// LEARN HOW TO PLACE ROCKS
		
		String input;
		Integer newX, newY;
		Integer oldX, oldY;
		int line = 0;
		int maxY = 0;
		
		while (in.hasNext()) {
			input = in.nextLine();
			
			System.out.println("line no: " + ++line);
			
			oldX = oldY = newX = newY = null;
			while (!input.isEmpty()) {
				
				if (input.charAt(0) == ' ') { // " -> " is in the string
					input = input.substring(4);
					continue;
				}
				
				oldX = newX; // reassigns old coords before starting new
				oldY = newY;
				
				
				newX = Integer.parseInt(input.substring(0,input.indexOf(',')))-300; // -400 to translate to array size
				try{
					newY = Integer.parseInt(input.substring(input.indexOf(',')+1,input.indexOf(' '))); // if in middle of line
				} catch (Exception e){
					newY = Integer.parseInt(input.substring(input.indexOf(',')+1)); // if end of line, then substring till end
				}
				
				System.out.println("newX: " + newX + " newY: " + newY + " || oldX: " + oldX + " oldY: " + oldY);
				
				
				if (oldX == null) { // when reading the first pair of coordinates
					oldX = newX;
					oldY = newY;
				}
				
				if (newY > maxY) {
					maxY = newY;
				}
				
				// now I have two pairs of coords
				
				
				if (oldX.equals(newX)) { // if traversing in y direction
					for (int i = Math.min(oldY, newY), max = Math.max(oldY,  newY); i<=max; i++) {
						cave[i][oldX] = 1;
					}
				} else { // if traversing in x direction
					for (int i = Math.min(oldX, newX), max = Math.max(oldX,  newX); i<=max; i++) {
						cave[oldY][i] = 1;
					}
				}
				
				
				try {
					input = input.substring(input.indexOf(' '));
				} catch (Exception e) {
					input = "";
					System.out.println();
				}
				
				
			}
		}
		
		//PART 2, MAKING A BOTTOM HORIZONTAL LINE
		for (int i=0; i<400; i++) {
			cave[maxY+2][i] = 1;
		}
		
		
		int moveX = 200;
		int moveY = 0;
		int sandCount = 0;
		
		
		// TRAVERSAL LOOP
		while (cave[0][200] == 0) {
			if (cave[moveY+1][moveX] == 0) {
				moveY++;
				continue;
			} else if (cave[moveY+1][moveX-1] == 0) {
				moveY++;
				moveX--;
				continue;
			} else if (cave[moveY+1][moveX+1] == 0) {
				moveY++;
				moveX++;
				continue;
			} else {
				cave[moveY][moveX] = 2;
				System.out.println("moveX: " + moveX + " || moveY: " + moveY);
				moveX = 200;
				moveY = 0;
				sandCount++;
			}
			
		}// end traversal while
		
		in.close();
		int temp = 0;
		int rockCount = 0;
		
		for (int i=0; i<180; i++) {
			for (int j=0; j<400; j++) {
				temp = cave[i][j];
				if (temp == 0) {
					System.out.print('.');
				} else if (temp == 1) {
					System.out.print('#');
					rockCount++;
				} else if (temp == 2) {
					System.out.print('o');
					//sandCount++;
				}
			}
			System.out.println();
		}

		System.out.println("rocks: " + rockCount);
		System.out.println("sand: " + sandCount);
	}//end main

}
