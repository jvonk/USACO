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
        humbles.add(1);
        for (int k = 0; k < 3; k++) 
        for (int i = 0; i < K; i++) {
            int len = humbles.size();
            for (int j = 0; j < len; j++) {
                humbles.add(S[i]*humbles.get(j));
            }
        }

        
        TreeSet<Integer> results = new TreeSet<>();
        results.addAll(humbles);
        Iterator<Integer> it = results.iterator();
        int i = 0;
        int result = -1;
        while(it.hasNext()) {
            if (i==N) {
                break;
            }
            result=it.next();
            i++;
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
        out.println(result);
        System.out.println(results.toString());
        out.close();

        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}