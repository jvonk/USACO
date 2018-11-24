
/*
ID: 100021881
LANG: JAVA
PROG: numtri
*/

import java.io.*;
import java.util.*;

public class numtri {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[N+1][N+1];
        int[][] results = new int[N+1][N+1];
        int total = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            StringTokenizer line = new StringTokenizer(br.readLine());
            for (int j = 0; line.hasMoreTokens(); j++) {
                triangle[i][j] = Integer.parseInt(line.nextToken());
                results[i][j] = -1;
                if(triangle[i][j]>max) max=triangle[i][j];
            }
            total+=max;
        }
        out.println(findLongest(triangle, 0, 0, results, N));
        br.close();
        out.close();
    }

    public static int findLongest(int[][] triangle, int i, int j, int[][] results, int N) {
        // System.out.println(i+" "+j);
        if(results[i][j]!=-1) {
            return results[i][j];
        } else if (i<=triangle.length) {
            results[i][j]=triangle[i][j]+Math.max(findLongest(triangle, i+1, j, results, N), findLongest(triangle, i+1, j+1, results, N));
            return results[i][j];
        } else {
            return 0;
        }
    }
}
