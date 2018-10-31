/*
ID: 100021881
LANG: JAVA
PROG: cowtour
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

public class cowtour {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("cowtour.in"));

        int N = Integer.parseInt(br.readLine());

        int[] pastureXs = new int[N];
        int[] pastureYs = new int[N];
        Node[] pastures = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            pastureXs[i] = Integer.parseInt(line.nextToken());
            pastureYs[i] = Integer.parseInt(line.nextToken());
            pastures[i] = new Node();
        }
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            pastures[i] = new Node();
            for (int j = 0; j < N; j++) {
                if (line[j] == '1') {
                    pastures[i].edges.add(new Edge(j, Math.sqrt(
                            Math.pow(pastureXs[j] - pastureXs[i], 2.0) + Math.pow(pastureYs[j] - pastureYs[i], 2.0))));
                }
            }
        }

        br.close();
        int[] designations = new int[N];
        double[] distances = new double[N];
        Arrays.fill(designations, -1);
        Arrays.fill(distances, -1);

        for (int i = 0; i < N; i++) {
            // DEBUG for (int k = 0; k < pastures[i].edges.size(); k++) {
            // DEBUG System.out.println(
            // DEBUG i + " " + pastures[i].edges.get(k).destination + " " +
            // DEBUG pastures[i].edges.get(k).weight);
            // DEBUG }
            Node[] tem = pastures.clone();
            Node[] temp = dijkstra(tem, tem[i]);
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    // DEBUG if (i == 6 && j==1) {
                    // DEBUG System.out.println(temp[j].distance+"\n\n");
                    // DEBUG }
                    if (temp[j].distance == Double.MAX_VALUE) {
                        if (designations[j] == -1) {
                            designations[j] = i;
                        }
                    } else if (temp[j].distance > distances[j]) {
                        distances[j] = temp[j].distance;
                    }
                }
            }
        }
        // DEBUG for (int i = 0; i < N; i++) {
        // DEBUG System.out.println(i + "\t" + designations[i] + "\t" + distances[i]);
        // DEBUG }
        double diameter = Double.MAX_VALUE;
        double[] diameters = new double[N];
        Arrays.fill(diameters, Double.MAX_VALUE);
        int maxi = -1;
        int maxj = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (designations[i] != designations[j]) {
                    
                    double temp1 = Math.abs(distances[i]);
                    double temp2 = Math.abs(distances[j]);
                    double temp = temp1 + temp2 + Math.sqrt( Math.pow(pastureXs[j] - pastureXs[i], 2.0) + Math.pow(pastureYs[j] - pastureYs[i], 2.0) );
                    //if (N>149)
                        temp = Math.min(temp, Math.max(temp1, temp2));
                    if (diameter > temp) {
                        maxi = i;
                        maxj= j;
                        diameter=temp;
                        System.out.println(maxi+" "+distances[i]+"\t"+maxj+" "+distances[j]+"\t"+diameter);
                    }
                }
            }
        }
        System.out.println(diameter+"\t"+maxi+"\t"+maxj);
        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        out.format("%.6f",Math.abs(diameter)); 
        out.println();
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
            double min = Double.MAX_VALUE;
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
        double weight;

        public Edge(int destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static class Node {
        double distance;
        boolean visited;
        int parent;
        List<Edge> edges = new ArrayList<>();

        public Node(double distance, boolean visited, int parent) {
            this.distance = distance;
            this.visited = visited;
            this.parent = parent;
        }

        public Node() {
            this.distance = Double.MAX_VALUE;
            this.visited = false;
            this.parent = -1;
        }

        public void set() {
            this.distance = Double.MAX_VALUE;
            this.visited = false;
            this.parent = -1;
        }
    }
}