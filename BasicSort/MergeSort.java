package com.yifeng.BasicSort;

import java.util.*;

public class MergeSort {

	public static void mergeSort(int[] nums) {
		if(nums == null || nums.length == 0){
			return;
		}
		int middle = nums.length / 2;
		if (nums.length > 1) {// exit condition of the recursion;

			// divide
			int[] left = Arrays.copyOfRange(nums, 0, middle);// copy half of the
																// elements into
																// sub-array;
			int[] right = Arrays.copyOfRange(nums, middle, nums.length);

			// divide each sub-array recursively;
			mergeSort(left);
			mergeSort(right);

			// conquer
			merge(nums, left, right);

			for (int i = 0; i < nums.length; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param result
	 * @param left
	 * @param right
	 *            always put smaller element into result[] if there are elements
	 *            in sub-array left;
	 */
	private static void merge(int[] result, int[] left, int[] right) {
		int i = 0;// the point of the result array;
		int l = 0, r = 0;// initialize two start points of two sub-array;
		while (l < left.length && r < right.length) {
			// put smaller element in two array one by one into result[] after
			// comparison;
			if (left[l] < right[r]) {
				result[i] = left[l];
				i++;
				l++;
			} else {
				result[i] = right[r];
				i++;
				r++;
			}
		}
		// if all the elements in one of the sub-array is put into result[],then
		// put all the left elements in another sub-array in to the tail of the
		// result[];
		while (l < left.length) {
			result[i] = left[l];
			l++;
			i++;
		}
		while (r < right.length) {
			result[i] = right[r];
			r++;
			i++;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 2, 32, 22, 21, 12, 42, 123, 54, 36, 1, 56, 66, 78, 99, 124 };

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		mergeSort(nums);
	}

}
