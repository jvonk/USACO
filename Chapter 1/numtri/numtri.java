
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
        int[][] triangle = new int[N][N];
        int[][] results = new int[N][N];
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
        out.println(findLongest(triangle, 0, 0, results, total));
        br.close();
        out.close();
    }

    public static int findLongest(int[][] triangle, int i, int j, int[][] results, int total) {
        // System.out.println(i+" "+j);
        int max = triangle[i][j];
        if (results[0][0]==-1) {
            if (triangle.length - 2 > i) {
                max += Math.max(Math.max(findLongest(triangle, i + 2, j, results, total), findLongest(triangle, i + 2, j + 1, results, total)),
                        findLongest(triangle, i + 2, j + 2, results, total)) + Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            } else if (triangle.length - 1 > i) {
                max += Math.max(findLongest(triangle, i + 1, j, results, total), findLongest(triangle, i + 1, j + 1, results, total));
            }
            if (max==total) {
                results[0][0]=max;
            }
            results[i][j]=max;
        }
        if (results[0][0]!=-1) {  
            for (int ii = 0; ii < 10; ii++) {
                for (int jj = 0; jj < results[ii].length; jj++) {
                    System.out.print(results[ii][jj]+" ");                    
                }
                System.out.println();
            }
        }
        return max;
    }
}
