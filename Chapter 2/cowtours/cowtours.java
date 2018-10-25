
/*
ID: 100021881
LANG: JAVA
PROG: cowtours
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

public class cowtours {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("cowtours.in"));

        int N = Integer.parseInt(br.readLine());

        int[] pastureXs = new int[N];
        int[] pastureYs = new int[N];
        Node[] pastures = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            pastureXs[i]=Integer.parseInt(line.nextToken());
            pastureYs[i]=Integer.parseInt(line.nextToken());
            pastures[i]=new Node();
        }
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            pastures[i]=new Node();
            for (int j = 0; j < N; j++) {
                if (line[j]=='1') {
                    pastures[i].edges.add(new Edge(j, Math.sqrt(Math.pow(pastureXs[j]-pastureXs[i], 2) + Math.pow(pastureYs[j]-pastureYs[i], 2))));                    
                }
            }
        }

        br.close();
        for (int i = 0; i < N; i++) {
            Node[] temp =dijkstra(pastures, pastures[i]);
            for (int j = 0; j < N; j++) {
                if (temp[j].distance>pastures[j].distance) {
                    pastures[j].distance=temp[j].distance;
                }
            }            
        }
        for (int i = 0; i < N; i++) {
            System.out.println(pastures[i].distance);
        }
        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtours.out")));
        
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static Node[] dijkstra(Node[] nodes, Node source) {
        // distance(j) is distance from source vertex to vertex j
        // parent(j) is the vertex that precedes vertex j in any shortest path (to reconstruct the path subsequently) 
        
        source.distance=0;
        source.parent=-1;
        
        int numVisited = 0;
        while(numVisited<nodes.length) {
            // find unvisited vertex with min distance to source; call it vertex i
            int mini = 0;
            double min = Float.MAX_VALUE;
            for (int i=0; i < nodes.length; i++) {
                if (!nodes[i].visited && nodes[i].distance < min) {
                    mini=i;
                    min=nodes[i].distance;
                }
            }
            nodes[mini].visited=true;
            
            for (int i = 0; i < nodes[mini].edges.size(); i++) {
                Edge edge = nodes[mini].edges.get(i);
                if (nodes[mini].distance + edge.weight < nodes[edge.destination].distance) {
                    nodes[edge.destination].distance = nodes[mini].distance + edge.weight;
                    nodes[edge.destination].parent=i;
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
    }
}