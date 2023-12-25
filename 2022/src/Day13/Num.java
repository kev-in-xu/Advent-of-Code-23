package Day13;

public class Num extends Folder {
	int value;
	
	public Num (Folder f, int value) {
		super(f);
		this.value = value;
	}
	
	public int get() {
		return value;
	}

}
