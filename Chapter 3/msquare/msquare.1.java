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
            if (!check(item.str, 0)) {
                Node a = new Node(new int[] {item.target[7], item.target[6], item.target[5], item.target[4], item.target[3], item.target[2], item.target[1], item.target[0]}, item.str+"A");
                deque.addLast(a);
            }
            if (!check(item.str, 1)) {
                Node b = new Node(new int[] {item.target[3], item.target[0], item.target[1], item.target[2], item.target[5], item.target[6], item.target[7], item.target[4]}, item.str+"B");
                deque.addLast(b);
            }
            if (!check(item.str, 2)) {
                Node c = new Node(new int[] {item.target[0], item.target[6], item.target[1], item.target[3], item.target[4], item.target[2], item.target[5], item.target[7]}, item.str+"C");
                deque.addLast(c);
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        out.println(res.length());
        out.println(res);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    public static boolean check (String s, int which) {
        if (which==0) 
            return (s.length()!=0 && ((s.charAt(s.length()-1)=='A')||s.endsWith("CAC")||s.endsWith("BABBB")||s.endsWith("BBBAB")||s.endsWith("BBABB")/*||s.endsWith("CCACC")||s.endsWith("ACACCAC")||s.endsWith("CCCACCC")||s.endsWith("CBBBABC")||s.endsWith("CBBABBC")||s.endsWith("CACACAC")||s.endsWith("CBABBBC")||s.endsWith("BBCACBB")||s.endsWith("ABABBAB")||s.endsWith("ABBABAB")||s.endsWith("ABABABB")||s.endsWith("BCACBBB")||s.endsWith("BABABAB")||s.endsWith("BBBCACB")*/));
        if (which == 1)
            return (s.endsWith("BBB")||s.endsWith("ABABB")||s.endsWith("BABAB")||s.endsWith("ABBAB")||s.endsWith("ABBBA")||s.endsWith("BABBA")||s.endsWith("BBABA")/*||s.endsWith("ABCACBB")||s.endsWith("BABCACB")||s.endsWith("BBACACB")||s.endsWith("BBBACAC")||s.endsWith("ABBBCAC")||s.endsWith("BABBCAC")||s.endsWith("BBABCAC")||s.endsWith("BACACBB")||s.endsWith("ABBCACB")||s.endsWith("BCACBAB")||s.endsWith("BCACBBA")||s.endsWith("CACBBBA")||s.endsWith("ABABABA")||s.endsWith("BBCACBA")||s.endsWith("BBBCACA")||s.endsWith("CACBABB")||s.endsWith("BBCACAB")||s.endsWith("CACBBAB")||s.endsWith("BCACABB")*/);
        return (s.endsWith("ACA")||s.endsWith("CCC")/*||s.endsWith("ACCAC")||s.endsWith("CACCA")||s.endsWith("CCCACAC")||s.endsWith("CACCCAC")||s.endsWith("ACCCACC")||s.endsWith("CCACACC")||s.endsWith("ACBABBB")||s.endsWith("ACBBBAB")||s.endsWith("BBABBCA")||s.endsWith("BBBABCA")||s.endsWith("BABBBCA")||s.endsWith("CCACCCA")||s.endsWith("ACACACA")||s.endsWith("ACBBABB")*/);
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