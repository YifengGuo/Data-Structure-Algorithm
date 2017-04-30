package com.yifeng.KMP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KMP {
	void KMPSearch(String pat, StringBuffer txt) {
		int M = pat.length();
		int N = txt.length();
		int next[] = new int[M];// Array next
		int j = 0;

		computeLPSArray(pat, M, next);

		int i = 0; // index for txt[]
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				System.out.println("Found pattern " + "at index " + (i - j));
				j = next[j - 1];
			}

			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				if (j != 0)
					j = next[j - 1];
				else
					i = i + 1;
			}
		}
	}

	void computeLPSArray(String pat, int M, int next[]) {
		int len = 0;
		int i = 1;
		next[0] = 0; // next[0] is always 0

		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				next[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = next[len - 1];
				} else {
					next[i] = len;
					i++;
				}
			}
		}
	}

	public static void main(String args[]) {
		String pat = "searchgoal";
		try {
			BufferedReader br = new BufferedReader(new FileReader("/Users/guoyifeng/Downloads/KMPtest.txt"));
			String line = "";
			int lineNum = 0;
			ArrayList<String> in = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				in.add(line);
				lineNum++;
			}
			StringBuffer txt = new StringBuffer();
			for (int i = 0; i < in.size(); i++) {
				txt.append(in.get(i));
			}
			long start = System.currentTimeMillis();
			new KMP().KMPSearch(pat, txt);
			long end = System.currentTimeMillis();
			long time = end - start;
			System.out.println("KMP search time with 5757 input: " + time + "ms");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
