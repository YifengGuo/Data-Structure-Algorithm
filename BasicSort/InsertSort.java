package com.yifeng.BasicSort;
/**
 * 
 * @author guoyifeng
 *思路:类似于斗地主摸牌时候将新摸的牌与左手中已经排序好的牌做比较，并且只需要比较到何时左边的那张牌比新摸的牌小即可
 *前提是之前的已经排好序；
 */
public class InsertSort {
	
	public static void insertSort(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int size = nums.length;
		int target;
		int i,j = 0;;
		for (i = 0; i < size; i++) {
			
			target = nums[i];//新摸的那张牌，或本次循环需要和之间元素比较的那个值
			
			for (j = i; j > 0 && target < nums[j - 1]; j--) {//除非比前面所有的都小，否则只要前面的比他大，就将前一个元素右移一位;j--继续和再前一个值比较;
				nums[j] = nums[j - 1];
			}
			nums[j] = target;//j--后，当前j所在的位置就是target应该在的位置，将target值赋给他；
		}
	}

	public static void main(String[] args) {
		int[] nums = { 3, 2, 5, 12, 23, 11, 6, 7, 32, 8, 14, 31, 24 };
		insertSort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
