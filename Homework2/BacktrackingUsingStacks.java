package com.yifeng.Homework2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
/**
 * 
 * @author guoyifeng
 * To run this program,please input the flight number according to the Airline Route.txt on console
 * the number is between 0 and 11
 */
public class BacktrackingUsingStacks {

	public static boolean isDestination(String s1, String s2, ArrayList<String> from, ArrayList<String> des,
			Stack<String> stack) {
		stack.push(s1);
		stack.push(s2);
		while (!stack.isEmpty()) {
			int count = 0;
			if (stack.elementAt(0).contentEquals(stack.elementAt(1))) {
				System.out.println("The flight has arrived in the destination " + stack.elementAt(1));
				stack.pop();
				stack.pop();
				return true;
			} else if (stack.elementAt(0) != stack.elementAt(1)) {
				if (from.contains(stack.elementAt(1))) {
					count = from.indexOf(stack.elementAt(1));
				}
				stack.pop();
				stack.pop();
				stack.push(from.get(count));
				stack.push(des.get(count));
				System.out.println("The flight is currently at " + from.get(count));
			}

		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> from = new ArrayList<String>();
		ArrayList<String> des = new ArrayList<String>();
		Stack<String> stack = new Stack<>();
		BufferedReader br = new BufferedReader(
				new FileReader("/Users/guoyifeng/Documents/Data Strut & Algorithm/Airline Route.txt"));
		String line = "";
		while ((line = br.readLine()) != null) {
			from.add(line.trim().split(",")[0]);
			des.add(line.trim().split(",")[1]);
		}
		br.close();
		// please input the airline route number according to the Airline Route.txt
		Scanner s = new Scanner(System.in);
		int number = s.nextInt();
		if (number > 11 || number < 0) {
			System.out.println("No airline route found.\n");
		}
		System.out.println("The flight starts at " + from.get(number));
		isDestination(from.get(number), des.get(number), from, des, stack);
		s.close();
	}

}
