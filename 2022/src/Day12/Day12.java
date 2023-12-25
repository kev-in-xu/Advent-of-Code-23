package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12 {
	
	static int xSize = 0;
	static int ySize = 0;
	final static Node NONE = new Node(99,-1,-1);
	static Node next = new Node(0,0,0);
	static int [][] stepMap = new int[154][41];
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day12Inputs.txt");
		Node start, end;
		String input;
		
		start = new Node(0,0,0);
		end = new Node(0,0,0);
		
		
		// SCANS FILE TO SET UP ARRAY
		Scanner in = new Scanner(SourceFile);
		
		while (in.hasNext()) {
			input = in.nextLine();
			ySize++;
			xSize = input.length();
		}//sizeGet while loop end
		in.close();
		
		System.out.println("xSize=" + xSize);
		System.out.println("ySize=" + ySize);
		
		Node[][] map = new Node[xSize][ySize];
		//int [][] stepMap = new int[xSize][ySize];
		
		
		
		//scans file to setup each node
		
		Scanner mapIn = new Scanner(SourceFile);

		for (int j=0;j<ySize;j++){
			input = mapIn.nextLine();
		
			for (int i=0;i<input.length();i++) {
				map[i][j] = new Node((int) input.charAt(i)-97,i,j);
				
				if (input.charAt(i) == 'S') {
					map[i][j] = new Node(0,i,j);
					start = map[i][j];
				}
				if (input.charAt(i) == 'E') {
					map[i][j] = new Node(25,i,j);
					end = map[i][j];
				}
			}
		}//map-make while loop end
		mapIn.close();
		
		
		for (int j=0;j<xSize;j++) {
			for (Node n : map[j]) {
				System.out.print(n.getAlt() + " ");
			}
			System.out.println();
		}
		
		//System.out.println(start.getAlt());

		//System.out.println(end.getAlt());
		
		
		int yes = findPath(map, start, end, 0);
		System.out.println(yes);
		
		for (int j=0;j<xSize;j++) {
			for (int n : stepMap[j]) {
				System.out.print(n);
			}
			System.out.println();
		}
		
	} //end main method
	
	public static int findPath(Node[][] map, Node curr, Node end, int steps) {
		int x = curr.getX();
		int y = curr.getY();
		
		System.out.println("Coordinates: " + x + " " + y + " alt: " + curr.getAlt());
		
		//if at the end, return steps;
		if (curr.getX() == end.getX() && curr.getY() == end.getY()) {
			return steps;
		}
		
		stepMap[curr.getX()][curr.getY()] = 1;
		
		//check if can go OR VISITED**
		//go and recall method at next node
		
		Node[] neighbors = getNeighbor(map,curr);
		
		
		// >0
		if (checkStep(neighbors, curr, 0)) {
			next.go();
			steps++;
			System.out.println("up" + steps);
			findPath(map, next, end, steps);
		} 
		else if (checkStep(neighbors, curr, -1)) {
			next.go();
			steps++;
			System.out.println("same" + steps);
			findPath(map, next, end, steps);
		} 
		else if (checkStep(neighbors, curr,-25)) {
			next.go();
			steps++;
			findPath(map, next, end, steps);
		} 
		
		return -1;
	}
	
	public static Node[] getNeighbor(Node[][] map, Node curr) {
		Node [] list = new Node[4];
		
		int x = curr.getX();
		int y = curr.getY();
		
		//get right
		if (x == xSize-1) {list[0] = NONE;} 
		else {list[0] = map[x+1][y];}
		
		//get up
		if (y == 0) {list[1] = NONE;} 
		else {list[1] = map[x][y-1];}
		
		//get down
		if (y == ySize-1) {list[2] = NONE;} 
		else {list[2] = map[x][y+1];}
		
		//get left
		if (x == 0) {list[3] = NONE;} 
		else {list[3] = map[x-1][y];}
		
		return list;
	}//end get neighbors
	
	
	public static boolean checkStep(Node[] list, Node curr, int diff) {
		
		for (Node n : list) {
			System.out.println("N coordinates: " + n.getX() + " " + n.getY() + " height: " + n.getAlt());
			if (!n.hasGone() && (n.getAlt() - curr.getAlt()) > diff && curr.canGo(n)) {
			//System.out.print("curr:" + curr.getAlt() + " " + n.getAlt());
			//if (!n.hasGone()) {
				next = n;
				
				System.out.println("true");
				return true;
			}
		}
		System.out.println("false");
		return false;
	}
	
	
	
}
