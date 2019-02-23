/*
ID: 100021881
LANG: JAVA
PROG: milk6
*/

import java.io.*;
import java.util.*;

/**
 * milk6
 * Use dinic algorithm to find the maximum flow.
 * Sum while you can reach the end using breath first search.
 * Then use depth first to find the minimum nodes that are needed for that sum.
 */

public class milk6 {
    public static int N, M, numAnswers;
    public static Node[] nodes = new Node[2005];
    public static int[][] originalCosts = new int[40][40], newCosts = new int[40][40];
    public static int[] depths = new int[40], answers = new int[2005];
    public static boolean bfs() {
        Arrays.fill(depths, 0);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        depths[1]=1;
        while(!queue.isEmpty()) {
            int start = queue.remove();
            for (int end = 1; end <= N; end++) {
                if (originalCosts[start][end]>newCosts[start][end]&&depths[end]==0) {
                    queue.add(end);
                    depths[end]=depths[start]+1;
                    if (end==N) return true;
                }
            }
        }
        return false;
    }
    public static int dinic(int start, int flow) {
        if (start==N) return flow;
        int rest=flow;
        for (int end = 1; end <= N; end++) {
            if(originalCosts[start][end]>newCosts[start][end]&&depths[end]==depths[start]+1) {
                int k = dinic(end, Math.min(rest, originalCosts[start][end]-newCosts[start][end]));
                if(k==0)depths[end]=0;
                newCosts[start][end]+=k;
                newCosts[end][start]-=k;
                rest-=k;
            }
        }
        return flow-rest;
    }
    public static int maxf() {
        int flow = 0, maxflow=0;
        while (bfs()) {
            while((flow=dinic(1, Integer.MAX_VALUE))!=0) {
                maxflow+=flow;
            }
        }
        return maxflow;
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        for(int i = 1; i <= M; i++) {
            line = br.readLine().split(" ");
            nodes[i] = new Node();
            nodes[i].start = Integer.parseInt(line[0]);
            nodes[i].end = Integer.parseInt(line[1]);
            nodes[i].weight = Integer.parseInt(line[2])*1001+1;
            originalCosts[nodes[i].start][nodes[i].end]+=nodes[i].weight;
        }
        br.close();
        int maxflow=maxf(), sum = 0;
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        out.print(maxflow);
        Arrays.sort(nodes, 1, M+1);
        for (int i = 1; i <= M; i++) {
            newCosts=new int[40][40];
            originalCosts[nodes[i].start][nodes[i].end]-=nodes[i].weight;
            if (maxf()+nodes[i].weight==maxflow) {
                answers[++numAnswers]=i;
                sum+=nodes[i].weight;
                if (sum==maxflow) break;
            }
            originalCosts[nodes[i].start][nodes[i].end]+=nodes[i].weight;
        }
        out.println(" "+numAnswers);
        Arrays.sort(answers, 1, numAnswers+1);
        for (int i = 1; i <= numAnswers; i++) {
            out.println(answers[i]);
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static class Node implements Comparable {
        int start, end, weight;
        @Override
        public int compareTo(Object y) {
            return Integer.compare(weight,((Node)y).weight);
        }
    }
}
