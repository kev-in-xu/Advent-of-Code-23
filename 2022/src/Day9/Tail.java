package Day9;

public class Tail {
	int x;
	int y;
	
	
	public Tail(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int hX, int hY) {
		if (hX-x == 2) { // head right
			x++;
			y += Integer.compare(hY, y);
		}
		if (hX-x == -2) { // head left
			x--;
			y += Integer.compare(hY, y);
		}
		if (hY-y == 2) { // head up
			y++;
			x += Integer.compare(hX, x);
		}
		if (hY-y == -2) { // head down
			y--;
			x += Integer.compare(hX, x);
		}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}


/*
 * things a tail has: get coords, move
 * and the map will use an array to keep track of all tail positions
 * 
 * 
 */
