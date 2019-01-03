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
        int alpha = 0;
        int[] sums = new int[N+1];
        boolean[][][] finished = new boolean[N+1][N+1][N+1];
        for (int i = N; i >= 2; i--) {
            for (int x = 0; x < N-i+1; x++) {
                for (int y = 0; y < N-i+1; y++) {
                    if (finished[i][x][y]) continue;
                    finished[i][x][y]=true;
                    boolean works = true;
                    for (int j = x; j < x+i; j++) {
                        for (int k = y; k < y+i; k++) {
                            alpha++;
                            if (data[j][k]==false) {
                                works = false;
                                break;
                            }       
                        }
                    }
                    if (works) {
                        sums[i]++;
                        for (int a = i-1; a >= 2; a--) {
                            for (int j = x; j < x+i-a+1; j++) {
                                for (int k = y; k < y+i-a+1; k++) {
                                    alpha++;
                                    if (finished[a][j][k]) continue;
                                    sums[a]++;
                                    finished[a][j][k]=true;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(alpha);
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