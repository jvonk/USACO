/*
ID: 100021881
LANG: JAVA
PROG: blist
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * blist
 */
public class blist {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("blist.in"));
        
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();            
        }

        br.close();

        int max = 0;
        for (int i = 1; i < 1000; i++) {
            int total=0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][0]<=i&&arr[j][1]>=i) total+=arr[j][2];
            }
            max = Math.max(max, total);
        }
        

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
        out.println(max);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}