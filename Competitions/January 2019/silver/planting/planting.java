/*
ID: 100021881
LANG: JAVA
PROG: planting
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * planting
 */
public class planting {
    public static Node[] fields;
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();
        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));
        int N = Integer.parseInt(br.readLine());
        fields = new Node[N];
        for(int i = 0; i < N; i++) {
            fields[i] = new Node();
        }
        for(int i = 0; i < N-1; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(l.nextToken())-1;
            int second = Integer.parseInt(l.nextToken())-1;
            fields[first].edges.add(second);
            fields[second].edges.add(first);
        }
        for(int i = 0; i < N; i++) {
            fields[i].bytwo.addAll(fields[i].edges);
            for(int j : fields[i].edges) {
                fields[i].bytwo.addAll(fields[j].edges);
            }
        }
        br.close();

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        out.println(findComponents());
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static int findComponents() {
        int n = 0;
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].component == -1) {
                Set<Integer> taken  = new HashSet<Integer>();
                for(int j : fields[i].bytwo) {
                    taken.add(fields[j].component);
                }
                int j = 1;
                for(; ; j++) {
                    if(!taken.contains(j)) {
                        break;
                    }
                }
                fields[i].component=j;
                n = Math.max(n, j);
            }
        }
        return n;
    }
    public static class Node {
        public int component = -1;
        public Set<Integer> edges = new HashSet<Integer>();
        public Set<Integer> bytwo = new HashSet<Integer>();
        public Node() {}
    }
}
