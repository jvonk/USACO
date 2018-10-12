
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
        int V = Integer.parseInt(br.readLine());
        int[] vitamins = new int[V];
        // Line 2: V integers (1 <= each one <= 1000), the minimum requirement for each
        // of the V vitamins that a cow requires each day
        StringTokenizer line = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            vitamins[i] = Integer.parseInt(line.nextToken());
            System.out.print(vitamins[i] + " ");
        }
        System.out.println();
        // Line 3: integer G (1 <= G <= 15), the number of types of feeds available
        int G = Integer.parseInt(br.readLine());
        int[][] feeds = new int[G][V];
        // Lines 4..G+3: V integers (0 <= each one <= 1000), the amount of each vitamin
        // that one scoop of this feed contains. The first line of these G lines
        // describes feed #1; the second line describes feed #2; and so on.
        int m = 1000; //max number of vitamins that could work
        for (int i = 0; i < G; i++) {
            int max = 0;
            line = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) {
                feeds[i][j] = Integer.parseInt(line.nextToken());
                System.out.print(feeds[i][j] + " ");
                if (feeds[i][j] > 0) {
                    int temp = vitamins[j] / feeds[i][j] + 1;
                    if (temp > max)
                        max = temp;
                }
            }
            if (max < m)
                m = max;
            System.out.println();
        }
        m *= feeds.length;
        System.out.println(m);
        br.close();
        int[] amounts = new int[feeds.length];
        amounts = solve(feeds, vitamins, amounts, 0, m);
        int[] totals = new int[vitamins.length];
        totals = count(feeds, vitamins, amounts);
        int sum = sum(amounts);
        out.print(sum);
        for (int i = 0; i < amounts.length; i++) {
            if (amounts[i] > 0) {
                out.print(" " + (i + 1));
            }
        }
        out.println();
        for (int i = 0; i < totals.length; i++) {
            System.out.println(vitamins[i] + " " + totals[i]);
        }
        System.out.println();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static int[] solve(int[][] feeds, int[] vitamins, int[] amounts, int count, int min) {
        int[] minArray = new int[amounts.length];
        minArray = amounts;
        if (count < feeds.length) {
            int temp = sum(amounts) - amounts[count];
            for (int i = 0; i <= min; i++) {
                if (temp + i > min)
                    break;
                int[] t = new int[amounts.length];
                t = amounts;
                t[count] = i;
                t = solve(feeds, vitamins, t, count + 1, min);
                if (works(feeds, vitamins, t)) {
                    int sum = sum(t);
                    if (sum < min) {
                        min = sum;
                        minArray = t;
                    } else if (sum == min) {
                        System.out.println(sum+" "+t);
                    }
                }
            }
        }
        return minArray;
    }

    public static boolean works(int[][] feeds, int[] vitamins, int[] amounts) {
        int[] totals = new int[vitamins.length];
        totals = count(feeds, vitamins, amounts);
        for (int i = 0; i < vitamins.length; i++) {
            if (totals[i] < vitamins[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] count(int[][] feeds, int[] vitamins, int[] amounts) {
        int[] totals = new int[vitamins.length];
        for (int i = 0; i < amounts.length; i++) {
            for (int j = 0; j < feeds[i].length; j++) {
                totals[j] += feeds[i][j] * amounts[i];
            }
        }
        return totals;
    }

    public static int sum(int[] amounts) {
        int c = 0;
        for (int i = 0; i < amounts.length; i++) {
            c += amounts[i];
        }
        return c;
    }
}
