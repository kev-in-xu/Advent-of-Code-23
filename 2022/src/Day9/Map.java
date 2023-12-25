package Day9;

public class Map {
	int[][] map, tailMap;
	Tail[] tails;
	
	int hX, hY, tX, tY; // coords for head and tail
	int tailSize;
	
	public Map(int xSize, int ySize, int tailSize) {
		map = new int[xSize][ySize];
		tailMap = new int[xSize][ySize];
		tails = new Tail[tailSize];
		this.tailSize = tailSize;
	}
	
	public void startAt(int x, int y) {
		hX = x;
		tX = x;
		hY = y;
		tY = y;
		for (int i=0;i<tailSize;i++) {
			tails[i] = new Tail(x,y);
		}
	}
	
	public void moveUp(int dist) {
		for (int i=0;i<dist;i++) {
			hY++;
			moveTails();
		}
	}
	
	public void moveDown(int dist) {
		for (int i=0;i<dist;i++) {
			hY--;
			moveTails();
		}
	}
	
	public void moveRight(int dist) {
		for (int i=0;i<dist;i++) {
			hX++;
			moveTails();
		}
	}
	
	public void moveLeft(int dist) {
		for (int i=0;i<dist;i++) {
			hX--;
			moveTails();
		}
	}
	
	
	private void moveTails() {
		tails[0].move(hX,hY);
		
		for (int i=1;i<tailSize;i++) {
			tails[i].move(tails[i-1].getX(), tails[i-1].getY());
		}
		
		tailMap[tails[tailSize-1].getX()][tails[tailSize-1].getY()] = 1;
	}
	
	/*
	 * How to set this up?
	 * 
	 * each tail as its own object?
	 * 
	 * nested methods calling each other?
	 * - that will almost certainly work but doens't seem efficient
	 * - also that will require so many variables... but I will need a lot of variables anyways, so maybe that's fine?
	 * 
	 * can I do it where I supply a length 9 as a parameter and have the program simulate it..?
	 */
	
	
	public int gethX() {
		return hX;
	}
	public int gethY() {
		return hY;
	}
	public int gettX() {
		return tX;
	}
	public int gettY() {
		return tY;
	}
	
	
	
	public int[][] getTailMap(){
		return tailMap;
	}
	
	public int[][] getMap(){
		return map;
	}
	
}

// up == y++
// down == y--
