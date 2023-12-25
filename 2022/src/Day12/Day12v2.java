package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12v2 {
	
	static int xSize = 154;
	static int ySize = 41;
	final static Node NONE = new Node(99,-1,-1);
	static Node next = new Node(0,0,0);
	static int [][] stepMap = new int[xSize][ySize];
	static Node [][] map = new Node[xSize][ySize];
	static Queue<Node> toVisit = new LinkedList<>();
	static Queue<Integer> stepList = new LinkedList<>();
	static int steps;
	static ArrayList<Node> aList = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day12Inputs.txt");
		Node curr, start, end;
		String input;
		
		start = new Node(0,0,0);
		end = new Node(0,0,0);
		
		
		/* SCANS FILE TO SET UP ARRAY
		Scanner in = new Scanner(SourceFile);
		
		while (in.hasNext()) {
			input = in.nextLine();
			ySize++;
			xSize = input.length();
		}//sizeGet while loop end
		in.close();
		
		System.out.println("xSize=" + xSize);
		System.out.println("ySize=" + ySize);
		
		//Node[][] map = new Node[xSize][ySize];
		//int [][] stepMap = new int[xSize][ySize];
		 */
		
		
		
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
		
		
		//add neighbors to each node
		for (int j=0;j<ySize;j++) {
			for (int i=0;i<xSize;i++) {
				map[i][j].addNeighbors(getAdjacent(map,map[i][j]));
			}
		}
		
		
		System.out.println("Height map:");
		for (int j=0;j<ySize;j++) {
			for (int i=0;i<xSize;i++) {
				System.out.print(map[i][j].getAlt());
			}
			System.out.println();
		}
		
		//System.out.println(start.getAlt());

		//System.out.println(end.getAlt());
		
		//findPath()
		// needs: toVisit stack; start, end, steps
		//findPath(toVisit,start,end,0);
		
		//makes aList of start points
		for (int j=0;j<ySize;j++) {
			for (int i=0;i<xSize;i++) {
				if (map[i][j].getAlt() == 0) {
					aList.add(map[i][j]);
				}
			}
		}
		
		int minSteps = 999;
		
		for (Node node : aList) {
			
			start = node;
			
			toVisit = new LinkedList<>();
			stepList = new LinkedList<>();
			steps = 0;
			
			//initializes toVisit stack
			for (Node n : start.getNeighbors()) {
				toVisit.add(n);
				stepList.add(1);
			}
			
			for (int j=0;j<ySize;j++) {
				for (int i=0;i<xSize;i++) {
					map[i][j].reset();
				}
			}
			
			boolean atEnd = false;
			// int count = 0;
			
			while(toVisit.size() > 0) {
				curr = toVisit.remove();
				steps = stepList.remove();
				
				//System.out.println("Coords: " + curr.getX() + " " + curr.getY() + " Alt: " + curr.getAlt() 
				//		+ " steps: " + steps + " " + count++);
				
				if (curr.getX() == end.getX() && curr.getY() == end.getY()) {
					atEnd = true;
					break;
				}
				
				map[curr.getX()][curr.getY()].go();
				
				//if a node hasn't been visited, I want to add it to the stack
				//the node also has to be available.
				
				for (Node n : curr.getNeighbors()) {
					if (!n.hasGone() && !toVisit.contains(n)) {
						toVisit.add(n);
						stepList.add(steps+1);
						stepMap[n.getX()][n.getY()]= stepMap[n.getX()][n.getY()]+1;
					}
				}
			} // end while
			
			if (atEnd) {
				System.out.println(steps);
			}else {
				System.out.println("failed");
			}
			
			if (steps < minSteps && atEnd) {minSteps = steps;}
			
		} // end aList for loop
			
		System.out.println(minSteps);

		System.out.println("\nMap of visited tiles:");
		for (int j=0;j<ySize;j++) {
			for (int i=0;i<xSize;i++) {
				System.out.print(stepMap[i][j] + " ");
			}
			System.out.println();
		}
		
	} //end main method
	
	
	
	public static int findPath(Stack<Node> toVisit, Node curr, Node end, int steps) {
		int x = curr.getX();
		int y = curr.getY();
		
		System.out.println("Coordinates: " + x + " " + y + " alt: " + curr.getAlt());
		
		stepMap[curr.getX()][curr.getY()]++;
		
		//if at the end, return steps;
		if (curr.getX() == end.getX() && curr.getY() == end.getY()) {
			return steps;
		}
		
		//check if can go OR VISITED**
		//go and recall method at next node
		return -1;
		
	}
	
	
	public static Queue<Node> getAdjacent(Node[][] map, Node curr) {
		Queue<Node> list = new LinkedList<>();
		
		int x = curr.getX();
		int y = curr.getY();
		
		//get right
		if (x != xSize-1 && curr.canGo(map[x+1][y])) {list.add(map[x+1][y]);}
		
		//get up
		if (y != 0 && curr.canGo(map[x][y-1])) {list.add(map[x][y-1]);}
		
		//get down
		if (y != ySize-1 && curr.canGo(map[x][y+1])) {list.add(map[x][y+1]);}
		
		//get left
		if (x != 0 && curr.canGo(map[x-1][y])) {list.add(map[x-1][y]);}
		
		return list;
	}//end getAdjacent
	
}
