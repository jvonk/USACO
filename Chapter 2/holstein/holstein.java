
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
        // Line 2: V integers (1 <= each one <= 1000), the minimum requirement for each of the V vitamins that a cow requires each day
        StringTokenizer line = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            vitamins[i]=Integer.parseInt(line.nextToken());
            System.out.print(vitamins[i]+" ");
        }
        System.out.println();
        // Line 3: integer G (1 <= G <= 15), the number of types of feeds available
        int G = Integer.parseInt(br.readLine());
        int[][] feeds = new int[G][V];
        // Lines 4..G+3: V integers (0 <= each one <= 1000), the amount of each vitamin that one scoop of this feed contains. The first line of these G lines describes feed #1; the second line describes feed #2; and so on.
        for (int i = 0; i < G; i++) {
            line = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) {
                feeds[i][j]=Integer.parseInt(line.nextToken());
                System.out.print(feeds[i][j]+" ");
            }
            System.out.println();
        }
        br.close();
        out.println(solve(feeds, vitamins, new int[V], amounts, -1));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
    public static int[] solve(int[][] feeds, int[] vitamins, int[] totals, int[] amounts, int which) {
        boolean works = true;
        if (which>-1) {
            amounts[which]++;
            for (int i = 0; i < feeds[which].length; i++) {
                totals[i]=feeds[which][i]*amounts[which];
                if (totals[i]<vitamins[i]) {
                    works=false;
                }
            }
        } else {
            works = false;
        }
        if(!works) {
            List<int[]> amountsArray = new ArrayList<int[]>();
            for (int i = 0; i < feeds[which].length; i++) {
                amountsArray.add(solve(feeds, vitamins, totals, amounts, i)));
            }
            int minCount = 1000000;
            for (int[] amountArray : amountArray) {
                int count = 0;
                for (int i = 0; i < amountArray.length; i++) {
                    count+=amountArray[i];
                }
                if (count < minCount) {
                    amounts=amountArray;
                    minCount=count;
                }
            }
        }
        return amounts;
    }
}
