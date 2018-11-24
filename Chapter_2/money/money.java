
/*
ID: 100021881
LANG: JAVA
PROG: money
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class money {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("money.in"));

        // read first line with V and N
        StringTokenizer l = new StringTokenizer(br.readLine());

        // get V, the number of coins in the system
        int V = Integer.parseInt(l.nextToken());

        // get N, the amount money to construct
        int N = Integer.parseInt(l.nextToken());

        // DEBUG System.out.println(V+" "+N);

        // array of the number of ways in which to get a sum of N
        long[] ways = new long[N + 1];

        // array of coin values
        int[] coins = new int[V];

        // read all V values of coins and add to coin array
        String line = "";
        int v = 0;
        while ((line = br.readLine()) != null) {
            l = new StringTokenizer(line);
            while (l.hasMoreTokens()) {
                coins[v] = Integer.parseInt(l.nextToken());
                v++;
            }
        }

        // DEBUG for (int i = 0; i < coins.length; i++) {
        // DEBUG    System.out.print(coins[i]+" ");
        // DEBUG }
        // DEBUG System.out.println();
        br.close();

        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

        // the number of different ways in which to create 0 in any money system is 1
        // because it is always 0 of every value
        ways[0] = 1;

        // loop over every possible coin value
        for (int i = 0; i < coins.length; i++) {
            // loop over every sum
            for (int j = 1; j < ways.length; j++) {
                // checks that j-coins[i] is greater or equal to 0
                if (j >= coins[i]) {
                    // The number of ways to get a sum of j is the sum of all the number of ways to
                    // get values that are less than j by a value in coins[].

                    // If you add one coin of value coins[i] then the sum is coins[i] more.
                    // You can add the number of sums that are less than j by a number in coins[].
                    ways[j] += ways[j - coins[i]];
                }
            }
        }
        // print out the number of ways to get a total of N, the desired number
        out.println(ways[N]);
        // DEBUG for (int i = 0; i <= N; i++) {
        // DEBUG    System.out.println(i+" "+ways[i]);
        // DEBUG }
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
