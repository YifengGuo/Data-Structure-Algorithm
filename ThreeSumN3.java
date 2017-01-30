import java.io.*;
import java.util.*;


public class ThreeSumN3 {
	public static void main(String[] args) {
		String path = "/Users/guoyifeng/Downloads/hw1-1.data/";
		BufferedReader br = null;
		List<String> in = new ArrayList<String>();
		int[] a = null;
		int[] name = { 8, 32, 128, 512, 1024, 4096, 4192, 8192 };
		for (int i = 0; i < name.length; i++) {
			try {

				br = new BufferedReader(new FileReader(path + name[i] + "int.txt/"));
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
					a = new int[lineNum];
					for (int j = 0; j < lineNum; j++) {
						a[j] = Integer.parseInt(in.get(j));
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
			
			long startTime = System.currentTimeMillis();
			bruteForceCount(a);
			long endTime = System.currentTimeMillis();
			System.out.println("brute force with " + name[i] + " input run time last:" + (endTime - startTime) + "ms");

			
			long startTime2 = System.currentTimeMillis();
			binarySearch(a);
			long endTime2 = System.currentTimeMillis();
			System.out.println("binary search with " + name[i] + " input run time last:" + (endTime2 - startTime2) + "ms");
			
		}
	}

	public static int bruteForceCount(int a[]) {
		int N = a.length;
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					if (a[i] + a[j] + a[k] == 0)
						count++;

		return count;

	}

	public static int binarySearch(int[] a) {
		Arrays.sort(a);
		int count = 0;
		int lo = 0;
		int hi = a.length - 1;
		int mid = 0;

		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j <a.length ; j++) {
				mid = lo + (hi - lo)/2;
				if (-a[i]-a[j] < a[mid]){
					hi = mid - 1;
				} else if(-a[i]-a[j] > a[mid]){
					lo = mid + 1;
				} else {
					count++;
					
				}

			}

		}
		//System.out.println(count);
		return count;
		
	}
	

}