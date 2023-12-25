package Day16;

import java.io.*;
import java.util.*;

public class Day16 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day16Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		String input, nodeName, connectionString;
		int flowRate;
		String[] connectionList;
		
		ArrayList<Node> nodes = new ArrayList<>();
	
		// read input file
		while (in.hasNext()) {
			input = in.nextLine();
			
			nodeName = input.substring(6,8);
			flowRate = Integer.parseInt(input.substring(23,input.indexOf(";")));
			
			nodes.add(new Node(nodeName, flowRate));

			// how do I make this into a proper data structure?
			
			// I can make an array of all the nodes first, then add all the connections, because otherwise I am adding a connection
			// that hasn't been instantiated yet

		}//end while
		
		
		
		// second read -- adds all the connections
		in = new Scanner(SourceFile);
		int i = 0;
		while (in.hasNext()) {
			input = in.nextLine();
			
			connectionString = input.substring(input.indexOf("valves")+7);
			connectionList = connectionString.split(", ");
			
			for (String connection : connectionList) {
				for (Node n : nodes) {
					if (connection.equals(n.name)) {
						nodes.get(i).addConnection(n);
					}
				}
			}
			i++;
		}//end while
		
		//System.out.println(nodes);
		
		
		Node currentNode = null;
		
		for (Node n : nodes) {
			if (n.name.equals("AA")) {
				currentNode = n;
			}
		}
		
		ArrayList<Node> onNodes = new ArrayList<>();
		ArrayList<Node> past = new ArrayList<>();
		
		System.out.println(recursiveSearch(nodes, onNodes, past, currentNode, 1, 0, 0));
		
		
	
		
	}//end main
	
	public static int recursiveSearch(ArrayList<Node> nodes, ArrayList<Node> onNodes, ArrayList<Node> pastNodes, Node current, int turn, int currentFlow, int total) {
		if (turn > 6) {
			return total;
		}
		pastNodes.add(current);
		//System.out.println(current + " on turn " + turn);
		//System.out.println(pastNodes);
		
		// right now the problem is that each node is turned on once within the whole recursive call
		// rather than being turned on within each branch of the tree recursion. (i.e. if turned on in left branch, it'd be turned on already in right branch
		
		
		//if (current.flow != 0 && !current.on) {
		if (current.flow != 0 && !onNodes.contains(current)) {
			
			currentFlow += current.turnOn();
			onNodes.add(current);
			
			System.out.println("at node " + current.name + ". turned on flow " + currentFlow + " on turn " + turn);
			for (Node n : pastNodes) {
				System.out.print(n.name + " ");
			}
			System.out.println();
			//System.out.println(pastNodes);
			
			
			//System.out.println(onNodes);
			recursiveSearch(nodes, onNodes, pastNodes, current, turn+1, currentFlow, total + currentFlow);
		}
		
		for (Node n : current.connections) {
			recursiveSearch(nodes, onNodes, pastNodes, n, turn+1, currentFlow, total + currentFlow);
			
		}
		
		//System.out.println("turn: " + turn + " node: " + current + " flow: " + currentFlow + " total: " + total);
		pastNodes.remove(current);
		//turn--;
		return total;
		
	}
}

class Node extends Object implements Comparable<Node> {
	public String name;
	public boolean on = false;
	int flow;
	public int currentFlow;
	public List<Node> connections = new ArrayList<>();
	
	
	public Node(String name, int flow) {
		this.name = name;
		this.flow = flow;
	}
	
	public void addConnection(Node n) {
		connections.add(n);
	}
	
	public int turnOn() {
		on = true;
		currentFlow = flow;
		return currentFlow;
	}
	
	public Node goTo(int index) {
		if (index < connections.size() - 1) {
			return connections.get(index);
		}
		return null;
	}
	
	public String toString() {
		if (connections == null) {
			return name + " " + flow;
		}
		String out = "" + name + " " + flow + " ";
		
		out += "{";
		for (Node n : connections) {
			out += n.name + ", ";
		}
		out = out.substring(0,out.lastIndexOf(" ")-1) + "}";
		
		return out;
	}
	
	public int compareTo(Node other) {
		System.out.println("this.name: " + this.name);
		System.out.println("other.name: " + other.name);
		return this.name.compareTo(other.name);
	}
	
}

//class Pair {}

