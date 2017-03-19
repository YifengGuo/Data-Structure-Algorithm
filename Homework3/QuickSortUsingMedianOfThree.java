package com.yifeng.Homework3;

import java.util.*;
import java.io.*;

public class QuickSortUsingMedianOfThree {
	
	public static int getPivot(int[] nums, int lo, int hi) {
		int mid = lo + (hi - lo) / 2;
		if (nums[mid] > nums[hi]) {
			swap(nums, mid, hi);
		}
		if (nums[lo] > nums[hi]) {
			swap(nums, lo, hi);
		}
		if (nums[mid] > nums[lo]) {
			swap(nums, mid, lo);
		}
		//pivot is the median of nums[lo],nums[hi] and nums[mid]
		int pivot = nums[lo];
		
		while (lo < hi) {
			while (nums[hi] >= pivot && hi > lo) {
				hi--;
			}
			nums[lo] = nums[hi];
			while (nums[lo] <= pivot && hi > lo) {
				lo++;
			}
			nums[hi] = nums[lo];
		}
		nums[hi] = pivot;
		return hi;

	}
	
	public static void swap(int[] nums,int a,int b){
		int temp = 0;
		temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	public static void quickSort(int[] nums, int lo, int hi) {
		if (hi < lo) {
			return;
		}
		int pivot = getPivot(nums, lo, hi);
		// divide recursion
		quickSort(nums, lo, pivot - 1);
		quickSort(nums, pivot + 1, hi);

	}

	public static void main(String[] args) throws IOException{
		String path = "//Users/guoyifeng/Downloads/hw2-dataset/data1.";
		BufferedReader br = null;
		List<String> in = new ArrayList<String>();
		int[] arr = null;
		int[] name = { 1024, 2048, 4096, 8192, 16384, 32768 };
		for (int i = 0; i < name.length; i++) {
			try {

				br = new BufferedReader(new FileReader(path + name[i]));
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found!");
			}

			String line = "";
			int lineNum = 0;
			try {
				while ((line = br.readLine()) != null) {
					in.add(line.trim());
					lineNum++;
				}
				arr = new int[lineNum];
				for (int j = 0; j < lineNum; j++) {
					arr[j] = Integer.parseInt(in.get(j));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			int size = arr.length;
			long startTime = System.currentTimeMillis();
			quickSort(arr, 0, size - 1);
			long endTime = System.currentTimeMillis();
			System.out.println(
					"Top Down MergeSort with " + name[i] + " input run time last:" + (endTime - startTime) + "ms");

			
			  // result sorted array
//			for (int q = 0; q < arr.length; q++) {
//			  System.out.print(arr[q] + " "); }
//			 
		}
		
//		int[] a = {3,25,6,21,14,57,2,16,95,23,152,12,63};
//		quickSort(a, 0, a.length - 1);
//		for(int i = 0; i < a.length; i++){
//			System.out.print(a[i]+" ");
//		}
	}

}
