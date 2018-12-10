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

        
        int tar = Integer.parseInt(br.readLine().replaceAll(" ",""));
        
        br.close();

        HashSet<Integer> set = new HashSet<>();
        Deque<Node> deque = new LinkedList<Node>();
        deque.add(new Node (12345678));
        String res = "NONE";
        while (!deque.isEmpty()) {
            Node item = deque.remove();
            if (item.target==tar) {
                res = item.str;
                break;
            }
            if (!check(item.str, 0)) {
                Node a = item.a();
                if (!set.contains(a)) {
                    set.add(a.target);
                    deque.addLast(a);
                }
            }

            if (!check(item.str, 1)) {
                Node b = item.b();
                if (!set.contains(b)) {
                    set.add(b.target);
                    deque.addLast(b);
                }
            }

            if (!check(item.str, 2)) {
                Node c = item.c();
                if (!set.contains(c)) {
                    set.add(c.target);
                    deque.addLast(c);
                }
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
            return (s.endsWith("BBB")||s.endsWith("ABABB")||s.endsWith("BABAB")||s.endsWith("ABBAB")||s.endsWith("ABBBA")/*||s.endsWith("BABBA")||s.endsWith("BBABA")||s.endsWith("ABCACBB")||s.endsWith("BABCACB")||s.endsWith("BBACACB")||s.endsWith("BBBACAC")||s.endsWith("ABBBCAC")||s.endsWith("BABBCAC")||s.endsWith("BBABCAC")||s.endsWith("BACACBB")||s.endsWith("ABBCACB")||s.endsWith("BCACBAB")||s.endsWith("BCACBBA")||s.endsWith("CACBBBA")||s.endsWith("ABABABA")||s.endsWith("BBCACBA")||s.endsWith("BBBCACA")||s.endsWith("CACBABB")||s.endsWith("BBCACAB")||s.endsWith("CACBBAB")||s.endsWith("BCACABB")*/);
        return (s.endsWith("ACA")||s.endsWith("CCC")/*||s.endsWith("ACCAC")||s.endsWith("CACCA")||s.endsWith("CCCACAC")||s.endsWith("CACCCAC")||s.endsWith("ACCCACC")||s.endsWith("CCACACC")||s.endsWith("ACBABBB")||s.endsWith("ACBBBAB")||s.endsWith("BBABBCA")||s.endsWith("BBBABCA")||s.endsWith("BABBBCA")||s.endsWith("CCACCCA")||s.endsWith("ACACACA")||s.endsWith("ACBBABB")*/);
    }
    public static class Node implements Comparable<Node> {
        int target;
        String str;
        public Node (int t) {
            target = t;
            str="";
        }
        public Node (int t, String init) {
            target = t;
            str=init;
        }
        @Override
        public int compareTo(Node other) {
            if(target==other.target) return 0;
            else return -1;
        }
        public Node a() {
            return new Node(((target%10)*10000000)+(((target/10)%10)*1000000)+(((target/100)%10)*100000)+(((target/1000)%10)*10000)+(((target/10000)%10)*1000)+(((target/100000)%10)*100)+(((target/1000000)%10)*10)+(target/10000000),str+"A");
        }
        public Node b() {
            return new Node((target/100000*10000)+(((target/10000)%10)*10000000)+((target%1000)*10)+((target/1000)%10),str+"B");
        }
        public Node c() {
            return new Node((target-target%10000000)+((target%100 - target%10)*100000)+((target%10000000 - target%1000000)/10)+(target%100000 - target%1000)+((target%1000000 - target%100000)/1000)+((target%1000 - target%100)/10)+target%10,str+"C");
        }
    }
}