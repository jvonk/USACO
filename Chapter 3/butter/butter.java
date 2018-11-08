/*
ID: 100021881
LANG: JAVA
PROG: butter
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
import java.util.Deque;
import java.util.LinkedList;

/*
 * butter
 * same as comehome basically, use dijkstra's algorithm
 */
public class butter {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("butter.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(l.nextToken());
        int P = Integer.parseInt(l.nextToken());
        int C = Integer.parseInt(l.nextToken());

        boolean[] hasCow = new boolean[P];

        for (int i = 0; i < N; i++) {
            hasCow[Integer.parseInt(br.readLine())-1]=true;
        }

        Node[] pastures = new Node[P];

        for (int i = 0; i < P; i++) {
            pastures[i] = new Node();
        }

        for (int i = 0; i < C; i++) {
            l = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(l.nextToken())-1;
            int second = Integer.parseInt(l.nextToken())-1;
            int distance = Integer.parseInt(l.nextToken());
            pastures[first].edges.add(new Edge(second, distance));
            pastures[second].edges.add(new Edge(first, distance));
        }

        br.close();


        int min = Integer.MAX_VALUE;
        for (int i = 0; i < P; i++) {
            Node[] calculated = dijkstra(pastures, pastures[i]);
            int total = 0;
            for (int j = 0; j < P; j++) {
                if(hasCow[j]) {
                    total+=calculated[j].distance;
                }
            }
            if (total<min) {
                min = total;
            }
        }


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
        out.println(min);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static Node[] dijkstra(Node[] nodes, Node source) {
        // distance(j) is distance from source vertex to vertex j
        // parent(j) is the vertex that precedes vertex j in any shortest path (to
        // reconstruct the path subsequently)

        for (int i = 0; i < nodes.length; i++) {
            nodes[i].set();
        }
        source.distance = 0;
        source.parent = -1;

        int numVisited = 0;
        while (numVisited < nodes.length) {
            // find unvisited vertex with min distance to source; call it vertex i
            int mini = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nodes.length; i++) {
                if (!nodes[i].visited && nodes[i].distance < min) {
                    mini = i;
                    min = nodes[i].distance;
                }
            }
            nodes[mini].visited = true;

            for (int i = 0; i < nodes[mini].edges.size(); i++) {
                Edge edge = nodes[mini].edges.get(i);
                if (nodes[mini].distance + edge.weight < nodes[edge.destination].distance) {
                    nodes[edge.destination].distance = nodes[mini].distance + edge.weight;
                    nodes[edge.destination].parent = i;
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

        public void set() {
            this.distance = Integer.MAX_VALUE;
            this.visited = false;
            this.parent = -1;
        }
    }
}