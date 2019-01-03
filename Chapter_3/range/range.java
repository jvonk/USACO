/*
ID: 100021881
LANG: JAVA
PROG: range
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * range
 */
public class range {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("range.in"));
        int N = Integer.parseInt(br.readLine());
        boolean[][] data = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            char[] in = br.readLine().toCharArray();
            boolean[] arr = new boolean[N];
            for (int j = 0; j < N; j++) {
                arr[j]=in[j]!='0';
            }
            data[i]=arr;
        }
        br.close();
        
        int[] sums = new int[N+1];
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                loop: for (int i = 1; i <= N-Math.max(x, y)+1; i++) {
                    if (y+i>N||x+i>N) break loop;
                    for (int y2 = y; y2 < y+i; y2++){
                        if (!data[x+i-1][y2]) break loop;
                    }
                    for (int x2 = x; x2 < x+i-1; x2++){
                        if (!data[x2][y+i-1]) break loop;                        
                    }
                    sums[i]++;
                }
            }   
        }
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
        for (int i = 2; i < N+1; i++) {
            if (sums[i]==0) continue;
            out.println(i+" "+sums[i]);
        }
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}