
/*
ID: 100021881
LANG: JAVA
PROG: rockers
*/

import java.io.*;
import java.util.*;

/**
 * Use brute force to go through all possible combinations of songs
 * @author  Johan Vonk <johan.d.s.vonk@gmail.com>
 */
public class rockers {
    static int N, T, M, best;
    static int[] len;
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("rockers.in"));
        int[] first = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = first[0];
        T = first[1];
        M = first[2];
        len = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        solve(0, 0, 0, 0);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
        out.println(best);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    /**
     * Recursively solve by brute force
     * @param   i   the current song
     * @param   n   number of songs used
     * @param   t   space used on current disk
     * @param   m   number of disks used
     */
    public static void solve(int i, int n, int t, int m) {
        if (i==N) {
            best = Math.max(best, n);
            return;
        }
        solve(i+1, n, t, m);
        if (T>=t+len[i]) {
            solve(i+1, n+1, t+len[i], m);
        } else if (m+1 < M && T >= len[i]) {
            solve(i+1, n+1, len[i], m+1);
        }
    }
}
