
/*
ID: 100021881
LANG: JAVA
PROG: subset
*/

import java.io.*;
import java.util.*;

public class subset {
    static long[][] results;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        int N = Integer.parseInt(br.readLine());
        int target = N * (N + 1) / 2;
        results = new long[target / 2 + 1][N + 1];
        for (int i = 0; i < results.length; i++) {
            Arrays.fill(results[i], -1);
        }
        if (target % 2 != 0) {
            out.println("0");
        } else {
            out.println(f(target / 2, N) / 2);
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static long f(int n, int k) {
        if (n < 0 || k < 0) {
            return 0;
        }
        if (results[n][k] != -1) {
            return results[n][k];
        }
        if (n == 0 && k == 0) {
            return 1;
        }
        return results[n][k]=f(n, k - 1) + f(n - k, k - 1);
    }
}
