package com.yifeng.BasicSort;

public class QuickSort {

	public static int getPivot(int[] nums, int lo, int hi) {
		// 由于已经将a[lo]中的数保存到temp中，可以理解成在数组a[lo]上挖了个坑，可以将其它数据填充到这来。
		// 从j开始向前找一个比temp小或等于temp的数。当j=8，符合条件，将a[8]挖出再填到上一个坑a[0]中。a[0]=a[8];
		// i++;
		// 这样一个坑a[0]就被搞定了，但又形成了一个新坑a[8]，这怎么办了？简单，再找数字来填a[8]这个坑。这次从i开始向后找一个大于temp的数，
		// 当i=3，符合条件，将a[3]挖出再填到上一个坑中a[8]=a[3], j--;
		int pivot = nums[lo];
		while (hi > lo) {
			while (hi > lo && pivot < nums[hi]) {
				hi--;
			}
			nums[lo] = nums[hi];// 填坑

			while (hi > lo && nums[lo] < pivot) {
				lo++;
			}
			nums[hi] = nums[lo];// 填坑
		}
		nums[lo] = pivot;// 归还开始被填掉的nums[lo]的值；此时比pivot小的都在左边，比pivot大的都在右边;
		return lo;
	}

	public static void quickSort(int[] nums, int lo, int hi) {
		if (hi < lo) {//递归退出条件
			return;
		}
		int pivot = getPivot(nums, lo, hi);
		// divide recursion
		quickSort(nums, lo, pivot - 1);
		quickSort(nums, pivot + 1, hi);

	}
	/*
	 * private static void swap(int a ,int b){ int temp; temp = a; a = b; b =
	 * temp; }
	 */

	public static void main(String[] args) {
		int[] nums = { 2, 3, 4, 6, 12, 25, 13, 42, 7, 23, 21 };
		if (nums.length > 0) {
			quickSort(nums, 0, nums.length - 1);
		}
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
