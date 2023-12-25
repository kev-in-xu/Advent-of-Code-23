package AdventOfCode;

import java.io.*;
import java.util.Scanner;

public class Day2 {
	static int score = 0;
	public static void main(String[] args) throws FileNotFoundException {
		
		
		File SourceFile = new File("Day2Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		
		String opponent, play;
		
		score = 0;
		
		while (in.hasNext()) {
			opponent = in.next();
			play = in.next();
			
			addPlayScore(opponent, play);
			addWinScore(opponent, play);
			
		}
		System.out.println((int) 'Y' + (int) 'A');
		
		System.out.println(score);
		//add points for throw
		
		//add points for win / lose
		in.close();
	}
	
	public static void addPlayScore(String opp, String play) {
		
		int outcome = (((int) play.charAt(0))+(int) opp.charAt(0)) % 3;
		
		switch (outcome) {
		case 1: score += 1; break;
		case 2: score += 2; break;
		default: score += 3; break;
		}
		//use mod 3?
		
		//AY --> A = 1
		//BX --> A
		//CZ --> A
		
		
		//BY -- B = 2
		//CX
		//AZ
		/*
		25
		34
		16
	
		15
		24
		36
		*/
				
		
		
		/* PART 1
		
		char hand = play.charAt(0);
		switch (hand) {
		case 'X': score += 1; break;
		case 'Y': score += 2; break;
		default: score += 3;
		}
		*/
	}
	
	public static void addWinScore(String opp, String play) {
		
		// PART 2
		char outcome = play.charAt(0);
		
		switch (outcome) {
		case 'X': score += 0; break;
		case 'Y': score += 3; break;
		default: score += 6;
		}
		
		/* PART 1
		int outcome = (((int) play.charAt(0))-(int) opp.charAt(0));
		
		switch (outcome) {
		case 23: score += 3; break;
		case 21: score += 6; break;
		case 24: score += 6; break;
		}
		*/
		
		//a x rock
		//b y paper
		//c z scissors
		
		
	}
	
}
