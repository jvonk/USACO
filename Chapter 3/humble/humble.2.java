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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;

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
        for (int i = 0; i < K; i++) {
            S[i] = Integer.parseInt(l.nextToken());
        }

        br.close();
        
        List<Integer> humbles = new ArrayList<>();
        for (int i = 2; humbles.size() < N; i++) {
            int num = i;
            for (int prime : S) {
                while (num % prime == 0) {
                    num /= prime;
                }
            }
            if (num==1) {
                humbles.add(i);
            }
        }
        // DEBUG System.out.println(humbles.toString());
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
        out.println(humbles.get(N-1));
        out.close();

        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}