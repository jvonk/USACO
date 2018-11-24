
/*
ID: 100021881
LANG: JAVA
PROG: nocows
*/

import java.io.*;
import java.util.*;

public class nocows {
    static long[][] results;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(line.nextToken());
        int K = Integer.parseInt(line.nextToken());
        results = new long[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(results[i], -1);
        }
            out.println(solve(N, K));
        /*
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                if (results[i][j] != -1)
                    System.out.println(i + "," + j + " " + results[i][j]);
            }
        }*/
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }

    public static long solve(int n, int k) {
        if (results[n][k] != -1) {
            return results[n][k];
        }
        if (n < 1 || k < 1 || 2*k-1>n || n%2==0) {
            return results[n][k]=0;
        }
        if (n == 1) {
            if (k == 1) {
                return results[n][k]=1;
            }
            return results[n][k]=0;
        }
        results[n][k] = 0;
        for (int i = 1; i < n - 1; i+=2) {
            int temp = n - i - 1;
            for (int j = 0; j < k - 1; j++) {
                results[n][k] += solve(i, j) * solve(temp, k - 1);
                results[n][k] += solve(i, k - 1) * solve(temp, j);
            }
            results[n][k] += solve(i, k - 1) * solve(temp, k - 1);
        }
        results[n][k] %= 9901;
        return results[n][k];
    }

    /*
     * public static long f(int n, int k, int count, boolean works) { if (n < 0 ||
     * count > k) { return 0; } if (n==0 && count==k) { return 1; } if
     * (results[n][count]!=-1) { return results[n][count]; } results[n][count]=2*f(n
     * - 1, k, count+1, works) + f(n - 2, k, count+1, works); return
     * results[n][count]; }
     */
}
