/*
ID: 100021881
LANG: JAVA
PROG: milk6
*/

import java.io.*;
import java.util.*;

/**
 * milk6
 * Maximum flow is equal to the minimum cut necessary.
 * Therefore, just use the maximum flow algorithm from earlier.
 */

public class milk6 {
    public static final int maxN = 40, maxM = 1002;
    public static int N, M, maxFlow, minNum;
    public static int[][] costs = new int[maxN][maxN], originalCosts = new int[maxN][maxN];
    public static int[] starts = new int[maxM], ends = new int[maxM], cost = new int[maxM], index = new int[maxM];
    public static int[] levels = new int[maxM];
    public static boolean[] visiting = new boolean[maxM], best = new boolean[maxM];
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        for(int i = 1; i <= M; i++) {
            line = br.readLine().split(" ");
            starts[i] = Integer.parseInt(line[0]);
            ends[i] = Integer.parseInt(line[1]);
            cost[i] = Integer.parseInt(line[2]);
            originalCosts[starts[i]][ends[i]] += cost[i];
        }
        br.close();
        costs = originalCosts.clone();
        maxFlow = dinic();

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        out.print(maxFlow + " ");
        int sum = 0;
        for(int i = 1; i <= M; i++) {
            costs = originalCosts.clone();
            costs[starts[i]][ends[i]] -= cost[i];
            if(dinic() < maxFlow) {
                index[++index[0]] = i;
                sum += cost[i];
            }
        }
        if(sum == maxFlow) {
            for(int i = 0; i <= index[0]; i++) {
                out.println(index[i]);
            }
        } else {
            minNum = index[0];
            getBest(1, 0, 0);
            out.println(minNum);
            for(int i = 1; i <= index[0]; i++) {
                if(best[i]) {
                    out.println(index[i]);
                }
            }
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static boolean bfs() {
        Arrays.fill(levels, 0);
        int head = 0, tail = 1;
        levels[1] = 1;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        while(!queue.isEmpty()) {
            int current = queue.remove();
            for(int i = 1; i <= N; i++) {
                if(levels[i] == 0 && costs[current][i] > 0) {
                    levels[i] = levels[current] + 1;
                    queue.add(i);
                }
            }
        }
        return levels[N] != 0;
    }
    public static int flow(int current, int maxFlow) {
        if(current == N) {
            return maxFlow;
        }
        for(int i = 1; i <= N; i++) {
            if(levels[i] == levels[current] + 1 && costs[current][i] > 0) {
                int pathflow = flow(i, Math.min(maxFlow, costs[current][i]));
                if(pathflow > 0) {
                    costs[current][i] -= pathflow;
                    costs[i][current] += pathflow;
                    return pathflow;
                }
            }
        }
        return 0;
    }
    public static int dinic() {
        int ans = 0;
        while(bfs()) {
            ans += flow(1, Integer.MAX_VALUE);
        }
        return ans;
    }
    public static void getBest(int cur, int totalCost, int totalN) {
        if(totalN >= minNum) {
            return;
        }
        if(cur > index[0]) {
            if(totalCost != maxFlow) {
                return;
            }
            costs = originalCosts.clone();
            for(int i = 1; i <= index[0]; i++) {
                if(visiting[i]) {
                    costs[starts[index[i]]][ends[index[i]]] -= cost[index[i]];
                }
            }
            if(!bfs()) {
                minNum = totalN;
                for(int i = 1; i < index[0]; i++) {
                    best[i] = visiting[i];
                }
            }
            return;
        }
        if(totalCost + cost[index[cur]] <= maxFlow) {
            visiting[cur] = true;
            getBest(cur + 1, totalCost + cost[index[cur]], totalN + 1);
        }
        visiting[cur] = false;
        getBest(cur + 1, totalCost, totalN);
    }
}
