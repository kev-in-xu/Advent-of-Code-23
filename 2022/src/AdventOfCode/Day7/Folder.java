package AdventOfCode.Day7;

import java.util.ArrayList;

public class Folder {
	
	private Folder parent;
	public ArrayList<Folder> subfolders;
	public ArrayList<Doc> files;
	public String name;
	
	// for root folder
	public Folder(String name) {
		this.name = name;
		subfolders = new ArrayList<Folder>();
		files = new ArrayList<Doc>();
		this.parent = this;
	}
	
	// for subsequent folders
	public Folder(Folder parent, String name) {
		this.parent = parent;
		this.name = name;
		subfolders = new ArrayList<Folder>();
		files = new ArrayList<Doc>();
	}
	
	
	public void addFile(String fileName, int size) {
		
		files.add(new Doc(fileName, size));
	}
	
	public void addSubfolder(Folder sub)
	{
		subfolders.add(sub);
	}
	
	public Folder getParent() {
		return parent;
	}
	
	public int getSize() {
		int size = 0;
		
		for (Doc i : files) {
			size += i.getSize();
		}
		
		for (Folder i : subfolders) {
			size += i.getSize();
		}
		
		return size;
	}
	
	public String getName() {
		return name;
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
