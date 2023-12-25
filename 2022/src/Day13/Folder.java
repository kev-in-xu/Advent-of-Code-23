package Day13;

import java.util.*;

public class Folder {
	
	private Folder parent;
	private ArrayList<Folder> things;
	private int value;
	
	// for root folder
	public Folder() {
		value = -1;
		this.parent = this;
		things = new ArrayList<Folder>();
	}
	
	// for subsequent folders
	public Folder(Folder parent) {
		value = -1;
		this.parent = parent;
		things = new ArrayList<Folder>();
	}
	
	
	public void addNum(int value) {
		
		things.add(new Num(this, value));
	}
	
	public Folder push()
	{
		Folder a = new Folder(this);
		things.add(a);
		return a;
	}
	
	public Folder pop() {
		return parent;
	}
	
	public Folder next() {
		Folder a = things.get(things.size()-1);
		things.remove(things.size()-1);
		return a;
	}
	
	public boolean isEmpty() {
		return things.isEmpty();
	}

}

/*
 * what do I want when someone makes a folder?
 * [DONE] I want it to hold files
 * [DONE] I want it to hold other folders
 * [DONE] I want it to know the folder it came from and can return
 * [DONE] I want to keep track of size
 * [DONE] I want it to return size 
 * 
 * 
 * addNewFolder(parent, new);
 * tree.newFolder(new)
 * parent.addNewFolder(new);
 */
