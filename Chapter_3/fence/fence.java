/*
ID: 100021881
LANG: JAVA
PROG: fence
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * fence
 */
public class fence {
    static int F, circuitPos;
    static Node[] nodes;
    static int[] circuit;
    static Stack<Node> stack;

    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("fence.in"));
        F = Integer.parseInt(br.readLine()); //The number of fences
        nodes = new Node[501];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i]=new Node();
        }
        stack = new Stack<>();
        circuit = new int[F+1];
        for (int i = 0; i < F; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(l.nextToken());
            int second = Integer.parseInt(l.nextToken());
            nodes[first].neighbors.add(second);
            nodes[second].neighbors.add(first);
        }
        for (int i = 0; i < nodes.length; i++) {
            Collections.sort(nodes[i].neighbors);
        }
        br.close();

        // DEBUG for (int i = 1; i < nodes.length; i++) {
        // DEBUG System.out.print(nodes[i].neighbors.size()==0?"":i+": "+nodes[i].neighbors.toString()+"\n");
        // DEBUG }
        find_euler_circuit();

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
        for (int i = F; i >= 0; i--) {
            out.println(circuit[i]);
        }
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static class Node {
        List<Integer> neighbors = new ArrayList<Integer>();
    }
    // circuit is a global array
    public static void find_euler_circuit() {
        circuitPos = 0; // circuitPos = 0
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].neighbors.size()%2==1) {
                find_circuit(i); // find_circuit(node i)
                return;
            }
        }
        find_circuit(1);
    }
    public static void find_circuit(int i) {
        // the path will be found in reverse order
        while(nodes[i].neighbors.size()>0) {                // while (node i has neighbors)
            int j = nodes[i].neighbors.remove(0);           //     pick the lowest neighbor node j of node i
            nodes[j].neighbors.remove((Integer)i);          //     delete_edges (node j, node i)
            find_circuit(j);                                //     find_circuit (node j)
        }
        circuit[circuitPos]=i;                              // circuit(circuitPos) = node i
        circuitPos++;                                       // circuitPos = circuitPos + 1
    }
}