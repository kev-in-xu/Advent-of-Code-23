package Day12;

import java.util.*;

public class Node {
	boolean visited;
	int alt, x, y;
	Queue<Node> neighbors;
	
	/*
	 * what do I want this to have?
	 * 
	 * track whether I've visited, track the height to know whether it's traversable
	 * 
	 * track nearby nodes?
	 * 
	 * 
	 */
	
	public Node (int alt, int x, int y) {
		this.alt = alt;
		visited = false;
		this.x = x;
		this.y = y;
		neighbors = new LinkedList<>();
	}
	
	public void addNeighbors(Queue<Node> list) {
		neighbors = list;
	}
	
	public void go() {
		visited = true;
	}
	
	public void reset() {
		visited = false;
	}
	
	public boolean hasGone() {
		return visited;
	}
	
	public boolean canGo(Node n) {
		if (n.getAlt()-alt < 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getAlt() {
		return alt;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Queue<Node> getNeighbors(){
		return neighbors;
	}
}
