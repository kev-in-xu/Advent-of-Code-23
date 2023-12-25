package AdventOfCode;

import java.io.*;
import java.util.*;

public class Day8 {
	
	static int sightMax, right, left, up, down;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day8Inputs.txt");
		
		// SCANS FILE TO SET UP ARRAY
		Scanner survey = new Scanner(SourceFile);
		int rows = 0;
		int columns = 0;
		String scan;
		
		while (survey.hasNext()) {
			scan = survey.nextLine();
			columns = scan.length();
			rows++;
		}
		survey.close();
		
		System.out.println("There are " + rows + " rows.");
		System.out.println("There are " + columns + " columns.");
		
		
		
		int[][] grid = new int[rows][columns];
		int[][] visible = new int[rows][columns];
		
		Scanner in = new Scanner(SourceFile);
		String input;
		
		//SET UP GRID
		for (int i=0;i<rows;i++) {
			input = in.nextLine();
			for (int j=0;j<columns;j++) {
				grid[i][j] = Integer.parseInt(input.substring(j,j+1));
			}
		}
		in.close();
		
		
		//SET UP CHECK FUNCTIONS @ PART 1
		int max;
		
		//check from left to right
		
		for (int i=0;i<rows;i++) {
			max = -1;
			for (int j=0;j<columns;j++) {
				if (grid[i][j] > max) {
					max = grid[i][j];
					visible[i][j] = 1;
				}
			}
		}
		
		//check from right to left
		
		for (int i=0;i<rows;i++) {
			max = -1;
			for (int j=columns-1;j>=0;j--) {
				if (grid[i][j] > max) {
					max = grid[i][j];
					visible[i][j] = 1;
				}
			}
		}
		
		//check from top to bottom*
		
		for (int i=0;i<columns;i++) {
			max = -1;
			for (int j=0;j<rows;j++) {
				if (grid[j][i] > max) {
					max = grid[j][i];
					visible[j][i] = 1;
				}
			}
		}
		
		//check from bottom to top*
		
		for (int i=0;i<columns;i++) {
			max = -1;
			for (int j=rows-1;j>=0;j--) {
				if (grid[j][i] > max) {
					max = grid[j][i];
					visible[j][i] = 1;
				}
			}
		}
		
		
		
		//OUTPUT MODEL*
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
		
		int count = 0;
		
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				System.out.print(visible[i][j]);
				if (visible[i][j] == 1) {
					count++;
				}
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("count=" + count);
		
		
		
		// PART 2 SCENIC SCORES
		
		/* Notes:
		 * don't need to check boundaries, because their score will be 0
		 */
		
		int[][] scores = new int[rows][columns];
		
		int height;
		
		// static int sightMax, right, left, up, down;
		// found above
		
		
		for (int i=1;i<rows-1;i++) {
			for (int j=1;j<columns-1;j++) {
							
				height = grid[i][j];
				System.out.print(height);
				reset();
				
				//check right
				
				int r=j+1;
				sightMax = -1;
				while (r<columns && sightMax < height) {
					right++;
					sightMax = grid[i][r];
					System.out.print(sightMax);
					r++;
				}
				
				
				//check left
				int l=j-1;
				sightMax = -1;
				while (l>=0 && sightMax < height) {
					left++;
					sightMax = grid[i][l];
					System.out.print(sightMax);
					l--;
					
				}

				
				//check down
				int d=i+1;
				sightMax = -1;
				while (d<rows && sightMax < height) {
					down++;
					sightMax = grid[d][j];
					System.out.print(sightMax);
					d++;
				}
				
				
				//check up
				int u=i-1;
				sightMax = -1;
				while (u>=0 && sightMax < height) {
					up++;
					sightMax = grid[u][j];
					System.out.print(sightMax);
					u--;
				}
				
				scores[i][j] = right*left*up*down;
				System.out.print("score=" + right*left*up*down);
				System.out.println();
			}
		}
		
		
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				System.out.print(scores[i][j]);
			}
			System.out.println();
		}
		
		
		int maxScore = 0;
		int maxi = 0;
		int maxj = 0;
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				if (scores[i][j] > maxScore) {
					maxScore = scores[i][j];
					maxi = i;
					maxj = j;
				}
			}
		}
		System.out.println("Max score is " + maxScore + " at " + maxi + ", " + maxj);

	}
	
	public static void reset() {
		right = 0;
		left = 0;
		up = 0;
		down = 0;
	}

}

/*
 * Plan:
 * Learn to use 2D arrays
 * 		set up 2 arrays: one for input data representation, one for visible / non visible
 * Set up functions to check from up down left right
 * 		if visible: 1; else: leave as 0
 * Count number of 1's in array*
 * 
 */

