package com.yifeng.Homework2;

public class MergeSortTopDown {

	private static int[] aux;

	public static void mergeSort(int[] a, int[] aux, int start, int end) {
		if (a == null || a.length == 0) {
			return;
		}
		int mid = start + (end - start) / 2;
		if (start < end) {
			mergeSort(a, aux, start, mid);
			mergeSort(a, aux, mid + 1, end);
			merge(a, aux, start, mid, end);
		}
	}

	public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		aux = new int[a.length];
		int i = lo, j = mid + 1;
		
		//auxiliary array has the same elements at the very beginning;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k]; 
		}

		for (int k = lo; k <= hi; k++) {
			if (i > mid) // if first half sub-array fully removed;
				a[k] = aux[j++];
			else if (j > hi)// if second half sub-array fully removed;
				a[k] = aux[i++];
			else if (aux[i] < aux[j])// remove current smaller element from which sub-array it belongs to the a[];
				a[k] = aux[i++];
			else
				a[k] = aux[j++];

		}
		//process of merge;
		for (int q = 0; q < a.length; q++) {
			System.out.print(aux[q] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int a[] = { 3, 2, 1, 4, 8, 57, 9, 34, 21, 34, 213, 5, 312, 12, 53, 125, 2236 };
		int size = a.length;

		mergeSort(a, aux, 0, size - 1);
		
		//result sorted array
		for (int q = 0; q < a.length; q++) {
			System.out.print(a[q] + " ");
		}
	}

}
