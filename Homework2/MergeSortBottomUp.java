package com.yifeng.Homework2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeSortBottomUp {

	private static int[] aux;

	public static void mergeSort(int[] a) {
		if (a == null || a.length == 0) {
			return;
		}
		
		int totalLength = a.length;
		
		for (int size = 1; size < totalLength; size += size) {
			for (int lo = 0; lo < totalLength - size; lo += size + size) {
				merge(a, aux, lo, lo + size - 1, Math.min(lo + size + size - 1, totalLength - 1));
			}
		}
	}

	public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		aux = new int[a.length];
		int i = lo, j = mid + 1;

		// auxiliary array has the same elements at the very beginning;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		for (int k = lo; k <= hi; k++) {
			if (i > mid) // if first half sub-array fully removed;
				a[k] = aux[j++];
			else if (j > hi)// if second half sub-array fully removed;
				a[k] = aux[i++];
			else if (aux[i] < aux[j])// remove current smaller element from
										// which sub-array it belongs to the
										// a[];
				a[k] = aux[i++];
			else
				a[k] = aux[j++];

		}
		/*
		 * // process of merge; for (int q = 0; q < a.length; q++) {
		 * System.out.print(aux[q] + " "); } System.out.println();
		 */
	}

	public static void main(String[] args) {
		// int a[] = { 3, 2, 1, 4, 8, 57, 9, 34, 21, 34, 213, 5, 312, 12, 53,
		// 125, 2236 };

		String path = "//Users/guoyifeng/Downloads/hw2-dataset/data1.";
		BufferedReader br = null;
		List<String> in = new ArrayList<String>();
		int[] arr = null;
		int[] name = { 1024 , 2048, 4096, 8192, 16384, 32768  };
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

			// int size = arr.length;
			long startTime = System.currentTimeMillis();
			mergeSort(arr);
			long endTime = System.currentTimeMillis();
			System.out.println(
					"Top Down MergeSort with " + name[i] + " input run time last:" + (endTime - startTime) + "ms");
			/*
			// result sorted array
			for (int q = 0; q < arr.length; q++) {
				System.out.print(arr[q] + " ");
			}
			*/
		}
	}
}
