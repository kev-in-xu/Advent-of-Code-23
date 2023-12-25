package Day15;


/* Notes Nov 9:
 * 
 * This could be faster if I checked each individual square to see if it is closer to a sensor than the nearest beacon
 * Then I also wouldn't have to check all of the rules, because I will move on the the next one if it is within any of the sensors'
 * "no nearer beacon zone"
 * 
 * 
 * Rather than creating a simulation of each row, painting it multiple times, and then counting, since that is redundant?
 * 
 * 
 */


import java.io.*;
import java.util.*;

public class Day15v2 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day15Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		//int[][] map = new int[4000000][4000000];
		
		// ArrayList<Pair> beacons = new ArrayList<>();
		ArrayList<Line> sensors = new ArrayList<>();
		
		
		
		String input;
		int Sx, Sy, Bx, By, dist;
		
		int distToLine, extraDist;
		
		
		// read input file
		while (in.hasNext()) {
			
			input = in.nextLine();
			
			Sx = Integer.parseInt(input.substring(input.indexOf('x')+2,input.indexOf(',')));
			Sy = Integer.parseInt(input.substring(input.indexOf('y')+2,input.indexOf(':',input.indexOf('y'))));
			
			
			
			Bx = Integer.parseInt(input.substring(input.indexOf('x',input.indexOf("beacon"))+2,input.indexOf(',',input.indexOf("beacon"))));
			By = Integer.parseInt(input.substring(input.indexOf('y',input.indexOf("beacon"))+2));
			
			//System.out.println("Sx: " + Sx + " Sy: " + Sy + " Bx: " + Bx + " By: " + By);
			
			dist = Math.abs(Sx-Bx) + Math.abs(Sy-By);
			
			sensors.add(new Line(Sx, Sy, dist));
		}//end while
		
		
		// checks for all horizons
		for (int horizonY=3000000; horizonY <=4000000; horizonY++) {
			int[] horizon = new int[4000001];
			if (horizonY % 100 == 0) {
				System.out.println("checking: " + horizonY);
			}
			
			for (Line l : sensors) {
				distToLine = Math.abs(horizonY-l.Sy);
				
				extraDist = 0;
				
				Sx = l.Sx;
				
				if (l.dist > distToLine) {
					extraDist = l.dist-distToLine;
					//System.out.println("extraDist: " + extraDist);
					if (Sx >= 0 && Sx <= 4000000) {
						horizon[Sx] = 1;
					}
				}
				
				for (int i=0; i<=extraDist; i++) {
					if (Sx+i >= 0 && Sx+i <= 4000000) {
						horizon[Sx+i] = 1;
					}
					
					if (Sx-i >= 0 && Sx-i <= 4000000) {
						horizon[Sx-i] = 1;
					}
					
				}
			}
			
			
			int count = 0;
			for (int i : horizon) {
				if (i == 1) {
					count++;
				}
			}
			if (count != 4000001) {
				System.out.println(count);
				for (int i = 0; i<=4000000; i++) {
					if (horizon[i] == 0) {
						System.out.println("x: " + i);
						System.out.println("y: " + horizonY);
						System.out.println("product: " + (i * horizonY));
					}
				}
				return;
			}
		}
	
		
	}//end main
	
}
