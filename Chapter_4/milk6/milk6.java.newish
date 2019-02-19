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
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        int[][] connections = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            connections[Integer.parseInt(line[0])][Integer.parseInt(line[1])] +=  Integer.parseInt(line[2]);
        }
        int C = flow(connections, 1, N);
        String str = "";
        int total = 0;
        for (int i = 0; i <= N; i++) {
            if (visited[i]) {
                str+=i+"\n";
                total++;
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        out.println(C+" "+total);
        out.println(str);
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
                System.out.println(maxloc);
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


    public static int flow(int[][] graph, int source, int sink) {
        if(source == sink) {
            return Integer.MAX_VALUE;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.push(source);
        visited = new boolean[graph.length];
        visited[source]=true;
        int[] from = new int[graph.length];
        Arrays.fill(from, -1);
        while (!queue.isEmpty()) {
            int next = queue.remove();
            for (int i = 0; i < graph.length; i++) {
                if (graph[next][i]>0&&!visited[i]) {
                    from[i]=next;
                    visited[i]=true;
                }
            }
        }
        int pathCap = Integer.MAX_VALUE;
        int current = source;
        int previous = -1;
        while (from[current]>-1) {
            previous = from[current];
            pathCap=Math.min(pathCap, graph[prev][current])
            current=previous;
        }
        while ()
        return totalflow;
    }
