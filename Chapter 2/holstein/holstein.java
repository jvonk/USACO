
/*
ID: 100021881
LANG: JAVA
PROG: holstein
*/

import java.io.*;
import java.util.*;

public class holstein {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

        // Line 1: integer V (1 <= V <= 25), the number of types of vitamins
        V = Integer.parseInt(br.readLine());
        min = new int[V];

        // Line 2: V integers (1 <= each one <= 1000), the minimum requirement for each
        // of the V vitamins that a cow requires each day
        StringTokenizer line = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            min[i] = Integer.parseInt(line.nextToken());
            System.out.print(min[i] + " ");
        }
        System.out.println();

        // Line 3: integer G (1 <= G <= 15), the number of types of feeds available
        G = Integer.parseInt(br.readLine());

        feeds = new int[G][V];
        // Lines 4..G+3: V integers (0 <= each one <= 1000), the amount of each vitamin
        // that one scoop of this feed contains. The first line of these G lines
        // describes feed #1; the second line describes feed #2; and so on.
        for (int i = 0; i < G; i++) {
            line = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) {
                feeds[i][j] = Integer.parseInt(line.nextToken());
                System.out.print(feeds[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        br.close();

        best = null;
        bestCount = Integer.MAX_VALUE;
        solve(0, new boolean[G], 0, new int[V]);

        out.print(bestCount + " ");
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < best.length; i++) {
            if (best[i]) {
                results.add(i+1);
            }
        }
        for (int i = 0; i < results.size(); i++) {
            if (i<results.size()-1) {
                out.print(results.get(i)+" ");
            } else {
                out.println(results.get(i));
            }
        }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    static int[] min;
    static int[][] feeds;
    static int V, G;

    static boolean[] best;
    static int bestCount;

    public static void solve(int current, boolean[] done, int count, int[] visited) {
        if (current == done.length) {
            for (int i = 0; i < V; i++) {
                if (visited[i] < min[i]) {
                    return;
                }
            }
            if (compare(done, count)) {
                bestCount = count;
                best = done.clone();
            }
            return;
        }
        solve(current + 1, done, count, visited);
        for (int i = 0; i < V; i++) {
            visited[i] += feeds[current][i];
        }
        done[current] = true;
        solve(current + 1, done, count + 1, visited);
        for (int i = 0; i < V; i++) {
            visited[i] -= feeds[current][i];
        }
        done[current] = false;
    }

    public static boolean compare(boolean[] done, int count) {
        if (count < bestCount) {
            return true;
        } else if (count > bestCount) {
            return false;
        } else {
            for (int i = 0; i < G; i++) {
                if (done[i] && !best[i]) {
                    return true;
                } else if (!done[i] && best[i]) {
                    return false;
                }
            }
            return false;
        }
    }
}
