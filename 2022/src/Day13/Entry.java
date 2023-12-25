package Day13;

public class Entry {
	private int value, lvl;
	
	public Entry(int value, int lvl) {
		this.value=value;
		this.lvl=lvl;
	}
	
	public void listify() {
		lvl++;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public String toString() {
		return "v: " + value + " l: " + lvl;
	}
	
	public int equalsLvl(Entry other) {
		return other.lvl - this.lvl;
	}
	
	public int equalsValue(Entry other) {
		return this.value - other.value;
	}
}
