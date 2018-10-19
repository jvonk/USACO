
/*
ID: 100021881
LANG: JAVA
PROG: maze1
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

public class maze1 {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
        StringTokenizer l = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(l.nextToken());
        int H = Integer.parseInt(l.nextToken());
        Node[] graph = new Node[W * H];
        
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                System.out.println(j * W + i);
                graph[j * W + i] = new Node();
            }
        }
        char[][] input = new char[2 * H + 1][2 * W + 1];
        for (int i = 0; i < 2 * H + 1; i++) {
            input[i] = br.readLine().toCharArray();
        }
        for (int i = 1; i < 2 * W + 1; i += 2) {
            for (int j = 1; j < 2 * H + 1; j += 2) {
                if (!(input[j][i] == ' ')) {
                    break;
                }
                int x = (i - 1) / 2;
                int y = (j - 1) / 2;
                int num = y * W + x;
                System.out.println(y + " " + x + " " + num);
                if (i - 1 >= 0 && input[j][i - 1] == ' ') {
                    graph[num].edges.add(new Edge(num - 1, 1));
                }
                if (i + 1 < 2 * W + 1 && input[j][i + 1] == ' ') {
                    graph[num].edges.add(new Edge(num + 1, 1));
                }
                if (j - 1 >= 0 && input[j - 1][i] == ' ') {
                    graph[num].edges.add(new Edge(num - W, 1));
                }
                if (j + 1 < 2 * H + 1 && input[j + 1][i] == ' ') {
                    graph[num].edges.add(new Edge(num + W, 1));
                }
            }
        }

        List<Node> ends = new ArrayList<>();
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                System.out.print("\n" + i + " " + j + "  \t\t");
                for (int k = 0; k < graph[j * W + i].edges.size(); k++) {
                    int temp = graph[j * W + i].edges.get(k).destination;
                    int x = temp % W;
                    int y = temp / W;
                    System.out.print((j*W+i)+" "+x + " " + y + "\t" + graph[j * W + i].edges.get(k).weight + "  \t\t");
                }
                if (j==1 && input[0][i*2+1]==' ') {
                    ends.add(graph[j * W + i]);
                    System.out.println(i+" "+j);
                }
                if (j==H-1 && input[2*H][i*2+1]==' ') {
                    ends.add(graph[j * W + i]);
                    System.out.println(i+" "+j);
                }
                if (i==1 && input[j*2+1][0]==' ') {
                    ends.add(graph[j * W + i]);
                    System.out.println(i+" "+j);
                }
                if (i==W-1 && input[j*2+1][2*W]==' ') {
                    ends.add(graph[j * W + i]);
                    System.out.println(i+" "+j);
                }
            }
        }
        int[] distances = new int[(W + 1) * (H + 1)];
        Arrays.fill(distances, -1);
        for (Node end : ends) {
            Node[] temp = new Node[(W + 1) * (H + 1)];
            temp = dijkstra(graph, end);
            for (int i = 0; i < temp.length; i++) {
                if (distances[i]==-1 || temp[i].distance<distances[i]) {
                    distances[i]=temp[i].distance;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (distances[j*W+i]>max) {
                    max=distances[j*W+i];
                }
            }
        }
        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        out.println(max);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static Node[] dijkstra(Node[] nodes, Node source) {
        // distance(j) is distance from source vertex to vertex j
        // parent(j) is the vertex that precedes vertex j in any shortest path (to
        // reconstruct the path subsequently)

        source.distance = 0;
        source.parent = -1;

        int numVisited = 0;
        while (numVisited < nodes.length) {
            // find unvisited vertex with min distance to source; call it vertex i
            int mini = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nodes.length; i++) {
                System.out.println(i);
                if (!nodes[i].visited && nodes[i].distance < min) {
                    mini = i;
                    min = nodes[i].distance;
                }
            }
            nodes[mini].visited = true;

            for (int i = 0; i < nodes[mini].edges.size(); i++) {
                Edge edge = nodes[mini].edges.get(i);
                System.out.println(mini);
                if (nodes[mini].distance + edge.weight < nodes[edge.destination].distance) {
                    nodes[edge.destination].distance = nodes[mini].distance + edge.weight;
                    nodes[edge.destination].parent = mini;
                }
            }
            numVisited++;
        }
        return nodes;
    }

    public static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static class Node {
        int distance;
        boolean visited;
        int parent;
        List<Edge> edges = new ArrayList<>();

        public Node(int distance, boolean visited, int parent) {
            this.distance = distance;
            this.visited = visited;
            this.parent = parent;
        }

        public Node() {
            this.distance = Integer.MAX_VALUE;
            this.visited = false;
            this.parent = -1;
        }
    }
}