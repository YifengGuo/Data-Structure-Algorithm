package com.yifeng.Homework3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BST<Key extends Comparable<Key>, Value> {
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int count;

		public Node(Key key, Value value, int count) {
			this.key = key;
			this.value = value;
			this.count = count;
		}
	}

	private Node root;
	
    public int size(){
        return size(root);
    }
     
    private int size(Node x){
        if(x == null){
            return 0;
        } else {
            return x.count;
        }
    }

    private Node put(Node x, Key key, Value value){
        if( x==null ){
            x = new Node(key, value, 1);
            return x;
        }
        int cmp = key.compareTo(x.key);
        if(cmp<0){
            x.left = put(x.left, key, value);
        }else if(cmp>0){
            x.right = put(x.right, key, value);
        } else{
            x.value=value;
        }
        x.count = size(x.left) + size(x.right) + 1;

        return x;
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }

	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node x, Key key) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return rank(x.left, key);
		} else if (cmp > 0) {
			return	size(x.left) + rank(x.right, key) + 1;
		} else {
			return size(x.left);
		}
	}
	
	public Key select(int k){
        Node x = select(root, k);
        if(x == null){
            return null;
        }
        return x.key;
    }
 
    private Node select(Node x, int k){
        if(x == null){
            return null;
        }
        int t = size(x.left);

        if(t == k) {
            return x;
        } else if(t>k){
            return select(x.left, k);
        }else{
            
            return select(x.right, k-t-1);
        }
    }

	public static void main(String[] args) {
		BST<Integer, String> bst =  new BST<Integer, String>();
		String path = "/Users/guoyifeng/Downloads/hw3-q3-data.txt/";
		BufferedReader br = null;
		List<String> in = new ArrayList<String>();
		int[] a = null;
		try {

			br = new BufferedReader(new FileReader(path));
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
//		for(int i = 0; i < a.length; i++){
//			System.out.println(a[i]);
//		}
		
		
		for(int i = 0; i < a.length; i++){
			bst.put(a[i], in.get(i).trim());
		}
		
		System.out.println(bst.select(9));
		System.out.println(bst.rank(15));
	}
	

}
