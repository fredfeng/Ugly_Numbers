package ugly;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

/**
 * 
 * combs: 0, 1, and 2 indicate nothing, +, and - respectively
 * @author Rishabh Sharma
 *
 */
public class Main {

	private static Reader input;
	private static PrintWriter output;
	private static Scanner sc;
	private static String result = "";
	private static int[] combs;
	/** main method for class. */
	public static void main(String[] args) {
		try {
			input = new FileReader("C:\\Users\\A\\Desktop\\MS\\csci174\\assignments\\CodeEval_Data_Files\\Ugly.txt");
			output = new PrintWriter(System.out);
			sc = new Scanner(input);
			access();
			output.close();        
		} catch (IOException e) {
		}
	}

	/**
	 * method that goes accesses each test case. 
	*/
	public static void access() {
		while (sc.hasNextLine()) {
			int count = 0;
			String str = sc.nextLine();
			int len = str.length();
			combs = new int[len - 1];
			// execute till all the possible combinations of the numbers are checked
			while (len != 1 && true) {
				if (isUgly(str, combs)) {
					count++;
				}
				if (isComplete(combs)) {
					break;
				}
			iterate();
			}
			if (len == 1) {
				int check = Integer.parseInt(str);
				if (check % 2 == 0 || check % 3 == 0 || check % 5 == 0 || check % 7 == 0) {
					count++;
				}
			}
			result = result + count + "\n";
		}
		output.print(result);
	}

	
	
	/** 
	 * takes in String of digits and a combination and returns whether they produce 
	 * an ugly number or not. 
	 */
	public static boolean isUgly(String inp, int[] com) {
		long answer = 0;
		long operator = 1;
		long operand = Integer.parseInt(String.valueOf(inp.charAt(0)));
		for (int i = 0; i < com.length; i++) {
			if (com[i] == 0) {
				operand = operand * 10 + Integer.parseInt(String.valueOf(inp.charAt(i + 1)));
				continue;
			} else {
				if (operator == 1) {
					answer += operand;
				} else {
					answer -= operand;
				}
				operator = com[i];
				operand = Integer.parseInt(String.valueOf(inp.charAt(i + 1)));
			}
		}
		if (operator == 1) {
			answer += operand;
		} else {
			answer -= operand;
		}
		return (answer % 2 == 0 || answer % 3 == 0 || answer % 5 == 0 || answer % 7 == 0);
	}

	/** iterates combs as necessary to produce next combination. */
	public static void iterate() {
		int len = combs.length;
		for (int i = len - 1; i >= 0; i--) {
			if (combs[i] != 2) {
				combs[i]++;
				for (int j = i + 1; j < len; j++) {
					combs[j] = 0;
				}
				break;
			}
		}
	}

	/** returns whether finished going through all combinations. */
	public static boolean isComplete(int[] inp) {
		for (int i = 0; i < inp.length; i++) {
			if (inp[i] != 2) {
				return false;
			}
		}
		return true;
	}

}