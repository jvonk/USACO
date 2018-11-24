/*
ID: 100021881
LANG: JAVA
PROG: humble
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class humble {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("humble.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(l.nextToken()); // number of primes in S
        int N = Integer.parseInt(l.nextToken()); // Nth humble number in S

        int[] S = new int[K];
        l = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            S[i] = Integer.parseInt(l.nextToken());

        br.close();
        System.out.println(System.currentTimeMillis() - startTime);
        
        int[] humbles = new int[N+1];
        int[] next = new int[K];
        humbles[0]=1;
        for (int i = 1; i <= N; i++) {
            int best = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++) {
                while (next[j] < i && S[j]*humbles[next[j]] <= humbles[i-1] ) {
                    next[j]++;
                }
                best=Math.min(best, S[j]*humbles[next[j]]);
            }
            humbles[i]=best;
        }
        // DEBUG System.out.println(humbles.toString());
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
        out.println(humbles[N]);
        out.close();

        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}