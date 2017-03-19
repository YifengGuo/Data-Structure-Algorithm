package com.yifeng.Homework3;

public class QuickSortUsingInsertionSort {
public static final int M = 5;//M is transform parameter and 5~15 will work well in most cases;
	public static int getPivot(int[] nums, int lo, int hi) {
		int pivot = nums[lo];//let number at lo position as the pivot;
		while (hi > lo) {
			while (hi > lo && pivot <= nums[hi]) {
				hi--;//hi pointer moves to left;
			}
			nums[lo] = nums[hi];

			while (hi > lo && nums[lo] <= pivot) {
				lo++;//lo pointer moves to right;
			}
			nums[hi] = nums[lo];
		}
		nums[lo] = pivot;//retrieve the covered number at the very beginning. Then the left half of array is less than pivot and the left half is greater than pivot;
		return lo;//find this position and use in future recursion.
	}

	public static void quickSort(int[] nums, int lo, int hi) {
		if (hi < lo + M) {
			insertSort(nums);
			return;
		}
		int pivot = getPivot(nums, lo, hi);
		// divide recursion
		quickSort(nums, lo, pivot - 1);
		quickSort(nums, pivot + 1, hi);

	}
	
	
	
	
	public static void insertSort(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int size = nums.length;
		int target;
		int i,j = 0;;
		for (i = 0; i < size; i++) {
			
			target = nums[i];
			
			for (j = i; j > 0 && target < nums[j - 1]; j--) {
				nums[j] = nums[j - 1];
			}
			nums[j] = target;
		}
	}
	public static void main(String[] args) {
		int[] a = {3,25,6,21,14,57,2,16,95,23,152,12,63};
		quickSort(a, 0, a.length - 1);
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		}

	}

}
