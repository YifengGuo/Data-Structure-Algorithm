package com.yifeng.Homework2;
import java.util.ArrayDeque;  
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.Deque;  
import java.util.List;

public class Two_Three_Tree <T extends Comparable<T>>{  
    private class Node{  
        Node parent; 
        //value  
        Object[] data_K;  
        //child node reference  
        Object[] node_A;  
          
        public Node(int k, int a, T data, Node parent){  
            data_K =  new Object[k];  
            node_A =  new Object[a];  
            data_K[0] = data;  
            this.parent = parent;  
        }  
        public Node(int k, int a, Node parent){  
            data_K =  new Object[k];  
            node_A =  new Object[a];  
            this.parent = parent;  
        }  
        public String toString(){  
            if(data_K != null){  
                StringBuilder sb = new StringBuilder("[");  
                for(Object k : data_K){  
                    sb.append(k.toString()+",");  
                }  
                return sb.toString().substring(0, sb.length() - 1) + "]";  
            }else{  
                return "[]";  
            }  
        }  
    }  
    //root  
    private Node root;  
      
    public Two_Three_Tree(){  
        root = null;  
    }  
    public Two_Three_Tree(T data){  
        root = new Node(1, 2, data, null);  
    }  
      
    /** 
     * insertion 
     * @param data 
     */  
    public void put(T data){  
        if(root == null){  
            root = new Node(1, 2, data, null);  
        }else{  
            //find the position to put  
            Node current = root;  
            Node parent = current;  
            T maxtmp = null,mintmp = null;  
            while(current != null){  
                if(current.data_K.length == 1){ //two-node  
                    maxtmp = mintmp = (T)current.data_K[0];  
                }else{ //three-node  
                    int result = ((T)current.data_K[0]).compareTo((T)current.data_K[1]);  
                    if(result > 0 ){  
                        maxtmp = (T)current.data_K[0];  
                        mintmp = (T)current.data_K[1];  
                    }else if(result < 0){  
                        maxtmp = (T)current.data_K[1];  
                        mintmp = (T)current.data_K[0];  
                    }  
                }    
                if(data.compareTo(mintmp) == 0 || data.compareTo(maxtmp) == 0){  
                    return; //if the element to be inserted is already existed in the tree, return.  
                }  
                if(maxtmp == mintmp){ //two-node  
                    parent = current;  
                    if(data.compareTo(maxtmp) > 0){  
                        current = (Node) current.node_A[1];  
                    }else if(data.compareTo(maxtmp) < 0){  
                        current = (Node)current.node_A[0];  
                    }  
                }else{ //three-node  
                    parent = current;  
                    if(data.compareTo(maxtmp) > 0){  
                        current = (Node)current.node_A[2];  
                    }else if(data.compareTo(mintmp) > 0){  
                        current = (Node)current.node_A[1];  
                    }else{  
                        current = (Node)current.node_A[0];  
                    }  
                }  
            }  
            
            if(parent != null && maxtmp == mintmp && maxtmp != null){  
                //data[]++, node[]++  
                parent.data_K = Arrays.copyOf(parent.data_K, parent.data_K.length + 1);  
                parent.node_A = Arrays.copyOf(parent.node_A, parent.node_A.length + 1);  
                //put data into data[]  
                parent.data_K[1] = data;  
                //maintain three-node 
                swap(parent, 0, 1);  
                  
            }else if(parent != null && maxtmp != mintmp && maxtmp != null){  
                fixForPut(parent.parent, parent, data);  
            }  
        }  
    }  
      
