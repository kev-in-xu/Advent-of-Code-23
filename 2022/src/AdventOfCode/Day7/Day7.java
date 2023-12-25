package AdventOfCode.Day7;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day7Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		String input;
		Folder root, current, newFolder;
		ArrayList<Folder> master = new ArrayList<Folder>();
		
		//part 1
		//int totalSize = 0;
		
		
		//part 2
		Folder min;
		int sizeToDelete, minSize;
		
		
		
		input = in.nextLine();
		
		current = new Folder("root");
		root = current;
		
		while (in.hasNext()) {
			
			input = in.nextLine();
			//System.out.println(input);
			
			
			if (input.substring(0,4).equals("$ cd")) {
				if (input.substring(5).equals("..")) { // go back
					
					//System.out.println("FOLDER REPORT: " + current.getName() 
					//		+ " " + current.getSize());
					master.add(current);
					current = current.getParent();
					
				} else { // go to new subfolder (or check if duplicate..?)
					
				newFolder = new Folder(current, input.substring(5));
				current.addSubfolder(newFolder);
				current = newFolder;
				}
				
			} else if (input.substring(0,4).equals("$ ls")) {
				//do nothing?????
			} else if (input.substring(0,3).equals("dir")) {
				//do nothing?????
			} else {
				int size = Integer.parseInt(input.substring(0,input.indexOf(" ")));
				current.addFile(input.substring(input.indexOf(" ")), size);
			}
			// if it scans a $, then it needs to be interpreted without input in next line...
		}
		
		//PART 1
		/*
		for (Folder i : master) {
			if (i.getSize() < 100000) {
				totalSize += i.getSize();
			}
		}
		System.out.println("Total size of folders < 100KB: " + totalSize);
		*/
		
		
		//PART 2
		
		sizeToDelete = root.getSize() - 40000000; 
		minSize = root.getSize();
		min = root;
		
		for (Folder i : master) {
			if (i.getSize() >= sizeToDelete && i.getSize() < minSize) {
				min = i;
				minSize = i.getSize();
			}
		}
		
		System.out.println("Folder to delete: " + min.getName());
		System.out.println("Size: " + minSize);
		
		
		in.close();
	}
}
