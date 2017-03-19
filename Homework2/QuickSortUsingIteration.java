package com.yifeng.Homework2;

import java.util.*;
import java.io.*;

public class QuickSortUsingIteration {

	public static int partitioning(int[] nums, int lo, int hi) {

		int pivot = nums[lo];// let number at lo position as the pivot;
		while (hi > lo) {
			while (hi > lo && pivot < nums[hi]) {
				hi--;// hi pointer moves to left;
			}
			nums[lo] = nums[hi];

			while (hi > lo && nums[lo] < pivot) {
				lo++;// lo pointer moves to right;
			}
			nums[hi] = nums[lo];
		}
		nums[lo] = pivot;// retrieve the covered number at the very beginning.
							// Then the left half of array is less than pivot
							// and the left half is greater than pivot;
		return lo;// find this position and use in future recursion.

	}

	public static void quickSort(int[] nums) {
		Stack<Integer> stack = new Stack<Integer>();
		//push position hi and position lo into stack to initialize the range of stack;
		//they are also the first loop's lo and high;
		stack.push(0); 
		stack.push(nums.length - 1); 
		
		while (!stack.isEmpty()) { //as long as stack is not empty
			
			//initialize current loop's hi and lo;
			int hi = stack.pop();
			int lo = stack.pop(); 

			int pivot = partitioning(nums,lo, hi);//revoke partitioning() find pivot of the current array
			
			//if there are elements existing between pivot and hi,push them into stack;
			if(pivot + 1 < hi){
				stack.push(pivot + 1); //next loop's lo
				stack.push(hi); 
			}
			//if there are elements existing between lo and pivot,push them into strack;
			if(lo + 1 < pivot){
				stack.push(lo); 
				stack.push(pivot);//next loop's hi
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// to get 1,100,000 input numbers from external file;
		Scanner in = null;
		in = new Scanner(new FileReader("/Users/guoyifeng/Documents/out1.txt"));
		ArrayList<String> al = new ArrayList<String>();
		String pattern = " ";
		in.useDelimiter(pattern);
		while (in.hasNext()) {
			String s = in.next();
			al.add(s);
		}
		in.close();
		int len = al.size();
		// to check how many random numbers have been initialized;
 System.out.println("The number is " + len);

		// transform string input into integer;
		int[] nums = new int[len];
		for (int i = 0; i < len; i++) {
			nums[i] = Integer.parseInt(al.get(i));
		}

		// revoke quicksort()
		long startTime = System.currentTimeMillis();
		if (nums.length > 0) {
			quickSort(nums);
		}
		long endTime = System.currentTimeMillis();

		System.out.println("The quick sort using recursion with "+ len + " input cost " + (endTime - startTime)
				+ " ms to complete sort.");

		// to print the sorted array with 1,100,000 entries;
//		 for (int i = 0; i < nums.length; i++) {
//		 System.out.println(nums[i] + " ");
//		 }
	}

}
