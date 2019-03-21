/*
ID: 100021881
LANG: JAVA
PROG: schlnet
*/

import java.io.*;
import java.util.*;

public class schlnet {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("schlnet.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));
        int N = Integer.parseInt(br.readLine());
        boolean[][] connections = new boolean[N+1][N+1];
        boolean[] input = new boolean[N+1];
        boolean[] output = new boolean[N+1];
        int[] parents = new int[N+1];
        List<List<Integer>> edges = new ArrayList<>(N+1);
        edges.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            edges.add(new ArrayList<>());
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < line.length - 1; j++) {
                int value = Integer.parseInt(line[j]);
                connections[i][value] = true;
                edges.get(i).add(value);
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (connections[j][i] && connections[i][k]) connections[j][k] = true;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (parents[i] == 0) {
                parents[0]++;
                parents[i] = i;
                for (int j = i + 1; j <= N; j++) {
                    if (parents[j] == 0 && connections[i][j] && connections[j][i]) parents[j] = i;
                }
            }
        }
        
        int ans1 = parents[0];
        int ans2 = parents[0];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                int value = parents[edges.get(i).get(j)];
                if (parents[i] != value) {
                    if (!input[value]) ans1--;
                    input[value] = true;
                    if (!output[parents[i]]) ans2--;
                    output[parents[i]] = true;
                }
            }
        }
        out.println(ans1);
        if (parents[0] == 1) out.println(0);
        else out.println(Math.max(ans1, ans2));
        br.close();
        out.close();
    }
}