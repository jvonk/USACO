
/*
ID: 100021881
LANG: JAVA
PROG: heritage
*/

import java.io.*;
import java.util.*;

public class heritage {
    public static char[] inorder, preorder;
    public static int preindex = 0;
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("heritage.in"));
        inorder = br.readLine().toCharArray();
        preorder = br.readLine().toCharArray();
        Node n = tree(0, inorder.length-1);
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
        out.println(n.toString());
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static Node tree(int start, int end) {
        if(start > end) {
            return null;
        }
        Node node = new Node(preorder[preindex++]);
        if(start == end) {
            return node;
        }
        int inindex = find(inorder, node.data, start, end);
        node.left = tree(start, inindex-1);
        node.right = tree(inindex+1, end);
        return node;
    }
    public static int find(char[] arr, char f, int start, int end) {
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == f) {
                return i;
            }
        return -1;
    }

    static class Node {
        public char data;
        public Node left, right;
        public Node(char d) {
            data = d;
            left = null;
            right = null;
        }
        public String toString() {
            return (left==null?"":left.toString())+(right==null?"":right.toString())+Character.toString(data);
        }
    }
}
