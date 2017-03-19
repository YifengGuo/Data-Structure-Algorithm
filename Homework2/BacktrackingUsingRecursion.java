package com.yifeng.Homework2;

import java.util.*;
import java.io.*;
/**
 * 
 * @author guoyifeng
 * To run this program,please input the flight number according to the Airline Route.txt on console
 * the number is between 0 and 11
 */
public class BacktrackingUsingRecursion {

	public static boolean isDestination(String s1, String s2, ArrayList<String> from, ArrayList<String> des)
			throws IOException {
		int count = 0;
		if (s1.contentEquals(s2)) {
			System.out.println("The flight has arrived in the destination " + s2);
			return true;
		} else if (s1 != s2) {
			if (from.contains(s2)) {
				count = from.indexOf(s2);
			}
			System.out.println("The flight is currently at " + from.get(count));
			isDestination(from.get(count), des.get(count), from, des);
			// System.out.println(count);

		} // else return false;

		return false;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> from = new ArrayList<String>();
		ArrayList<String> des = new ArrayList<String>();
		BufferedReader br = new BufferedReader(
				new FileReader("/Users/guoyifeng/Documents/Data Strut & Algorithm/Airline Route.txt"));
		String line = "";
		while ((line = br.readLine()) != null) {
			from.add(line.trim().split(",")[0]);
			des.add(line.trim().split(",")[1]);
		}
		br.close();
		//please input the airline route number according to the Airline Route.txt 
		Scanner s = new Scanner(System.in);
		int number = s.nextInt();
		if(number > 11 || number < 0) {
			System.out.println("No airline route found.\n");
		}
		System.out.println("The flight starts at " + from.get(number));
		isDestination(from.get(number), des.get(number), from, des);
		s.close();
	}

}
