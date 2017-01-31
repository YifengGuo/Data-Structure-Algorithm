import java.io.*;
import java.util.Scanner;

public class QuickFind {
	private int[] id;
	private int count;

	public QuickFind(int n) {
		count = n;
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private int find(int q) {
		return id[q];
	}

	public void union(int p, int q) {
		int pID = id[p];
		int qID = id[q];

		if (pID == qID)
			return;

		for (int i = 0; i < id.length; i++) {
			if (id[i] == pID)
				id[i] = qID;
		}
		count--;
	}

	public static void main(String[] args) {
		int[] name = { 8, 32, 128, 512, 1024, 4096, 8192 };

		for (int i = 0; i < name.length; i++) {
			long startTime = System.currentTimeMillis();
			int n = 10000;
			QuickFind qf = new QuickFind(n);
			Scanner in = null;

			try {
				in = new Scanner(new FileReader("/Users/guoyifeng/Downloads/hw1-2.data/" + name[i] + "pair.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String pattern = " ";
			in.useDelimiter(pattern);

			while (in.hasNext()) {
				String s = in.nextLine();
				String[] arr = s.split(pattern);
				int p = Integer.parseInt(arr[0]);
				int q = Integer.parseInt(arr[1]);
// System.out.print(p + " ");
// System.out.println(q);
				if (qf.connected(p, q))
					continue;
				qf.union(p, q);

				// System.out.println(p +" " + q);

			}
			long endTime = System.currentTimeMillis();
			System.out.println(n - qf.count() + " components");
			System.out.println("Quick find with " + name[i] + " input costs:" + (endTime - startTime) + "ms");
		}
	}

}
