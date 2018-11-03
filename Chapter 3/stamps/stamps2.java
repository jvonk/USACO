/*
ID: 100021881
LANG: JAVA
PROG: stamps
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class stamps {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("stamps.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        
        int K = Integer.parseInt(l.nextToken()); //total number of stamps that can be used
        int N = Integer.parseInt(l.nextToken()); //number of stamp values

        int[] coins = new int[N];
        String line;
        int counter = 0;
        while ((line = br.readLine())!=null) {
            l = new StringTokenizer(line);
            while(l.hasMoreTokens()) {
                coins[counter]=Integer.parseInt(l.nextToken());
                counter++;
            }
        }

        br.close();
        Arrays.sort(coins);
        // recursively add stamps
        int[] minimums = new int[K*coins[N-1]+1];
        Arrays.fill(minimums, Integer.MAX_VALUE);
        minimums[0]=0;
        boolean finished = false;
        while(!finished) {
            finished=true;
            //for each coin
            for (int i = 0; i < N; i++) {
                for (int j = 0; j+coins[i] < minimums.length; j++) {
                    int newer = minimums[j]+1;
                    int old = minimums[j+coins[i]];
                    if (newer < old) {
                        finished=false;
                        minimums[j+coins[i]]=newer;
                    }
                }
            }
        }
        int i = 0;
        for (; i < minimums.length; i++) {
            if (minimums[i]>K) {
                break;
            }
            // DEBUG System.out.println(i+"\t"+minimums[i]);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));

        out.println(i-1);
        
        out.close();

        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}