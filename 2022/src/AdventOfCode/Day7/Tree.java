package AdventOfCode.Day7;

import java.util.ArrayList;

public class Tree {
	
	private int size;
	private ArrayList<Tree> children;
	public ArrayList<Doc> files = new ArrayList<Doc>();
	
	Folder root, L1, L2, L3, L4, L5, L6;
	

	public Tree()
	{
		size = 0;
		children = new ArrayList<>();
	}
	
	/*
	exitToParent()
	changeFolder(input.substring(4));
	makeNewFolder(input.substring(4));
	*/
	
	
	
	
	
	public void addSubtree(Tree subtree)
	{
		children.add(subtree);
	}
	
	public void addFile(String fileName, int size) {
		
		files.add(new Doc(fileName, size));
	}
	
	
	public int getSize() {
		size = 0;
		
		for (Doc i : files) {
			size += i.getSize();
		}
		
		for (Tree i : children) {
			size += i.getSize();
		}
		
		return size;
	}

}
