/*
ID: 100021881
LANG: JAVA
PROG: msquare
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Deque;
import java.util.LinkedList;

/*
 * msquare
 */
public class msquare {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("msquare.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        
        Node target = new Node (new int [] {Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken())});

        br.close();

        HashSet<Node> set = new HashSet<>();
        Deque<Node> deque = new LinkedList<Node>();
        deque.add(new Node (new int[] {1, 2, 3, 4, 5, 6, 7, 8}));
        String res = "NONE";
        while (!deque.isEmpty()) {
            Node item = deque.remove();
            if (item.compareTo(target)==0) {
                res = item.str;
                break;
            }
            //if (item.str.length()==7)
            //    System.out.println(item.str);
            if (item.str.equals("BCABCCB")) {
                for (int i = 0; i < 8; i++) {
                    System.out.println(i+": "+item.target[i]);
                }
            }
            if (item.str.length() == 0 || item.str.charAt(item.str.length()-1) != 'A') {
                Node a = a(item);
                if (!set.contains(a)) {
                    set.add(a);
                    deque.addLast(a);
                }
            }
            Node b = b(item);
            if (!set.contains(b)) {
                set.add(b);
                deque.addLast(b);
            }
            Node c = c(item);
            if (!set.contains(c)) {
                set.add(c);
                deque.addLast(c);
            }
        }


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        out.println(res);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    //0  1  2  3
    //7  6  5  4
    public static Node a(Node n) {
        return new Node(new int[] {n.target[7], n.target[6], n.target[5], n.target[4], n.target[3], n.target[2], n.target[1], n.target[0]}, n.str+"A");
    }
    public static Node b(Node n) {
        return new Node(new int[] {n.target[3], n.target[0], n.target[1], n.target[2], n.target[5], n.target[6], n.target[7], n.target[4]}, n.str+"B");
    }
    public static Node c(Node n) {
        return new Node(new int[] {n.target[0], n.target[6], n.target[1], n.target[3], n.target[4], n.target[2], n.target[5], n.target[7]}, n.str+"C");
    }
    public static class Node implements Comparable<Node> {
        int[] target;
        String str;
        public Node (int[] t) {
            target = t;
            str="";
        }
        public Node (int[] t, String init) {
            target = t;
            str=init;
        }
        @Override
        public int compareTo(Node other) {
            if(Arrays.equals(this.target, other.target)) return 0;
            else return -1;
        }
    }
}