
/*
ID: 100021881
LANG: JAVA
PROG: comehome
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

public class comehome {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("comehome.in"));

        int P = Integer.parseInt(br.readLine());
        boolean[] hasCow = new boolean[52];
        Node[] pastures = new Node[52];
        for (int i = 0; i < 52; i++) {
            pastures[i] = new Node();
        }
        for (int i = 0; i < P; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            char t = line.nextToken().charAt(0);
            int first = -1;
            if (t >= 'A' && t <= 'Z') {
                first = t - 'A';
                if (t != 'Z') {
                    hasCow[first] = true;
                }
            } else if (t >= 'a' && t <= 'z') {
                first = t - 'a' + 26;
                hasCow[first] = true;
            }
            t = line.nextToken().charAt(0);
            int second = -1;
            if (t >= 'A' && t <= 'Z') {
                second = t - 'A';
                if (t != 'Z') {
                    hasCow[second] = true;
                }
            } else if (t >= 'a' && t <= 'z') {
                second = t - 'a' + 26;
                hasCow[second] = true;
            }
            int distance = Integer.parseInt(line.nextToken());
            pastures[first].edges.add(new Edge(second, distance));
            pastures[second].edges.add(new Edge(first, distance));
        }
        br.close();

        
        pastures = dijkstra(pastures, pastures[25]);
        int min = Integer.MAX_VALUE;
        int minPasture = -1;
        for (int i = 0; i < 25; i++) {
            System.out.println((char) (i + 'A') + " " + hasCow[i] + " " + pastures[i].distance);
            if (hasCow[i] && pastures[i].distance < min) {
                min = pastures[i].distance;
                minPasture = i;
            }
        }

        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        out.print((char) (minPasture + 'A'));
        out.println(" " + min);
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