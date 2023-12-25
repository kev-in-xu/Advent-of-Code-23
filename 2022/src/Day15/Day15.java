package Day15;

import java.io.*;
import java.util.*;

public class Day15 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day15Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		//int[][] map = new int[4000000][4000000];
		
		// ArrayList<Pair> beacons = new ArrayList<>();
		//ArrayList<Line> sensors = new ArrayList<>();
		
		int[] horizon = new int[7000000];
		
		String input;
		int Sx, Sy, Bx, By, dist;
		
		int distToLine, extraDist;
		
		
		//reads input file
		while (in.hasNext()) {
			
			input = in.nextLine();
			
			Sx = Integer.parseInt(input.substring(input.indexOf('x')+2,input.indexOf(',')));
			Sy = Integer.parseInt(input.substring(input.indexOf('y')+2,input.indexOf(':',input.indexOf('y'))));
			
			
			
			Bx = Integer.parseInt(input.substring(input.indexOf('x',input.indexOf("beacon"))+2,input.indexOf(',',input.indexOf("beacon"))));
			By = Integer.parseInt(input.substring(input.indexOf('y',input.indexOf("beacon"))+2));
			
			System.out.println("Sx: " + Sx + " Sy: " + Sy + " Bx: " + Bx + " By: " + By);
			
			dist = Math.abs(Sx-Bx) + Math.abs(Sy-By);
			System.out.println("dist: " + dist);
			
			// parses x, parses y for sensor
			// parses x and y for beacon
			// calculate Manhattan distance
			
			distToLine = Math.abs(2000000-Sy);
			
			extraDist = 0;
			
			if (dist > distToLine) {
				extraDist = dist-distToLine;
				System.out.println("extraDist: " + extraDist);
				horizon[1000000+Sx] = 1;
			}
			
			for (int i=0; i<=extraDist; i++) {
				horizon[1000000+Sx+i] = 1;
				horizon[1000000+Sx-i] = 1;
				
			}
			
			
			// calculate dist to y=2000000
			// paint array
			
			
			// add sensor on map
			// add sensor to sensors
			// add beacon on map
			
			System.out.println();
		}//end while
		
		int count = 0;
		for (int i : horizon) {
			if (i == 1) {
				count++;
			}
		}
		System.out.println(count);

		
		
		
		
	}//end main
}


