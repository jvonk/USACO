
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

        for (int i = 0; i < 10; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 10; j++) {

            }
        }
        br.close();

        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        
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
            int min = Integer.MAX_VALUE;
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