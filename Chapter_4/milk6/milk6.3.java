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
    public static int N, M;
    public static boolean[] visited;
    public static boolean[][] results;
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        results=new boolean[2][N+1];
        int[][] connections = new int[N+1][N+1];
        int[][] map = new int[N+1][N+1];
        for (int i = 1; i <= M; i++) {
            line = br.readLine().split(" ");
            int first = Integer.parseInt(line[0]);
            int second = Integer.parseInt(line[1]);
            connections[first][second] +=  Integer.parseInt(line[2]);
            map[first][second] = i;
        }
        int C = flow(connections, 1, N);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j]!=0&&visited[i]&&!visited[j]) {
                    results[0][map[i][j]] = true;
                }
            }
        }
        String str = "";
        for (int i = 0; i <= N; i++) {
            if (results[0][i]) {
                str+=i+"\n";
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        out.print(C+" "+str.split("\n").length+"\n"+str);
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
        visited = new boolean[graph.length];
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
                for(int i = 1; i < graph.length; i++) {
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
                for(int i = 1; i < graph.length; i++) {
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
