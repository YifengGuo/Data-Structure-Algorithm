package com.yifeng.Homework2;

public class EndOfPrefix {

	public static int prefix(String s, int start) {
		int firstEnd = 0;

		if (s == null || s.length() == 0) {
			return -1;
		}

		char[] arr = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			arr[i] = s.charAt(i);
		}

		if (start >= arr.length) {
			return 0;
		}

		if (arr[start] == '*' || arr[start] == '/' || arr[start] == '+' || arr[start] == '-') {
			start++;
			prefix(s, start);
		} else {
			firstEnd = start;
			System.out.println("The current recursion ends at position of :" + firstEnd);
			start++;
			prefix(s, start);

		}

		return -1;

	}

	public static void main(String[] args) {
		String s = "+*a+b-cs/dk";
		prefix(s, 0);
	}
}
