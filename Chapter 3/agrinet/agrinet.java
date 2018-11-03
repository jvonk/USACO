/*
ID: 100021881
LANG: JAVA
PROG: agrinet
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class agrinet {
    private static int N;
    private static int[][] dist;
    private static Node[] nodes;


    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("agrinet.in"));

        N = Integer.parseInt(br.readLine());

        dist = new int[N][N];
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            StringTokenizer l;
            while ((l = new StringTokenizer(line)).countTokens()<N) {
                line+=" "+br.readLine();
            }
            //System.out.println(i);
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(l.nextToken());
                //System.out.println(j + "\t"+dist[i][j]);
            }
        }

        br.close();

        System.out.println(System.currentTimeMillis() - startTime);

        int res = solve();


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
        out.println(res);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static int solve() {
        // distance(j) is distance from tree to node j
        // source(j) is which node of so-far connected MST is closest to node j
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
        }
        int treesize = 1;                    // add node 1 to tree
        int treecost = 0; 
        nodes[0].inTree = true;
        nodes[0].distance = 0;
        nodes[0].source = -1;
        for (int j = 1; j < N; j++) {
            if (dist[0][j]!=-1) {
                nodes[j].distance=dist[0][j];
                nodes[j].source=0;
            }
        }

        while (treesize < N) {
            // find node with minimum distance to tree; call it node i
            int mini = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (!nodes[i].inTree && nodes[i].distance < min) {
                    min = nodes[i].distance;
                    mini = i;
                }
            }
            if (min == Integer.MAX_VALUE) {
                System.out.println("Graph Is Not Connected");
                return -1;
            }
            // add edge source(i),i to MST
            treesize++;
            treecost += min;
            nodes[mini].inTree = true;  // mark node i as in tree 

            // update distance after node i added
            for (int j = 0; j < N; j++) {
                if (nodes[j].distance > dist[mini][j]) {
                    nodes[j].distance=dist[mini][j];
                    nodes[j].source=mini;
                }
            }
        }
        return treecost;
    }
    public static class Node {
        int distance;
        boolean inTree;
        int source;

        public Node(int distance, boolean inTree, int source) {
            this.distance = distance;
            this.inTree = inTree;
            this.source = source;
        }

        public Node() {
            this.distance = Integer.MAX_VALUE;
            this.inTree = false;
            this.source = -1;
        }

        public void set() {
            this.distance = Integer.MAX_VALUE;
            this.inTree = false;
            this.source = -1;
        }
    }
}