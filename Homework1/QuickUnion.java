import java.io.*;
import java.util.Scanner;

public class QuickUnion {
	private int[] id;
	private int count;

	public QuickUnion(int n) {
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
	
	//to find the root  
	private int find(int p) {
		while(p != id[p]){
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		//find the root node for p and q;
		int pID = find(p);
		int qID = find(q);
		//if p and q are already in the same tree, then return;
		if (pID == qID) return;
	
		//make p's parent-node be the root node of q,then p and q are in the same tree;
		id[p] = qID;
		
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

