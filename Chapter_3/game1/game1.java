
/*
ID: 100021881
LANG: JAVA
PROG: game1
*/

import java.io.*;
import java.util.*;

public class game1 {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("game1.in"));
        int N = Integer.parseInt(br.readLine());
        int[][] sums = new int[N + 1][N + 1], dp = new int[N + 1][N + 1];
        for (int t = 0; t < N;) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                sums[++t][t] = Integer.parseInt(st.nextToken());
                dp[t][t] = sums[t][t];
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                sums[i][j] = sums[i][j - 1] + dp[j][j];
            }
        }
        for (int n = 1; n < N; n++) {
            for (int i = 1; i + n <= N; i++) {
                dp[i][i + n] = sums[i][i + n] - Math.min(dp[i][i + n - 1], dp[i + 1][i + n]);
            }
        }
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
        out.println(dp[1][N] + " " + (sums[1][N] - dp[1][N]));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}