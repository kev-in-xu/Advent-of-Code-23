package Day13;

import java.io.*;
import java.util.*;

public class Day13v3 {
	public static void main(String[] args) throws FileNotFoundException {
		
		File SourceFile = new File("Day13Inputs.txt");
		Scanner in = new Scanner(SourceFile);
		String left, right;
		
		int count = 0;
		int index = 0;
		
		/* PART 1
		while (in.hasNext()) {
			left = in.nextLine();
			right = in.nextLine();
			index++;
			
			System.out.println("\nindex: " + index);
			count += compare(left, right, index);
			
			
			if (in.hasNext()) {in.nextLine();}
			
		}
		in.close();
		
		System.out.print("count: " + count);
		
		*/
		
		// PART 2
		
		LinkedList<String> sorted = new LinkedList<>();
		String input;
		
		while (in.hasNext()) {
			input = in.nextLine();
			//System.out.println(input);
			
			if (input.isBlank()) {
				continue;
			}
			
			int i = 0;
			int result = 0;
			
			while (i < sorted.size()) {
				result = compareSort(input, sorted.get(i));
				if (result > 0) {
					sorted.add(i,input);
					//System.out.println("adding at: " + i);
					break;
				}
				i++;
			}
			
			if (result <= 0) {
				sorted.add(input);
			}
			
			
		}
		in.close();
		
		
		int two = sorted.indexOf("[[2]]");
		int six = sorted.indexOf("[[6]]");
		
		for (String s : sorted) {
			System.out.println(s);
		}
		
		System.out.println("two: " + (two+1) + " || six: " + (six+1) + " || product: " + (two+1)*(six+1));
		
		
	}//end main

	public static int compare(String l, String r, int index){
		
		int result = index;
		//System.out.println("result: " + result);
		
		int leftL, rightL; // L = length
		Integer leftV, rightV; // V = value of char
		char left, right;
		boolean valid = true;
		
		while(result == index) {
			leftL = l.length();
			rightL = r.length();
			
			if (leftL * rightL == 0) {
				result = leftL != 0 ? 0 : index;
				System.out.println("end of string");
				break;
			}
			
			
			left = l.charAt(0);
			right = r.charAt(0);
			
			if (left == ',') {
				l=l.substring(1);
				continue;
			}
			
			if (right == ',') {
				r=r.substring(1);
				continue;
			}
			
			if (left == '[' && right == '[') {
				l=l.substring(1);
				r=r.substring(1);
				continue;
			}
			
			leftV = rightV = null;
			
			
			if (Character.isDigit(left)) {
				leftV = (int) left - 48; // 48 is ascii for 0
				if (left == '1' && l.charAt(1) == '0') { // if 10 is an entry
					leftV = 10;
				}
			} else if (left == ']') {
				leftV = -1;
			}
			
			if (Character.isDigit(right)) {
				rightV = (int) right - 48;
				if (right == '1' && r.charAt(1) == '0') {
					rightV = 10;
				}
			} else if (right == ']') {
				rightV = -1;
			}
			
			System.out.println("left: " + left + " || right: " + right);
			System.out.println("leftV: " + leftV + " || rightV: " + rightV);
			
			if (leftV != null && rightV != null) {
				if (leftV < rightV) {
					break;
				}else if (leftV == rightV) {
					r = rightV == 10 ? r.substring(2) : r.substring(1);
					l = leftV == 10 ? l.substring(2) : l.substring(1);
					continue;
				}
				//System.out.println("leftV > rightV");
				//System.out.println("leftV: " + leftV + " || rightV: " + rightV);
				result = 0;
				continue;
			}
			
			//10 doesn't work rn
			if (left == '[' && rightV != null) { // left has '[', right is number of or ']'
				if (rightV == -1) {
					result = 0;
					break;
					//r = "[]]" + r.substring(1);
				} else if (rightV == 10) {
					r = "[10]" + r.substring(2);
				} else {
					r = "[" + rightV + "]" + r.substring(1);
				}
				continue;
			}
			
			if (left == '[' && right == ']') { // right empty before left
				result = 0; // means right before left, wrong order
				break;
			}
			
			if (left == ']' && right == '[') { // left empty before right
				break; // correct order, return index
			}
			
			if (right == '[' && leftV != null) { // left has '[', right is number
				if (leftV == 10) {
					l = "[10]" + l.substring(2);
				} else {
					l = "[" + leftV + "]" + l.substring(1);
				}
				continue;
			}
			
		}
		
		System.out.println("result: " + result);
		return result;
	}
	
public static int compareSort(String l, String r){
		
		//System.out.println("result: " + result);
		
		int leftL, rightL;
		Integer leftV, rightV;
		char left, right;
		boolean valid = true;
		
		while(true) {
			leftL = l.length();
			rightL = r.length();
			
			if (leftL * rightL == 0) {
				//System.out.println("end of string");
				if (leftL == 0 && rightL == 0) {
					return 0;
				}
				return leftL != 0 ? -1 : 1;
			}
			
			
			left = l.charAt(0);
			right = r.charAt(0);
			
			if (left == ',') {
				l=l.substring(1);
				continue;
			}
			
			if (right == ',') {
				r=r.substring(1);
				continue;
			}
			
			if (left == '[' && right == '[') {
				l=l.substring(1);
				r=r.substring(1);
				continue;
			}
			
			leftV = rightV = null;
			
			
			//gotta deal with 10's
			if (Character.isDigit(left)) {
				leftV = (int) left - 48;
				if (left == '1' && l.charAt(1) == '0') {
					leftV = 10;
				}
			} else if (left == ']') {
				leftV = -1;
			}
			
			if (Character.isDigit(right)) {
				rightV = (int) right - 48;
				if (right == '1' && r.charAt(1) == '0') {
					rightV = 10;
				}
			} else if (right == ']') {
				rightV = -1;
			}
			
			//System.out.println("left: " + left + " || right: " + right);
			//System.out.println("leftV: " + leftV + " || rightV: " + rightV);
			
			if (leftV != null && rightV != null) {
				if (leftV < rightV) {
					return 1;
				} else if (leftV == rightV) {
					r = rightV == 10 ? r.substring(2) : r.substring(1);
					l = leftV == 10 ? l.substring(2) : l.substring(1);
					continue;
				}
				//System.out.println("leftV > rightV");
				//System.out.println("leftV: " + leftV + " || rightV: " + rightV);
				return -1;
			}
			
			//10 doesn't work rn
			if (left == '[' && rightV != null) { // left has '[', right is number of or ']'
				if (rightV == -1) {
					return -1;
					//r = "[]]" + r.substring(1);
				} else if (rightV == 10) {
					r = "[10]" + r.substring(2);
				} else {
					r = "[" + rightV + "]" + r.substring(1);
				}
				continue;
			}
			
			if (left == '[' && right == ']') { // right empty before left
				return -1; // means right before left, wrong order
			}
			
			if (left == ']' && right == '[') { // left empty before right
				return 1; // correct order, return index
			}
			
			if (right == '[' && leftV != null) { // left has '[', right is number
				if (leftV == 10) {
					l = "[10]" + l.substring(2);
				} else {
					l = "[" + leftV + "]" + l.substring(1);
				}
				continue;
			}
			
		}
	}
	
	
}