    /**
     * maintain the three-node after insertion
     * @param parent
     * @param node
     * @param data
     */
    private void fixForPut(Node parent, Node node, T data) {  
        if(root == node && node.data_K.length == 2){ //node==3-Node && node==root  
            if(data.compareTo((T)node.data_K[0]) < 0){  
                root = new Node(1, 2, (T)node.data_K[0], null);  
                root.node_A[0] = new Node(1, 2, data, root);  
                root.node_A[1] = new Node(1, 2, (T)node.data_K[1], root);  
            }else if(data.compareTo((T)node.data_K[1]) > 0){  
                root = new Node(1, 2, (T)node.data_K[1], null);  
                root.node_A[0] = new Node(1, 2, (T)node.data_K[0], root);  
                root.node_A[1] = new Node(1, 2, data, root);  
            }else{  
                root = new Node(1, 2, data, null);  
                root.node_A[0] = new Node(1, 2, (T)node.data_K[0], root);  
                root.node_A[1] = new Node(1, 2, (T)node.data_K[1], root);  
            }  
            return;  
        }  
        /*if(node.data_K.length == 2 && parent != null && parent.data_K.length == 1){//node==3-Node && parent==2-Node 
             
        }*/  
        if(node.data_K.length == 2){  
            Node n_4Node = create4_Node(node, data);  
            Node current = n_4Node;  
            while(current != null && current.data_K.length == 3){  
                current = split(current, current.parent);  
            }  
        }  
          
    }  
      
