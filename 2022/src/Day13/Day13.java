package Day13;

import java.io.*;
import java.util.*;

public class Day13 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day13Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		String left, right;
		Queue<Entry> leftList, rightList; 
		
		int index = 0;
		int sum = 0;
		
		boolean correct = false;

		while (in.hasNext()) {
			left = in.nextLine();
			right = in.nextLine();
			if (in.hasNext()) {in.nextLine();}
			index++;
			System.out.println("\nindex: " + index);
			
			leftList = parse(left);
			rightList = parse(right);
			
			//System.out.println(leftList);
			//System.out.println(rightList);
			
			
			if(compareList(leftList,rightList)) {
				sum += index;
			}
			
			//System.out.println(sum);
			
			
		}//end file while
		in.close();
		
		System.out.println(sum);
		
	}//end main
	
	public static Queue<Entry> parse(String s) {
		Queue<Entry> list = new LinkedList<Entry>();
		int lvl = 0;
		list.getClass();
		
		for (int i=0;i<s.length();i++) {
			switch (s.charAt(i)) {
			case '[': 
				lvl++;
				if (s.charAt(i+1) == ']') {
					list.add(new Entry(-1,lvl));
				}
				break;
			case ']': 
				lvl--;
				break;
			case ',': break;
			case '1':
				if (s.charAt(i+1) == '0') {
					list.add(new Entry(10,lvl));
					i++;
				}else {
					list.add(new Entry(1,lvl));
				}
				break;
			default:
				list.add(new Entry((int) s.charAt(i) - 48,lvl));
			}
		}
		
		return list;
	}// end parse
	
	
	public static boolean compareList(Queue<Entry> a, Queue<Entry> b) {

		Entry left, right;
		Boolean lListed, rListed;
		
		while (a.size() > 0 || b.size() > 0) {
			left = a.remove();
			right = b.remove();
			
			
			if (left.getValue() == -1 && right.getValue() == -1) {
				if (left.equalsLvl(right) == 0) {
					continue;
				}
				if (left.equalsLvl(right) < 0) {
					return true;
				}
				if (left.equalsLvl(right) > 0) {
					return false;
				}
			}
			
			//makes both same level
			while (left.equalsLvl(right) !=0) {
				if (left.equalsLvl(right) < 0) {
					right.listify();
				} else {
					left.listify();
				}
			}
			System.out.print(left + "||" + right + " ");
			
			//compare int
			if (left.equalsValue(right) < 0) {
				System.out.println("right bigger");
				return true;
			}
			if (left.equalsValue(right) > 0) {
				System.out.println("left bigger");
				return false;
			}
			
			// WITH INTS BEING EQUAL, check empty list
			if (a.peek() == null) {
				return true;
			}
			if (b.peek() == null) {
				return false;
			}
			
			
			// WITH LIST BEING FULL, check drop in level
			int x = a.peek().equalsLvl(b.peek());
			if (a.peek().equalsLvl(left) < 0 || b.peek().equalsLvl(right) < 0) {
				if (x<0) {
					System.out.println("left list empty");
					return false;
				}
				if (x>0) {
					System.out.println("right list empty");
					return true;
				}
			}
	
			
		}
		
		return true;
	}
	
}
/*
 * I will need to parse two strings as a time, 
 * a boolean that tracks left vs right
 * int to 
 * 
 * How to parse string?
 * 
 * Each number in the list has a stack value?
 * 
 * if stack values don't match, 
 * 
 */
