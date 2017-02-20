package com.yifeng.BasicSort;
/*
 * bubblesort:
 * 相邻两个数做比较，如果前一个数比后一个数大，交换该两数;
 */
public class BubbleSort {
	public static void bubbleSort(int[] nums){
		int temp;
		for(int i = 0; i < nums.length - 1; i++){//从第一个数开始比，比的第几次，最多是倒数第二个数和倒数第一个数比
			for(int j = 0; j < nums.length - 1 - i;j++){//还剩多少个数没比
				if(nums[j] > nums[j + 1]){
					temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
		}
	}

public static void main(String[] args){
		int[] nums = {2,3,4,12,23,12,22,123,21,1,32};
		bubbleSort(nums);
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i] + " ");
		}
	}
}