    /**
     * create temporary four-node 
     * @param node_3
     * @param data
     * @return
     */
    private Node create4_Node(Node node_3, T data){  
        if(node_3 != null && node_3.data_K.length == 2){ //if the current node is three-node, create temp 4-node 
            Node node_4 = new Node(3, 4, node_3.parent);
            node_4.data_K[0] = node_3.data_K[0];  
            node_4.data_K[1] = node_3.data_K[1];  
            node_4.data_K[2] = data;  
            swap(node_4, 0, 1);  
            swap(node_4, 0, 2);  
            swap(node_4, 1, 2);  
              
            finalDealForSplit(node_4, node_3);  
            return node_4;  
        }  
       
          
        return null;  
    }  
    /** 
     * split four-node into two two-node 
     * @param node_4 
     */  
    private Node split(Node node_4, Node parent){  
        if(node_4 != null){  
            if(root == node_4 && node_4.data_K.length == 3){//if 4-node is root or subroot  
                root = new Node(1, 2, (T)node_4.data_K[1], null);  
                Node l_tmp = new Node(1, 2, (T)node_4.data_K[0], root);  
                Node r_tmp = new Node(1, 2, (T)node_4.data_K[2], root);  
                l_tmp.node_A[0] = node_4.node_A[0];  
                l_tmp.node_A[1] = node_4.node_A[1];  
                if( node_4.node_A[0] != null){  
                    Node t0 = (Node)node_4.node_A[0];  
                    t0.parent = l_tmp;  
                }  
                if( node_4.node_A[1] != null){  
                    Node t1 = (Node)node_4.node_A[1];  
                    t1.parent = l_tmp;  
                }  
                r_tmp.node_A[0] = node_4.node_A[2];  
                r_tmp.node_A[1] = node_4.node_A[3];  
                if( node_4.node_A[2] != null){  
                    Node t2 = (Node)node_4.node_A[2];  
                    t2.parent = r_tmp;  
                }  
                if( node_4.node_A[3] != null){  
                    Node t3 = (Node)node_4.node_A[3];  
                    t3.parent = r_tmp;  
                }         
                  
                root.node_A[0] = l_tmp;  
                root.node_A[1] = r_tmp;  
                  
                return root;  
            }else if(node_4.data_K.length == 3 && parent.data_K.length == 1){//current node is four-node and its parent is two-node 
                  
                Node p = new Node(2, 3, parent.parent);  
                Node s1 = new Node(1, 2, p);  
                Node s2 = new Node(1, 2, p);  
                  
                p.data_K[0] = node_4.data_K[1];  
                p.data_K[1] = parent.data_K[0];  
                  
                swap(p, 0, 1);  
                  
                if(node_4 == parent.node_A[0]){ //left
                    p.node_A[2] = parent.node_A[1];  
                    if(parent.node_A[1] != null){  
                        Node t = (Node)parent.node_A[1];  
                        t.parent = p;  
                    }  
                      
                    s1_S2(s1, s2, node_4, p);  
                      
                    p.node_A[0] = s1;  
                    p.node_A[1] = s2;  
                      
                }else if(node_4 == parent.node_A[1]){//right 
                    p.node_A[0] = parent.node_A[0];  
                    if(parent.node_A[0] != null){  
                        Node t = (Node)parent.node_A[0];  
                        t.parent = p;  
                    }  
                      
                    s1_S2(s1, s2, node_4, p);  
                      
                    p.node_A[1] = s1;  
                    p.node_A[2] = s2;  
                }  
                  
                finalDealForSplit(p, parent);  
                if(p.parent == null){  
                    root = p;  
                }  
                return p;  
                  
            }else if(node_4.data_K.length == 3 && parent.data_K.length == 2){//current node is four-node and its parent is three-node
                Node p = new Node(3, 4, parent.parent);  
                Node s1 = new Node(1, 2, p);  
                Node s2 = new Node(1, 2, p);  
                p.data_K[0] = node_4.data_K[1];  
                p.data_K[1] = parent.data_K[0];  
                p.data_K[2] = parent.data_K[1];  

                swap(p, 0, 1);  
                swap(p, 0, 2);  
                swap(p, 1, 2);  
                  
                if(node_4 == parent.node_A[0]){//left  
                    p.node_A[2] = parent.node_A[1];  
                    p.node_A[3] = parent.node_A[2];  
                    if(parent.node_A[1] != null){  
                        Node t3 = (Node)parent.node_A[1];  
                        t3.parent = p;  
                    }  
                    if(parent.node_A[2] != null){  
                        Node t2 = (Node)parent.node_A[2];  
                        t2.parent = p;  
                    }  
                      
                    s1_S2(s1, s2, node_4, p);  
                      
                    p.node_A[0] = s1;  
                    p.node_A[1] = s2;  
                }else if(node_4 == parent.node_A[1]){ //middle  
                    p.node_A[0] = parent.node_A[0];  
                    p.node_A[3] = parent.node_A[2];  
                    if(parent.node_A[0] != null){  
                        Node t0 = (Node)parent.node_A[0];  
                        t0.parent = p;  
                    }  
                    if(parent.node_A[2] != null){  
                        Node t2 = (Node)parent.node_A[2];  
                        t2.parent = p;  
                    }  
                      
                    s1_S2(s1, s2, node_4, p);  
                      
                    p.node_A[1] = s1;  
                    p.node_A[2] = s2;  
                }else if(node_4 == parent.node_A[2]){ //right  
                    p.node_A[0] = parent.node_A[0];  
                    p.node_A[1] = parent.node_A[1];  
                    if(parent.node_A[0] != null){  
                        Node t0 = (Node)parent.node_A[2];  
                        t0.parent = p;  
                    }  
                    if(parent.node_A[1] != null){  
                        Node t1 = (Node)parent.node_A[1];  
                        t1.parent = p;  
                    }  
                      
                    s1_S2(s1, s2, node_4, p);  
                      
                    p.node_A[2] = s1;  
                    p.node_A[3] = s2;  
                }  
                finalDealForSplit(p, parent);  
                if(p.parent == null){  
                    root = p;  
                }  
                return p;  
            }  
        }  
        return null;  
    }  
    private void s1_S2(Node s1, Node s2, Node node_4,Node p){  
        s1.data_K[0] = node_4.data_K[0];  
        s1.node_A[0] = node_4.node_A[0];  
        s1.node_A[1] = node_4.node_A[1];  
        if(node_4.node_A[0] != null){  
            Node n1 = (Node)node_4.node_A[0];  
            n1.parent = s1;  
        }  
        if(node_4.node_A[1] != null){  
            Node n2 = (Node)node_4.node_A[1];  
            n2.parent = s1;  
        }  
        s2.data_K[0] = node_4.data_K[2];  
        s2.node_A[0] = node_4.node_A[2];  
        s2.node_A[1] = node_4.node_A[3];  
        if(node_4.node_A[2] != null){  
            Node n3 = (Node)node_4.node_A[2];  
            n3.parent = s2;  
        }  
        if(node_4.node_A[3] != null){  
            Node n4 = (Node)node_4.node_A[3];  
            n4.parent = s2;  
        }  
    }  
    //  
    private void finalDealForSplit(Node p, Node parent){  
        if(root == parent){  
            root = p;  
        }else{  
            Node pp = parent.parent;  
            for(int i = 0; pp != null && i < pp.node_A.length; i ++){  
                if(pp.node_A[i] == parent){  
                    pp.node_A[i] = p;  
                    break;  
                }  
            }  
        }  
    }  

