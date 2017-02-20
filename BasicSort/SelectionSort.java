package com.yifeng.BasicSort;
/** 
 * @author guoyifeng
 *基本思路:在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 *然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
 */
public class SelectionSort {
	/*
	 * 注意不要重写swap方法，因为java是值传递，重写swap只是将想交换的实际参数的各自复制给交换，
	 * 数组中的实际参数并没有发生任何改变！！！！！
	 */
	public static void selectSort(int[] nums) {
		if(nums == null || nums.length == 0){
			return;
		}
		int size = nums.length;
		int temp = 0;
		int k;
		for (int i = 0; i < size; i++) {
			k = i;//因为每次是将剩余数中的最小值与之前选好了的最小值的下一个数交换，则k ＝ i 表示每次要更新的最小值;
			for (int j = size - 1; j > i; j--) {
				if (nums[j] < nums[k]) {//找到剩余数中的最小值;
					k = j;
				}
			}
			//exchange two numbers
			temp = nums[i];
			nums[i] = nums[k];
			nums[k] = temp;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 3, 2, 5, 12, 23, 11, 6, 7, 32, 8, 14, 31, 24 };
		selectSort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
