/*
ID: 100021881
LANG: JAVA
PROG: inflate
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Deque;
import java.util.LinkedList;

/*
 *
 */
public class inflate {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("inflate.in"));

        StringTokenizer l = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(l.nextToken()); // contest minutes
        int N = Integer.parseInt(l.nextToken()); // number of problem classes
        

        int[] data = new int[M+1];

        for (int i = 0; i < N; i++) {
            l = new StringTokenizer(br.readLine());
            int points = Integer.parseInt(l.nextToken());
            int minutes = Integer.parseInt(l.nextToken());
            for (int j = minutes; j <= M; j++) {
                if (data[j]<data[j-minutes]+points) {
                    data[j] = data[j-minutes] + points;
                }
            }
        }

        
        br.close();



        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        out.println(data[M]);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
}