package Day11;

import java.util.ArrayList;

public class Monkey {
	
	ArrayList<Long> items;
	ArrayList<Integer> targets;
	private int number, inspections;
	
	
	public Monkey(int num) {
		number = num;
		items = new ArrayList<Long>();
		inspections = 0;
	}
	
	public void add(long thing) {
		items.add(thing);
	}
	
	public ArrayList<Long> turn() {
		
		ArrayList<Long> thrown = new ArrayList<Long>();
		targets = new ArrayList<Integer>();
		
		while(items.size() > 0) {
			long i = items.get(0);
			
			i = inspect(i);
			if (i > (2*3*5*7*11*13*17*19)) {
				i = i%(2*3*5*7*11*13*17*19);
			}
			//i = i/3;
			
			thrown.add(i);
			targets.add(chooseTarget(i));
			
			items.remove(0);
		}
		return thrown;
	}
	
	public ArrayList<Integer> getTargets(){
		return targets;
	}
	
	
	private long inspect(long old) {
		long novo = 0;
		inspections++;
		
		switch (number) {
		case 0: 
			novo = old*3; break;
		case 1:
			novo = old*11; break;
		case 2:
			novo = old+6; break;
		case 3:
			novo = old+4; break;
		case 4:
			novo = old+8; break;
		case 5:
			novo = old+2; break;
		case 6:
			//System.out.println(old);
			novo = old*old; break;
		case 7:
			novo = old+5; break;
		default:;
		}
		
		return novo;
	}
	
	private int chooseTarget(long i) {
		int target = 0;
		
		switch (number) {
		case 0:
			target = (i%2) == 0? 2:6; break;
		case 1:
			target = (i%7) == 0? 3:4; break;
		case 2:
			target = (i%3) == 0? 5:1; break;
		case 3:
			target = (i%11) == 0? 7:4; break;
		case 4:
			target = (i%17) == 0? 0:7; break;
		case 5:
			target = (i%5) == 0? 1:3; break;
		case 6:
			target = (i%13) == 0? 2:5; break;
		case 7:
			target = (i%19) == 0? 6:0; break;
		default:;
		}
		
		return target;
	}
	
	public int getInspections() {
		return inspections;
	}

}