    //if t1 > t2 , swap t1 and t2  
    private void swap(Node node, int t1, int t2){  
        if(node != null && node.data_K.length > t2){  
            if(((T)node.data_K[t1]).compareTo((T)node.data_K[t2]) > 0){  
                T t = (T)node.data_K[t1];  
                node.data_K[t1] = node.data_K[t2];  
                node.data_K[t2] = t;  
            }  
        }  
    } 
    
    /** 
     * search the node with certain data
     * @param data 
     * @return 
     */  
    public Msg search(T data){  
        if(root == null){  
            new Exception("The tree is empty");  
        }  
        else{  
            int height = 1;  
            Node current = root;  
            while(current != null){  
                if(current.data_K.length == 1){ //current node is two-node  
                    if(data.compareTo((T)current.data_K[0]) < 0){  
                        current = (Node) current.node_A[0];  
                    }else if(data.compareTo((T)current.data_K[0]) > 0){  
                        current = (Node) current.node_A[1];  
                    }else{  
                        return new Msg(current, height);  
                    }  
                }  
                else if(current.data_K.length == 2){//current-node is three-node  
                    if(data.compareTo((T)current.data_K[0]) == 0 || data.compareTo((T)current.data_K[1]) == 0){  
                        return new Msg(current, height);  
                    }  
                    if(data.compareTo((T)current.data_K[0]) < 0 ){  
                        current = (Node)current.node_A[0];  
                    }else if(data.compareTo((T)current.data_K[1]) > 0){  
                        current = (Node)current.node_A[2];  
                    }else{  
                        current = (Node)current.node_A[1];  
                    }  
                }  
                height ++;  
            }  
        }  
        return null;  
    }  
  
    //BFS 
    public List<Node> breadthFirstSearch(){  
        return cBreadthFirstSearch(root);  
    }  
    private List<Node> cBreadthFirstSearch(Node node) {  
        List<Node> nodes = new ArrayList<Node>();  
        Deque<Node> deque = new ArrayDeque<Node>();  
        if(node != null){  
            deque.offer(node);  
        }  
        while(!deque.isEmpty()){  
            Node n = deque.poll();  
            nodes.add(n);  
            for(int i = 0; i < n.node_A.length; i ++){  
                if(n.node_A[i] != null){  
                    deque.offer((Node)n.node_A[i]);  
                }  
            }  
        }  
        return nodes;  
    }  
    public static void main(String[] args) {  
        Two_Three_Tree<Integer> tree = new Two_Three_Tree<Integer>();  
        tree.put(12);  
        tree.put(10);
        tree.put(9);
//        System.out.println(tree.breadthFirstSearch());  
//        tree.put(11);  
//        System.out.println(tree.breadthFirstSearch());  
//        tree.put(14);  
//        System.out.println(tree.breadthFirstSearch());  
//        tree.put(16);  
//        System.out.println(tree.breadthFirstSearch());  
//        tree.put(18);  
//        System.out.println(tree.breadthFirstSearch());  
//        tree.put(20);  
//        System.out.println(tree.breadthFirstSearch());  
        
        System.out.println(tree.search(12));
 
    }
    
    private class Msg{  
        private Node node;  
        private int height;  
        public Msg(Node node, int height){  
            this.node = node;  
            this.height = height;  
        }  
        public String toString(){  
            return "Layer"+height+"£º"+node.toString();  
        }  
    }  
        
    
}  