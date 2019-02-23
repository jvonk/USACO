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
        System.out.println(C);
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (connections[i][j]==0&&map[i][j]!=0) {
                    System.out.println(i+"\t"+j + "\t" + visited[i]+"\t"+visited[j]);
                }
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
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
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(source);
            Arrays.fill(prevnode, -1);
            Arrays.fill(flow, 0);
            Arrays.fill(visited, false);
            visited[source]=true;
            while (!queue.isEmpty()) {
                int next = queue.remove();
                for (int i = 0; i < graph.length; i++) {
                    if (graph[next][i]>0&&!visited[i]) {
                        prevnode[i]=next;
                        visited[i]=true;
                        queue.add(i);
                    }
                }
            }
            int pathcapacity = Integer.MAX_VALUE;
            int current = sink;
            int previous = -1;
            while (prevnode[current]>-1) {
                previous = prevnode[current];
                pathcapacity=Math.min(pathcapacity, graph[previous][current]);
                current=previous;
            }
            current=sink;
            while (prevnode[current]>-1) {
                previous=prevnode[current];
                graph[previous][current]-=pathcapacity;
                graph[current][previous]+=pathcapacity;
                current=previous;
            }
            if (pathcapacity==Integer.MAX_VALUE) {
                break;
            }
            totalflow+=pathcapacity;
        }
        return totalflow;
    }
}
