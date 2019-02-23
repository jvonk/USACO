/*
ID: 100021881
LANG: JAVA
PROG: milk6
*/

/**
 * milk6
 * Use dinic algorithm to find the maximum flow.
 * Sum while you can reach the end using breath first search.
 * Then use depth first to find the minimum nodes that are needed for that sum.
 */

import java.util.*;
import java.io.*;

public class milk6 {
    public static int N, M;
    public static long[][] costs = new long[33][33], oldCosts = new long[33][33];
    public static Edge[] edges = new Edge[1001];
    public static int[] depths = new int[33];
    public static Queue<Integer> queue = new LinkedList<Integer>();

    public static boolean bfs() {
        Arrays.fill(depths, 0);
        depths[1] = 1;
        queue.add(1);
        while (!queue.isEmpty()) {
            int i = queue.remove();
            for (int j = 1; j <= N; j++) {
                if (costs[i][j] > 0 && depths[j] == 0) {
                    depths[j] = depths[i] + 1;
                    queue.add(j);
                }
            }
        }
        return depths[N] != 0;
    }

    public static long dfs(int i, long max) {
        if (i == N) {
            return max;
        }
        long flow = 0;
        for (int j = 1; j <= N; j++) {
            if (depths[j] - depths[i] == 1 && costs[i][j] != 0) {
                long d = dfs(j, Math.min(max - flow, costs[i][j]));
                flow += d;
                costs[i][j] -= d;
                costs[j][i] += d;
            }
        }
        if (flow == 0) depths[i] = 0;
        return flow;
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        for (int i = 0; i < M; i++) {
            edges[i] = new Edge(br.readLine().split(" "));
        }
        for (int i = 0; i < oldCosts.length; i++) {
            System.arraycopy(oldCosts[i], 0, costs[i], 0, oldCosts[i].length);
        }
        br.close();
        long flow = 0;
        while (bfs()) {
            flow += dfs(1, Long.MAX_VALUE);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        out.println((long) (flow / 1001) + " " + (long) (flow % 1001));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < oldCosts.length; j++) {
                System.arraycopy(oldCosts[j], 0, costs[j], 0, oldCosts[j].length);
            }
            costs[edges[i].u][edges[i].v] -= edges[i].w;
            long newFlow = 0;
            while (bfs()) {
                newFlow += dfs(1, Long.MAX_VALUE);
            }
            if (newFlow + edges[i].w == flow) {
                out.println(i + 1);
                oldCosts[edges[i].u][edges[i].v] -= edges[i].w;
                flow -= edges[i].w;
            }
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }

    public static class Edge {
        int u, v;
        long w;

        public Edge(String[] line) {
            this.u = Integer.parseInt(line[0]);
            this.v = Integer.parseInt(line[1]);
            this.w = Long.parseLong(line[2]);
            oldCosts[u][v] += w * 1001 + 1;
        }
    }
}