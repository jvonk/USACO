/*
ID: 100021881
LANG: JAVA
PROG: stall4
*/

import java.io.*;
import java.util.*;

/**
 * stall4
 */
public class stall4 {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int[][] graph = new int[N+M+2][N+M+2];
        for(int i = 1; i <= N; i++) {
            line = br.readLine().split(" ");
            graph[0][i] = 1;
            for (int j = 1; j <= Integer.parseInt(line[0]); j++) {
                graph[i][N+Integer.parseInt(line[j])] = 1;
                graph[N+Integer.parseInt(line[j])][N+M+1] = 1;
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        out.println(flow(graph, 0, N+M+1));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static int flow(int[][] graph, int source, int sink) {
        if(source == sink) {
            return Integer.MAX_VALUE;
        }
        int totalflow = 0;
        int[] prevnode = new int[graph.length];
        int[] flow = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        int maxflow = 0, maxloc = -1;
        while(true) {
            // find path with highest capacity from source to sink
            // uses a modified djikstra's algorithm
            Arrays.fill(prevnode, -1);
            Arrays.fill(flow, 0);
            Arrays.fill(visited, false);
            flow[source] = Integer.MAX_VALUE;
            while(true) {
                maxflow = 0;
                maxloc = -1;
                // find the unvisited node with the highest capacity to it
                for(int i = 0; i < graph.length; i++) {
                    if(flow[i] > maxflow && !visited[i]) {
                        maxflow = flow[i];
                        maxloc = i;
                    }
                }
                if(maxloc == -1 || maxloc == sink) {
                    break;
                }
                visited[maxloc] = true;
                // update its neighbors
                for(int i = 0; i < graph.length; i++) {
                    if(graph[maxloc][i] <= 0 || flow[i] >= Math.min(maxflow, graph[maxloc][i])) {
                        continue;
                    }
                    prevnode[i] = maxloc;
                    flow[i] = Math.min(maxflow, graph[maxloc][i]);
                }
            }
            if(maxloc == -1) {
                break; // no possible path left
            }
            int pathcapacity = flow[sink];
            totalflow += pathcapacity;
            // add that flow to the network,
            // update capacity appropriately
            int curnode = sink;
            // for each arc, prevnode(curnode),
            // curnode on path:
            while(curnode != source) {
                int nextnode = prevnode[curnode];
                graph[nextnode][curnode] -= pathcapacity;
                graph[curnode][nextnode] += pathcapacity;
                curnode = nextnode;
            }
        }
        return totalflow;
    }
}
