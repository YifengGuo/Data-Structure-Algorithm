package com.yifeng.KMP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BF {

	public int bruteForce(StringBuffer S, String T) {
		if (S == null || T == null) {
			return -1;
		}
		int n = S.length();
		int m = T.length();
		int i = 0;
		while (i + m <= n) {
			int k = i, j = 0;
			for (; j < m && k < n && S.charAt(k) == T.charAt(j); ++k, ++j)
				;
			if (j == m)
				return i; // match success, return the start position of
							// comparison
			++i;
		}
		return -1; // match failed
	}

	public static void main(String[] args) {
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
			System.out.println("BF search time with 5757 input: " + time + "ms");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
